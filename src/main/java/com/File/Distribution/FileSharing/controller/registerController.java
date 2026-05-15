package com.File.Distribution.FileSharing.controller;

import com.File.Distribution.FileSharing.model.userRegister;
import com.File.Distribution.FileSharing.repo.userRegisterRepo;
import com.File.Distribution.FileSharing.service.registerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class registerController {

    @Autowired
    registerService registerService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    userRegisterRepo userRegisterRepo;


    @PostMapping ("/register")
    public String save(@RequestBody userRegister userRegister)
    {


//        userRegisterRepo.save(userRegister);

        if (userRegister.getPassword().equals(userRegister.getCpassword()))
        {
            userRegister.setPassword(bCryptPasswordEncoder.encode(userRegister.getPassword()));
            registerService.getData(userRegister);
            return "registered";
        }
        else {
            return "password not matched";
        }

    }

    @GetMapping("/get/{name}")
    public String getName(@PathVariable String name) {

        userRegister username = registerService.findByName(name);
      
        return username.getName();
    }
}
