package com.File.Distribution.FileSharing.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table (name = "file")
public class fileData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String filename;
    private String filepath;
    private long filesize;

}
