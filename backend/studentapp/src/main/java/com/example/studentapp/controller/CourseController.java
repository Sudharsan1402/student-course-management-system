package com.example.studentapp.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.studentapp.service.CourseService;
import com.example.studentapp.dto.CourseDTO;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // Get all courses
    @GetMapping("/courses")
    public List<CourseDTO> getCourses() {
        return courseService.getCourses();
    }

    // Enroll student
    @PostMapping("/enroll/{courseId}")
    public String enroll(@PathVariable Long courseId, @RequestParam Long studentId) {
        return courseService.enroll(studentId, courseId);
    }

    // Unenroll student
    @DeleteMapping("/enroll/{courseId}")
    public String unenroll(@PathVariable Long courseId, @RequestParam Long studentId) {
        courseService.unenroll(studentId, courseId);
        return "Unenrolled Successfully";
    }
}