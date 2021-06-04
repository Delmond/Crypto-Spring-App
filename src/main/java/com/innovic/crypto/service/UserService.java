package com.innovic.crypto.service;

import java.util.Optional;

import com.innovic.crypto.model.User;
import com.innovic.crypto.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getById(Long userId){
        Optional<User> user = userRepository.findById(userId);
        return user.orElse(null);
    }
    public boolean checkIfExistsById(Long id){
        return userRepository.existsById(id);
    }
    public boolean checkIfExistsByUsernameOrEmail(String username, String email){
        return userRepository.findOneByUsernameOrEmail(username, email).isPresent();
    }

    public User createUser(String username, String email, String rawPassword){
        User user = new User(username, email, passwordEncoder.encode(rawPassword));
        return userRepository.save(user);
    }

}
