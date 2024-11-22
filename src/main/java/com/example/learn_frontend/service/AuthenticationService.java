package com.example.learn_frontend.service;

import com.example.learn_frontend.dto.AuthenticationRequestDto;
import com.example.learn_frontend.dto.AuthenticationResponseDto;
import com.example.learn_frontend.dto.RegisterRequestDto;
import com.example.learn_frontend.entity.User;
import com.example.learn_frontend.enums.Role;
import com.example.learn_frontend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;


    public AuthenticationResponseDto register(RegisterRequestDto registerRequestDto){
        User user = new User();
        user.setUsername(registerRequestDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
        user.setEmail(registerRequestDto.getEmail());
        user.setRole(Role.ADMIN);

        user = userRepository.saveAndFlush(user);

        String jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponseDto(jwtToken);
    }


    public AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequestDto.getUsername(),
                        authenticationRequestDto.getPassword()
                )
        );

        User user = userRepository.findByUsername(authenticationRequestDto.getUsername())
                .orElseThrow();

        String jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponseDto(jwtToken);
    }
}
