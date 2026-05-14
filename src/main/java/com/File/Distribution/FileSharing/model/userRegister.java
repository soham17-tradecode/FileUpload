package com.File.Distribution.FileSharing.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "register")
public class userRegister {
    @Id
    int i;
    String name;
    String email;
    String password;
    String cpassword;


}
