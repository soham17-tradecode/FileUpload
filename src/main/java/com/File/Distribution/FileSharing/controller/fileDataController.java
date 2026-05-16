package com.File.Distribution.FileSharing.controller;

import com.File.Distribution.FileSharing.model.fileData;
import com.File.Distribution.FileSharing.repo.fileDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
public class fileDataController {
    @Autowired
    private fileDataRepo fileDataRepo;
    private final String UPLOAD_DIR="uploads/";


 //upload the file--->
    @PostMapping("/uploads")
    public String uploadFile(@RequestParam("file")MultipartFile file)
    {
        try{

            Authentication authentication =
                    SecurityContextHolder
                            .getContext()
                            .getAuthentication();
            assert authentication != null;
            String username = authentication.getName();


            String userFolder = "uploads/" + username + "/";

            File folder = new File(userFolder);
            if (!folder.exists())
            {
                folder.mkdir();
            }
            String fileName = file.getOriginalFilename();


            Path path = Paths.get(userFolder+fileName);
            System.out.println(path);


            Files.write(path,file.getBytes());

            //add to db detailes--->
            fileData fileData = new fileData();

            fileData.setFilename(fileName);

            fileData.setFilesize(file.getSize());

            fileData.setFilepath(path.toString());

            fileData.setUploadBy(username);

            fileDataRepo.save(fileData);

            return "upload successfully ";





        }
        catch (Exception e)
        {
            return e.getMessage();
        }


    }
    //to delete the file by id--->

    @DeleteMapping("/del/{id}")
    public String  deleteById(@PathVariable int id , Principal principal) throws IOException {
        Optional<fileData> fileData = fileDataRepo.findById(id);


        fileData fileData1 = fileData.get();
        if (!fileData1.getUploadBy().equals(principal.getName()))
        {
            return "unauthorized";
        }
//        File file = new File(fileData1.getFilepath());
//        if (file.exists())
//        {
//            file.delete();
//        }
        Files.deleteIfExists(Paths.get(fileData1.getFilepath()));//best approach for deletion of file from DIR--->

        fileDataRepo.deleteById(id);

       return "deleted";
    }
    //search the file by name
    @GetMapping("{name}")
    public List<String> getFiles(@PathVariable String name)
    {
        List< fileData> l1 = fileDataRepo.findByuploadBy(name);
        return l1.stream()
                .map(fileData ::getFilename)
                .toList();
    }




}
