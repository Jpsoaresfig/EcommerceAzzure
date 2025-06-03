package com.jp.azzure.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jp.azzure.repository.UserRepository;

//fazer consultas para o Spring Security
@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository; //injected repository

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //verify if user exists

        
        
       return userRepository.findByEmail(username)
               .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
    }
    
}
