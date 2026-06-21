package com.jobportal.srm.controller;

import com.jobportal.srm.dto.StudentRequest;
import com.jobportal.srm.dto.StudentResponse;
import com.jobportal.srm.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Create student profile - STUDENT only
    @PostMapping
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<StudentResponse> createProfile(@Valid @RequestBody StudentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentService.createProfile(request));
    }

    // Get own profile by user ID - STUDENT or ADMIN
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('STUDENT', 'ADMIN')")
    public ResponseEntity<StudentResponse> getProfileByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(studentService.getProfileByUserId(userId));
    }

    // Get profile by student record ID - COMPANY or ADMIN (recruiter viewing applicant)
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('COMPANY', 'ADMIN', 'STUDENT')")
    public ResponseEntity<StudentResponse> getProfileById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getProfileById(id));
    }

    // Get all student profiles - ADMIN or COMPANY
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'COMPANY')")
    public ResponseEntity<List<StudentResponse>> getAllProfiles() {
        return ResponseEntity.ok(studentService.getAllProfiles());
    }

    // Update own profile - STUDENT only
    @PutMapping("/user/{userId}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<StudentResponse> updateProfile(
            @PathVariable Long userId,
            @Valid @RequestBody StudentRequest request
    ) {
        return ResponseEntity.ok(studentService.updateProfile(userId, request));
    }
}
