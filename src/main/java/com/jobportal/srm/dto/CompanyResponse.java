package com.jobportal.srm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CompanyResponse {

    private Long id;
    // Company ID

    private String companyName;
    // Name

    private String description;
    // Description

    private Boolean approved;
    // Approval status

    private LocalDateTime createdAt;
    // Creation time
}