package com.example.studentapp.service;

import org.springframework.stereotype.Service;

import com.example.studentapp.repository.StudentRepository;
import com.example.studentapp.entity.Student;
import com.example.studentapp.dto.StudentRegisterDTO;
import com.example.studentapp.dto.LoginDTO;
import com.example.studentapp.dto.StudentResponseDTO;
import com.example.studentapp.exception.EmailAlreadyExistsException;
import com.example.studentapp.exception.InvalidCredentialsException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Register Student
    public StudentResponseDTO register(StudentRegisterDTO dto) {

        if(studentRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already registered");
        }

        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setPassword(encoder.encode(dto.getPassword()));

        Student saved = studentRepository.save(student);

        return new StudentResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getEmail()
        );
    }

    // Login Student
    public StudentResponseDTO login(LoginDTO dto) {

        Student student = studentRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        if(!encoder.matches(dto.getPassword(), student.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        return new StudentResponseDTO(
                student.getId(),
                student.getName(),
                student.getEmail()
        );
    }
}