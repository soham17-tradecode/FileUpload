package com.File.Distribution.FileSharing.service;

import com.File.Distribution.FileSharing.model.userRegister;
import com.File.Distribution.FileSharing.repo.userRegisterRepo;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class registerService {
    @Autowired
    userRegisterRepo userRegisterRepo;
    public void getData( userRegister userRegister)
    {
        userRegisterRepo.save(userRegister);
    }

    public userRegister findByName(String name)
    {
        return userRegisterRepo.findByName(name);
    }
//    public userRegister findByPassword(String password)
//    {
//        return userRegisterRepo.findByName(password);
//    }

    public userRegister findByNameAndPassword(String name,String password)
    {
        return userRegisterRepo.findByNameAndPassword(name,password);
    }

}
