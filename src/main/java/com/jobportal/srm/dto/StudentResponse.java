package com.jobportal.srm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StudentResponse {

    private Long id;
    private Long userId;
    private String name;
    private String email;
    private String branch;
    private Double cgpa;
    private String skills;
    private String resumeUrl;
}
