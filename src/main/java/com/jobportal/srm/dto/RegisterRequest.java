package com.jobportal.srm.dto;

import com.jobportal.srm.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "Name is required can't be empty")
    private String name;

    @Email(message = "Invalid Format")
    @NotBlank(message = "Email is required can't be empty")
    private String email;

    @NotBlank(message = "Password is required can't be empty")
    @Size(min = 6, message = "Password must be at least 60 characters")
    private String password;
    private Role role;
}