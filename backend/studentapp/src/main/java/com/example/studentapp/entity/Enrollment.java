package com.example.studentapp.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="ENROLLMENT",
       uniqueConstraints=@UniqueConstraint(columnNames={"STUDENT_ID","COURSE_ID"}))
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="STUDENT_ID")
    private Student student;

    @ManyToOne
    @JoinColumn(name="COURSE_ID")
    private Course course;

    @Column(name="ENROLLED_AT")
    private LocalDateTime enrolledAt = LocalDateTime.now();

    public Enrollment(){}

    public Long getId(){ return id; }

    public Student getStudent(){ return student; }
    public void setStudent(Student student){ this.student = student; }

    public Course getCourse(){ return course; }
    public void setCourse(Course course){ this.course = course; }

    public LocalDateTime getEnrolledAt(){ return enrolledAt; }
}