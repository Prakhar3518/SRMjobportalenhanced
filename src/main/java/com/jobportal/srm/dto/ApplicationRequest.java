package com.jobportal.srm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationRequest {

    private Long studentId; // student applying
    private Long jobId; // job being applied to

}