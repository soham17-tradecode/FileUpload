package com.File.Distribution.FileSharing.controller;

import com.File.Distribution.FileSharing.DTOs.uploadInitRequest;
import com.File.Distribution.FileSharing.DTOs.uploadInitResponse;
import com.File.Distribution.FileSharing.service.chunkUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
@CrossOrigin("*")
public class chunkUploadController {

    @Autowired
    chunkUploadService uploadService;


    @PostMapping("/upload/init")
    public ResponseEntity<Map<String, String>> uploadInit(@RequestBody uploadInitRequest request, Principal principal)

    {

        String fileId = uploadService.initialUpload(request,principal);
          return ResponseEntity.ok(
            Map.of(
                    "fileId",
                    fileId
            )
    );

    }


    @PostMapping("/upload/chunk")
    public ResponseEntity<String>
    uploadChunk(

            @RequestParam("fileId")
            String fileId,

            @RequestParam("chunkIndex")
            Integer chunkIndex,

            @RequestParam("file")
            MultipartFile file
    ) {

        System.out.println(
                "CHUNK API HIT"
        );

        uploadService.uploadChunk(
                fileId,
                chunkIndex,
                file
        );

        return ResponseEntity.ok(
                "Chunk uploaded"
        );
    }


    @PostMapping("/upload/complete/{fileId}")
    public ResponseEntity<String> uploadComplete(@PathVariable String fileId) throws Exception
    {

        System.out.println(
                "BACKEND COMPLETE FILE ID: "
                        + fileId
        );


        uploadService.completeUpload(fileId);
        return ResponseEntity.ok("upload completed");
    }





}
