package com.jp.azzure.infra.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpsSecurity) throws Exception {

        return httpsSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))//session stateless
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll() //login
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll() //register
                        .requestMatchers(HttpMethod.POST, "/products").hasRole("ADMIN") //only admin can create products
                        .requestMatchers(HttpMethod.PUT, "/products").hasRole("ADMIN") //only admin can update products
                        .requestMatchers(HttpMethod.DELETE, "/products").hasRole("ADMIN") //only admin can delete products
                        .requestMatchers(HttpMethod.GET, "/products").permitAll() //all users can see products
                )
                        .build();   
    }   
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {// encrypt password
        return new BCryptPasswordEncoder();
    }
    
}
