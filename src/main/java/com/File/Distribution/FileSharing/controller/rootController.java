package com.File.Distribution.FileSharing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class rootController {
    @GetMapping("/")
    public String getPage() {


        return "f";
    }
}
