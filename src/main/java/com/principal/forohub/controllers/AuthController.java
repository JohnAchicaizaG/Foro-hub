package com.principal.forohub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.principal.forohub.config.UserDetailsServiceImpl;

@RestController
@RequestMapping("/auth")
public class AuthController  {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO login){
        return new ResponseEntity<>(userDetailsServiceImpl.login(login), HttpStatus.OK);
    }

}
