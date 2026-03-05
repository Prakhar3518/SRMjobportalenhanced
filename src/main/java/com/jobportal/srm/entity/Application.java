package com.jobportal.srm.entity;

import com.jobportal.srm.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity // marks this as a database entity
@Getter
@Setter
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // primary key

    @ManyToOne // many applications belong to one student
    @JoinColumn(name = "student_id")
    private User student; // student applying

    @ManyToOne // many applications belong to one job
    @JoinColumn(name = "job_id")
    private Job job; // job applied to

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status; // APPLIED / SHORTLISTED / REJECTED

    private LocalDateTime appliedAt; // timestamp of application

}