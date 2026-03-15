package com.example.studentapp.controller;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

import com.example.studentapp.service.StudentService;
import com.example.studentapp.service.CourseService;

import com.example.studentapp.dto.StudentRegisterDTO;
import com.example.studentapp.dto.LoginDTO;
import com.example.studentapp.dto.StudentResponseDTO;
import com.example.studentapp.dto.EnrollmentDTO;

@RestController
@RequestMapping("/api/students")
@CrossOrigin
public class StudentController {

    private final StudentService studentService;
    private final CourseService courseService;

    public StudentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    // Register
    @PostMapping("/register")
    public StudentResponseDTO register(@Valid @RequestBody StudentRegisterDTO dto) {
        return studentService.register(dto);
    }

    // Login
    @PostMapping("/login")
    public StudentResponseDTO login(@RequestBody LoginDTO dto) {
        return studentService.login(dto);
    }

    // Get student enrollments
    @GetMapping("/{id}/enrollments")
    public List<EnrollmentDTO> getEnrollments(@PathVariable Long id) {
        return courseService.getStudentEnrollments(id);
    }
}