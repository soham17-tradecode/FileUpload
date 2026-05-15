package com.File.Distribution.FileSharing.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "register")
public class userRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  private  Integer i;
    @Column(unique = true)
    private String name;
    private String email;
    private String password;
    private String cpassword;


}
