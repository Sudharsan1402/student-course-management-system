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


```


---

## 🚀 Features

| Feature | Status |
|--------|--------|
| Student Registration | ✅ Mandatory |
| Student Login | ✅ Mandatory |
| View Courses | ✅ Mandatory |
| Enroll in Course | ✅ Mandatory |
| View My Enrollments | ✅ Mandatory |
| Unenroll from Course | ✅ Mandatory |
| Course Seed Data | ✅ Mandatory |

---

## 🔐 Security

- Password stored using **BCrypt hashing**
- CORS configuration enabled
- Global exception handling with meaningful error messages

---

## ✅ Validation Rules

| Rule | Required |
|------|----------|
| Name: alphabets only | ✅ Mandatory |
| Email: valid & unique | ✅ Mandatory |
| Password: min 6 chars, alphanumeric | ✅ Mandatory |
| No empty fields | ✅ Mandatory |
| No duplicate enrollment | ✅ Mandatory |
| Seats must not go below zero | ✅ Mandatory |
| Unenroll restores seat count | ✅ Mandatory |

---

## 📌 Functional Requirements

### 1️⃣ Register Student

#### Input Fields

| Field | Validation |
|------|-----------|
| Name | Alphabets only, required |
| Email | Valid format, unique, required |
| Password | Minimum 6 characters, alphanumeric |

#### Logic

- Store student in Oracle DB  
- Password stored as BCrypt hash  

---

### 2️⃣ Login

- Validate email & password  
- Return success or failure response  
- No JWT (simple session via response/localStorage)  

---

### 3️⃣ View Available Courses

Display all pre-loaded courses:

| Column | Description |
|--------|------------|
| Course ID | Unique ID |
| Course Name | Name of course |
| Description | Course details |
| Duration | Example: 30 Hours |
| Seats Available | Remaining seats |

---

### 4️⃣ Enroll in Course

#### Flow

1. Check if seats available  
2. Check duplicate enrollment  
3. Insert enrollment record  
4. Decrement available seats  

---

### 5️⃣ View My Enrollments

- Show all enrolled courses  
- Provide **Unenroll button**

#### Unenroll Flow

1. Confirm action  
2. Delete enrollment record  
3. Increment available seats  
4. Refresh UI  

---

## 🗂️ ER Diagram

```mermaid
erDiagram

    STUDENT {
        Long id PK
        String name
        String email
        String password
    }

    COURSE {
        Long id PK
        String name
        String description
        String duration
        int totalSeats
        int availableSeats
    }

    ENROLLMENT {
        Long id PK
        Long student_id FK
        Long course_id FK
    }

    STUDENT ||--o{ ENROLLMENT : enrolls
    COURSE ||--o{ ENROLLMENT : contains



## 🗄️ Database Seed Data

```sql
INSERT INTO COURSE (ID, AVAILABLE_SEATS, DESCRIPTION, DURATION, NAME, TOTAL_SEATS) VALUES
(1, 29, 'Learn core Java concepts from scratch.', '40 Hours', 'Java Programming', 30),
(2, 25, 'Introduction to Spring Boot framework.', '30 Hours', 'Spring Boot Basics', 25),
(3, 19, 'Fundamentals of Oracle SQL and PL/SQL.', '20 Hours', 'Oracle SQL', 20),
(4, 39, 'Web design using HTML5 and CSS3.', '15 Hours', 'HTML & CSS', 40),
(5, 34, 'Vanilla JavaScript for beginners.', '25 Hours', 'JavaScript Essentials', 35),
(6, 29, 'Learn Python programming from basics to advanced.', '35 Hours', 'Python Programming', 30),
(7, 24, 'Understanding data structures and algorithms.', '45 Hours', 'Data Structures', 25),
(8, 28, 'Frontend development using React JS framework.', '30 Hours', 'React JS', 30),
(9, 34, 'Concepts of relational databases and normalization.', '40 Hours', 'Database Management Systems', 35),
(10, 20, 'Building RESTful APIs using Spring Boot.', '30 Hours', 'REST API Development', 20);

---

```

## 🔗 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/students/register | Register student |
| POST | /api/students/login | Login |
| GET | /api/courses | View courses |
| POST | /api/enroll/{courseId} | Enroll in a course |
| DELETE | /api/enroll/{courseId} | Unenroll from a course |
| GET | /api/students/{id}/enrollments | View enrollments |

---

## 💻 Frontend Pages

| Page | Description |
|------|-------------|
| Login Page | Student login |
| Registration Page | Signup with validation |
| Dashboard | Welcome screen with navigation |
| Course List | View and enroll in courses |
| My Enrollments | View enrolled courses and unenroll |

---



