package com.jobportal.srm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class JobResponse {

    private Long id;
    // Job ID

    private String companyName;
    // Company name instead of full object

    private String title;
    // Job title

    private String description;
    // Description

    private Double minCgpa;
    // Min CGPA

    private String requiredSkills;
    // Skills

    private String location;
    // Location

    private LocalDate deadline;
    // Deadline

    private LocalDateTime createdAt;
    // Creation timestamp
}