package com.jobportal.srm.dto;

import com.jobportal.srm.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ApplicationResponse {

    private Long id; // application id

    private Long studentId; // student id

    private Long jobId; // job id

    private ApplicationStatus status; // application status

    private LocalDateTime appliedAt; // when applied

}