package com.File.Distribution.FileSharing.security;

import com.File.Distribution.FileSharing.model.userRegister;
import com.File.Distribution.FileSharing.service.registerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static ch.qos.logback.core.joran.spi.ConsoleTarget.findByName;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    registerService registerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        userRegister user = registerService.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("not found user");

        }
        return User.builder()
                .username(user.getName())
                .password(user.getPassword())

                .build();
    }
}
