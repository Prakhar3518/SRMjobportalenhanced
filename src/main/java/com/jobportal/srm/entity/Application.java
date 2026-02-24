package com.jobportal.srm.entity;


import com.jobportal.srm.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
            name = "applications",
            uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "job_id"})
)
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class Application {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "student_id", nullable = false)
        private Student student;

        @ManyToOne
        @JoinColumn(name = "job_id", nullable = false)
        private Job job;

        @Enumerated(EnumType.STRING)
        private ApplicationStatus status = ApplicationStatus.APPLIED;

        private LocalDateTime appliedAt = LocalDateTime.now();
    }

