package com.jobportal.srm.controller;

import com.jobportal.srm.dto.LoginRequest;
import com.jobportal.srm.dto.RegisterRequest;
import com.jobportal.srm.dto.UserResponse;
import com.jobportal.srm.entity.User;
import com.jobportal.srm.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponse register(@RequestBody RegisterRequest request) {

        User user = userService.registerUser(request);

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }
    /*old register without dto:
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    */

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    //User login:
    @PostMapping("/login")
    public UserResponse login(@RequestBody LoginRequest request) {

        User user = userService.loginUser(
                request.getEmail(), //to receive input from user
                request.getPassword()
        );

        return new UserResponse( // returns the user info
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }

}