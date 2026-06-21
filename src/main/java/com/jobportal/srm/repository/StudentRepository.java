package com.jobportal.srm.repository;

import com.jobportal.srm.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByUserId(Long userId); // find student profile by user ID
    boolean existsByUserId(Long userId);
}
