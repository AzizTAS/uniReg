package com.engineersaint.uniReg.repository;

import com.engineersaint.uniReg.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
