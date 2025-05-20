package com.jp.azzure.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jp.azzure.domain.user.AuthenticationDTO;
import com.jp.azzure.domain.user.LoginResponseDTO;
import com.jp.azzure.domain.user.RegisterDTO;
import com.jp.azzure.domain.user.User;
import com.jp.azzure.infra.Security.TokenService;
import com.jp.azzure.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data) {

        var usernamepassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamepassword);// autentica o usuario

        var token = tokenService.generateToken((User) auth.getPrincipal());// gera o token

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }



    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data) {
        if (this.userRepository.findByEmail(data.email()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        var user = new User(data.email(), encryptedPassword, data.role());

        this.userRepository.save(new User(data.email(), encryptedPassword, data.role())); 
                                                                                          

        return ResponseEntity.ok().build();
    }
}
