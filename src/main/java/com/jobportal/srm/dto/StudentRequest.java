package com.jobportal.srm.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotBlank(message = "Branch is required")
    private String branch;

    @NotNull(message = "CGPA is required")
    @DecimalMin(value = "0.0", message = "CGPA must be at least 0.0")
    @DecimalMax(value = "10.0", message = "CGPA cannot exceed 10.0")
    private Double cgpa;

    private String skills;    // comma-separated e.g. "Java, Spring Boot, MySQL"

    private String resumeUrl; // link to uploaded resume
}
