package com.File.Distribution.FileSharing.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table (name = "shared")
public class sharedEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int fieldId;
    private String owner;
    private String sharedWith;

}
