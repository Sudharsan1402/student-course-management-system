package com.example.studentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.studentapp.entity.Course;

public interface CourseRepository extends JpaRepository<Course,Long>{

}
