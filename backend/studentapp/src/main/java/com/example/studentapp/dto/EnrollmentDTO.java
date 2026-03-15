package com.example.studentapp.dto;

public class EnrollmentDTO {

    private Long courseId;
    private String courseName;

    public EnrollmentDTO(Long courseId,String courseName){
        this.courseId=courseId;
        this.courseName=courseName;
    }

    public Long getCourseId(){ return courseId; }
    public String getCourseName(){ return courseName; }
}