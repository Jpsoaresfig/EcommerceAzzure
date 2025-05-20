package com.jp.azzure.services;

import com.jp.azzure.domain.user.User;
import com.jp.azzure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");//validation if email is null or empty
        }

        //Verifica se já existe um usuário com o mesmo email
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");//validation if email already exists
        }

        return userRepository.save(user);
    }


    public User updateUser(Long id, User user) {
        if (!userRepository.existsById(id)) {
            return null; 
        }
        user.setId(id); 
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
        
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null); 
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}