package com.File.Distribution.FileSharing.service;

import com.File.Distribution.FileSharing.DTOs.uploadInitRequest;
import com.File.Distribution.FileSharing.model.fileMetaData;
import com.File.Distribution.FileSharing.repo.fileMetaRepo;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.TE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class chunkUploadService {
    @Autowired
    private fileMetaRepo fileMetaRepo;
    private final String TEMP_DIR = "temp/";
    private final String FINAL_DIR = "uploads/";




    public String initialUpload(uploadInitRequest request, Principal principal)
    {
        String fileId = UUID.randomUUID().toString();

        fileMetaData fileMetaData = com.File.Distribution.FileSharing.model.fileMetaData.builder()
                .fileId(fileId)
                .fileSize(request.getFileSize())
                .fileName(request.getFilename())
                .uploadComplete(false)
                .uploadBy(principal.getName())
                .totalChunks(request.getTotalChunks())
                .build();

        File file = new File(TEMP_DIR+fileId);
        if (!file.exists())
        {
            file.mkdir();

        } fileMetaRepo.save(fileMetaData);
        return fileId;
    }



public void uploadChunk(
        String fileId,
        Integer chunkIndex,
        MultipartFile file
) {

    try {

        String basePath =
                System.getProperty("user.dir");

        File dir = new File(
                basePath
                        + File.separator
                        + "temp"
                        + File.separator
                        + fileId
        );

        if (!dir.exists()) {

            boolean created = dir.mkdirs();

            System.out.println(
                    "DIR CREATED: "
                            + created
            );
        }

        File chunkFile = new File(
                dir,
                "chunk_" + chunkIndex
        );

        file.transferTo(chunkFile);

        System.out.println(
                "CHUNK SAVED: "
                        + chunkFile.getAbsolutePath()
        );

    } catch (Exception e) {

        e.printStackTrace();
    }
}

public void completeUpload(String fileId)
        throws Exception {

    fileMetaData fileMetaData =
            fileMetaRepo.findByFileId(fileId);

    if (fileMetaData == null) {

        throw new RuntimeException(
                "File metadata not found"
        );
    }

    File uploadDir = new File(FINAL_DIR);

    if (!uploadDir.exists()) {
        uploadDir.mkdirs();
    }

    File finalFile = new File(
            FINAL_DIR + fileMetaData.getFileName()
    );

    try (
            FileOutputStream fos =
                    new FileOutputStream(finalFile)
    ) {

        for (
                int i = 0;
                i < fileMetaData.getTotalChunks();
                i++
        ) {

            String basePath =
                    System.getProperty("user.dir");

            File chunkFile = new File(
                    basePath
                            + File.separator
                            + "temp"
                            + File.separator
                            + fileId,
                    "chunk_" + i
            );



            fos.write(
                    Files.readAllBytes(
                            chunkFile.toPath()
                    )
            );

            chunkFile.delete();
        }
    }

    File tempFolder = new File(
            TEMP_DIR + fileId
    );

    tempFolder.delete();

    fileMetaData.setUploadComplete(true);

    fileMetaData.setStoragePath(
            finalFile.getAbsolutePath()
    );

    fileMetaRepo.save(fileMetaData);
}









}
