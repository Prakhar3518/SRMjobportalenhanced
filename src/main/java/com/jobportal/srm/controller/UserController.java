package com.jobportal.srm.controller;

import com.jobportal.srm.dto.AuthResponse;
import com.jobportal.srm.dto.LoginRequest;
import com.jobportal.srm.dto.RegisterRequest;
import com.jobportal.srm.dto.UserResponse;
import com.jobportal.srm.entity.User;
import com.jobportal.srm.service.UserService;
import com.jobportal.srm.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    // Register - public
    @PostMapping("/register")
    public UserResponse register(@Valid @RequestBody RegisterRequest request) {
        User user = userService.registerUser(request);
        return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getRole());
    }

    // Login - returns JWT token
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        User user = userService.loginUser(request.getEmail(), request.getPassword());
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
        return new AuthResponse(token, user.getId(), user.getName(), user.getEmail(), user.getRole());
    }

    // Get all users - ADMIN only
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get user profile by ID - authenticated
    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // Change password - authenticated
    @PutMapping("/{id}/change-password")
    public String changePassword(
            @PathVariable Long id,
            @RequestParam String oldPassword,
            @RequestParam String newPassword
    ) {
        userService.changePassword(id, oldPassword, newPassword);
        return "Password changed successfully";
    }
}
