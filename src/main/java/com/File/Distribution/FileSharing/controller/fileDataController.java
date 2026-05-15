package com.File.Distribution.FileSharing.controller;

import com.File.Distribution.FileSharing.model.fileData;
import com.File.Distribution.FileSharing.repo.fileDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class fileDataController {
    @Autowired
    private fileDataRepo fileDataRepo;
    private final String UPLOAD_DIR="uploads/";



    @PostMapping("/uploads")
    public String uploadFile(@RequestParam("file")MultipartFile file)
    {
        try{
            File folder = new File(UPLOAD_DIR);
            if (!folder.exists())
            {
                folder.mkdir();
            }
            String fileName = file.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR+fileName);
            Files.write(path,file.getBytes());

            //add to db detailes--->
            fileData fileData = new fileData();
            fileData.setFilename(fileName);
            fileData.setFilesize(file.getSize());
            fileData.setFilepath(path.toString());
            fileDataRepo.save(fileData);
            return "file saved to db ";





        }
        catch (Exception e)
        {
            return e.getMessage();
        }


    }

    @GetMapping("{id}")
    public String  id (@PathVariable int id)
    {
        fileData fileData = new fileData();


        return fileDataRepo.findById(id).getFilename();
    }
}
