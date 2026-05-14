package com.File.Distribution.FileSharing.controller;

import com.File.Distribution.FileSharing.model.userRegister;
import com.File.Distribution.FileSharing.service.registerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class registerController {

    @Autowired
    registerService registerService;

    @PostMapping ("/save")
    public void save(userRegister userRegister)
    {
        registerService.getData(userRegister);
    }
}
