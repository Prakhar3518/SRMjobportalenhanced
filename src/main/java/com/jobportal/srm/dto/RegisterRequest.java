package com.jobportal.srm.dto;

import com.jobportal.srm.enums.Role;
import lombok.Data;

@Data
public class RegisterRequest {

    private String name;
    private String email;
    private String password;
    private Role role;
}