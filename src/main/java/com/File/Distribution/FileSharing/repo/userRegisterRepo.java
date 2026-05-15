package com.File.Distribution.FileSharing.repo;

import com.File.Distribution.FileSharing.model.userRegister;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRegisterRepo extends JpaRepository<userRegister,Integer> {

    userRegister findByName(String name);
//    userRegister findByPassword(String password);
    userRegister findByNameAndPassword(String name,String password);
}
