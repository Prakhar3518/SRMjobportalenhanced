package com.jobportal.srm.entity;

import jakarta.persistence.*; // JPA annotations
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime; // For created_at

@Entity // Marks this class as database entity
@Table(name = "companies") // Maps to companies table
@Getter
@Setter
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Primary key, auto increment

    @Column(name = "company_name", nullable = false)
    private String companyName;
    // Maps to company_name column

    @Column(length = 255)
    private String description;
    // About company

    @Column(nullable = false)
    private Boolean approved;
    // Maps tinyint(1) to Boolean

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    // Timestamp when company was created
}