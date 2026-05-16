package com.File.Distribution.FileSharing.controller;

import com.File.Distribution.FileSharing.model.fileData;
import com.File.Distribution.FileSharing.repo.fileDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Optional;

@RestController
public class downloadController {
    @Autowired
    fileDataRepo fileDataRepo;

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable int id, Principal principal)
    {
        try{

            Optional<fileData> optionalFileData = fileDataRepo.findById(id);
            if (optionalFileData.isEmpty())
            {
                ResponseEntity.notFound()
                        .build();
            }

            fileData fileData = optionalFileData.get();
            if (!fileData.getUploadBy().equals(principal.getName()))
            {
                ResponseEntity
                        .status(403)
                        .build();
            }
            Path path = Paths.get(fileData.getFilepath());
            Resource resource = new UrlResource(path.toUri());

            return ResponseEntity.ok()
//                    .contentType(
//                            MediaType.APPLICATION_OCTET_STREAM
//                    )//it is used as direct download no preview
                    .contentType(
                            MediaType.parseMediaType(
                                    Files.probeContentType(path)
                            )
                    )//it is good for browser preview
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename = \""
                    +fileData.getFilename()
                    +"\"")
                    .body(resource);

        }catch (Exception e)
        {
            return ResponseEntity.badRequest()
                    .build();
        }
    }

}
