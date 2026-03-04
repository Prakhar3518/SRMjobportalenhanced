package com.jobportal.srm.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CompanyRequest {

    @NotBlank
    private String companyName;
    // Company name

    private String description;
    // Company description
}