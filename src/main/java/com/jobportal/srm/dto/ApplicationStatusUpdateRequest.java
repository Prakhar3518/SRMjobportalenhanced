package com.jobportal.srm.dto;

import com.jobportal.srm.enums.ApplicationStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationStatusUpdateRequest {

    private ApplicationStatus status;
    // new status: SHORTLISTED or REJECTED

}