package com.example.studentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.studentapp.entity.Enrollment;
import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment,Long>{
	
	List<Enrollment> findByStudent_Id(Long studentId);
	boolean existsByStudent_IdAndCourse_Id(Long studentId,Long courseId);
	void deleteByStudent_IdAndCourse_Id(Long studentId,Long courseId);

	
}
