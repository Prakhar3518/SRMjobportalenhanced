package com.jobportal.srm.dto;

import com.jobportal.srm.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthResponse {

    private String token;   // JWT token
    private Long id;
    private String name;
    private String email;
    private Role role;
}
