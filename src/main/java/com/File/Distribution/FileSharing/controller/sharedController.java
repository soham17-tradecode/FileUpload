package com.File.Distribution.FileSharing.controller;

import com.File.Distribution.FileSharing.model.fileData;
import com.File.Distribution.FileSharing.model.sharedEntity;
import com.File.Distribution.FileSharing.repo.fileDataRepo;
import com.File.Distribution.FileSharing.repo.sharedRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class sharedController {
    @Autowired
    sharedRepo sharedRepo;
    @Autowired
    fileDataRepo fileDataRepo;

    @PostMapping("/share/{fieldId}/{username}")
    public String sharedWith(@PathVariable int fieldId, @PathVariable String username, Principal principal)
    {
        try{
            Optional<fileData> fileData = fileDataRepo.findById(fieldId);
            if (fileData.isEmpty())
            {
                return "file not found";
            }

            fileData fileData1 = fileData.get();
            if (!fileData1.getUploadBy().equals(principal.getName()))
            {
                return "unauthorized";
            }


            sharedEntity sharedEntity = new sharedEntity();
            sharedEntity.setFieldId(fieldId);
            sharedEntity.setOwner(principal.getName());
            sharedEntity.setSharedWith(username);

            sharedRepo.save(sharedEntity);
            return "file shared successfully";



        }catch (Exception e)
        {
            return e.getMessage();
        }
    }


    @GetMapping("/shared")
    public List<fileData> sharedEntityList(Principal pr)
    {
        List<sharedEntity> sharedEntity = sharedRepo.findBySharedWith(pr.getName());
        List<fileData> fileData = new ArrayList<>();
        for (sharedEntity sharedEntity1:sharedEntity)
        {
            fileDataRepo.findById(sharedEntity1.getFieldId()).ifPresent(fileData::add);
        }
        return fileData;
    }

}
