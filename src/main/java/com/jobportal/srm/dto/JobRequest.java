package com.jobportal.srm.dto;

import jakarta.validation.constraints.NotBlank; // Validation
import jakarta.validation.constraints.NotNull; // Validation
import lombok.Data;

import java.time.LocalDate;

@Data
public class JobRequest {

    @NotNull
    private Long companyId;
    // ID of company posting job

    @NotBlank
    private String title;
    // Job title

    @NotBlank
    private String description;
    // Job description

    private Double minCgpa;
    // Minimum CGPA

    private String requiredSkills;
    // Required skills

    @NotBlank
    private String location;
    // Job location

    @NotNull
    private LocalDate deadline;
    // Application deadline
}