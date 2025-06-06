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
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {

        var usernamepassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamepassword);// autentica o usuario

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }



    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data) {// a ? indica que pode ser qualquer tipo de resposta
        if (this.userRepository.findByEmail(data.email()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already existss");
        }
        if(this.userRepository.findByEmail(String.valueOf(data.email())).isPresent() || data.email().trim().isEmpty()){
            return ResponseEntity.badRequest().body("Email cannot be null or empty");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());// BCryptPasswordEncoder é usado para criptografar a senha que é uma função do Spring Security
        var user = new User(data.email(), encryptedPassword, data.role());

        this.userRepository.save(user); 
                                                                                          

        return ResponseEntity.ok().build();
    }
}
