package com.example.studentapp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.studentapp.repository.*;
import com.example.studentapp.entity.*;
import com.example.studentapp.dto.CourseDTO;
import com.example.studentapp.dto.EnrollmentDTO;
import com.example.studentapp.exception.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;

    public CourseService(CourseRepository courseRepository,
                         EnrollmentRepository enrollmentRepository,
                         StudentRepository studentRepository) {

        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
    }

    // Get all courses
    public List<CourseDTO> getCourses() {

        List<Course> courses = courseRepository.findAll();

        return courses.stream()
                .map(c -> new CourseDTO(
                        c.getId(),
                        c.getName(),
                        c.getDescription(),
                        c.getDuration(),
                        c.getAvailableSeats()
                ))
                .collect(Collectors.toList());
    }

    // Enroll student
    public String enroll(Long studentId, Long courseId) {

        if(enrollmentRepository.existsByStudent_IdAndCourse_Id(studentId, courseId)) {
            throw new CourseAlreadyEnrolledException("Student already enrolled in this course");
        }

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        if(course.getAvailableSeats() <= 0) {
            throw new SeatsOverException("No seats available for this course");
        }

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        enrollmentRepository.save(enrollment);

        course.setAvailableSeats(course.getAvailableSeats() - 1);
        courseRepository.save(course);

        return "Enrollment Successful";
    }

    // Unenroll student
    @Transactional
    public void unenroll(Long studentId, Long courseId) {

        enrollmentRepository.deleteByStudent_IdAndCourse_Id(studentId, courseId);

        Course course = courseRepository.findById(courseId).orElse(null);

        if(course != null) {
            course.setAvailableSeats(course.getAvailableSeats() + 1);
            courseRepository.save(course);
        }
    }

    // Get student enrollments
    public List<EnrollmentDTO> getStudentEnrollments(Long studentId) {

        List<Enrollment> enrollments = enrollmentRepository.findByStudent_Id(studentId);

        return enrollments.stream()
                .map(e -> new EnrollmentDTO(
                        e.getCourse().getId(),
                        e.getCourse().getName()
                ))
                .collect(Collectors.toList());
    }
}