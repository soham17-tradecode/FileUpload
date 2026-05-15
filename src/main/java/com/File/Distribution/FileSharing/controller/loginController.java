package com.File.Distribution.FileSharing.controller;

import com.File.Distribution.FileSharing.model.login;
import com.File.Distribution.FileSharing.model.userRegister;
import com.File.Distribution.FileSharing.repo.userRegisterRepo;
import com.File.Distribution.FileSharing.service.registerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class loginController {

    userRegister userRegister;

    @Autowired
    registerService registerService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/login")
    public String login( @RequestBody login login)
    {


        userRegister username = registerService.findByName(login.getName());
        if (username==null)
        {
            return "user not found";
        }

        if (bCryptPasswordEncoder.matches(login.getPassword(),username.getPassword())){
            return "logged in success";
        }else
        {
            return "failed logged in";

        }

//        userRegister user = registerService.findByNameAndPassword(name,pass);







    }

}
