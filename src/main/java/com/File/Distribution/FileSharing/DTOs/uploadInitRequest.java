package com.File.Distribution.FileSharing.DTOs;

import lombok.Data;

@Data

public class uploadInitRequest {
    private String filename;
    private Long fileSize;
    private Integer totalChunks;
}
