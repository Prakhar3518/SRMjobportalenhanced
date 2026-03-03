package com.jobportal.srm.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "jobs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Primary key, auto-increment



    //private Long companyId;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    // Foreign key reference

    @Column(nullable = false)
    private String title;
    // Job title

    @Column(length = 2000, nullable = false)
    private String description;
    // Detailed description

    @Column(name = "min_cgpa")
    private Double minCgpa;
    // Minimum CGPA required

    @Column(name = "required_skills")
    private String requiredSkills;
    // Required skills (comma separated for now)

    @Column(nullable = false)
    private String location;
    // Job location

    @Column(nullable = false)
    private LocalDate deadline;
    // Last date to apply

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    // Timestamp when job was created

}