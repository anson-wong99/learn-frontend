package com.example.learn_frontend.controller;

import com.example.learn_frontend.dto.AuthenticationRequestDto;
import com.example.learn_frontend.dto.AuthenticationResponseDto;
import com.example.learn_frontend.dto.RegisterRequestDto;
import com.example.learn_frontend.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/user/")
public class UserController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("register")
    public ResponseEntity<AuthenticationResponseDto> register(@RequestBody RegisterRequestDto registerRequestDto){
        return ResponseEntity.ok(authenticationService.register(registerRequestDto));
    }

    @PostMapping("authenticate")
    public ResponseEntity<AuthenticationResponseDto> authenticate(@RequestBody AuthenticationRequestDto authenticationRequestDto){
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequestDto));
    }
}
