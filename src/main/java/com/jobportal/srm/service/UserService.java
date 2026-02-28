package com.jobportal.srm.service;

import com.jobportal.srm.dto.RegisterRequest;
import com.jobportal.srm.entity.User;
import com.jobportal.srm.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ========================
    // USER REGISTER
    // ========================
    public User registerUser(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // hashed password
        user.setRole(request.getRole());

        return userRepository.save(user);
    }

    /*  old register version without dto
    public User registerUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        return userRepository.save(user);
    }
    */


    // ========================
    // GET ALL USERS
    // ========================
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ========================
    // USER LOGIN
    // ========================
    public User loginUser(String email, String password) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) { //Bcryted password
            throw new RuntimeException("Invalid password");
        }

        return user;
    }
}