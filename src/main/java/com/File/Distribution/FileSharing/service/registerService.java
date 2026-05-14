package com.File.Distribution.FileSharing.service;

import com.File.Distribution.FileSharing.model.userRegister;
import com.File.Distribution.FileSharing.repo.userRegisterRepo;
import org.springframework.stereotype.Service;

@Service
public class registerService {
    userRegisterRepo userRegisterRepo;
    public void getData(userRegister userRegister)
    {
        userRegisterRepo.save(userRegister);
    }

}
