# 🎓 Student Course Management System

A full-stack web application that allows students to register, login, view courses, enroll, and manage their enrollments.

---

## 📌 Project Overview

This system provides a simple course enrollment platform where:

- Students can register and login securely  
- View available courses  
- Enroll in courses (with seat validation)  
- View and manage their enrollments  
- Unenroll from courses  

---

## 🛠️ Tech Stack

### Backend
- Java  
- Spring Boot  
- Spring Data JPA  
- Hibernate  
- Oracle DB  
- BCrypt (Password Encryption)  

### Frontend
- HTML  
- CSS  
- JavaScript (Vanilla JS)  

### Tools
- Bruno (API Testing)  
- Git & GitHub  

---

## 📁 Project Structure

```bash
student-course-management-system
│
├── backend
│   ├── src/main/java/com/example/studentapp
│   │
│   │   ├── controller
│   │   │   ├── StudentController.java
│   │   │   └── CourseController.java
│   │   │
│   │   ├── service
│   │   │   ├── StudentService.java
│   │   │   └── CourseService.java
│   │   │
│   │   ├── repository
│   │   │   ├── StudentRepository.java
│   │   │   ├── CourseRepository.java
│   │   │   └── EnrollmentRepository.java
│   │   │
│   │   ├── entity
│   │   │   ├── Student.java
│   │   │   ├── Course.java
│   │   │   └── Enrollment.java
│   │   │
│   │   ├── dto
│   │   │   ├── StudentRequestDTO.java
│   │   │   ├── StudentResponseDTO.java
│   │   │   ├── LoginRequestDTO.java
│   │   │   ├── CourseDTO.java
│   │   │   └── EnrollmentDTO.java
│   │   │
│   │   ├── exception
│   │   │   ├── GlobalExceptionHandler.java
│   │   │   ├── EmailAlreadyExistsException.java
│   │   │   ├── InvalidCredentialsException.java
│   │   │   ├── CourseAlreadyEnrolledException.java
│   │   │   ├── SeatsOverException.java
│   │   │   
│   │   │
│   │   ├── config
│   │   │   └── CorsConfig.java
│   │   │
│   │   └── StudentAppApplication.java
│   │
│   └── src/main/resources
│       ├── application.properties
│       └── data.sql
│
├── frontend
│   ├── login.html
│   ├── register.html
│   ├── dashboard.html
│   ├── courses.html
│   ├── enrollments.html
│   ├── script.js
│   └── style.css
│

