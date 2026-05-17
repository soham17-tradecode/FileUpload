package com.File.Distribution.FileSharing.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table (name="metadata")
public class fileMetaData {
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private  String fileId;
    private String fileName;
    private Long fileSize;
    private Integer totalChunks;
    private String uploadBy;
    private Boolean uploadComplete;
    private String storagePath;

}
