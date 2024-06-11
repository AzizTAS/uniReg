package com.engineersaint.uniReg.repository;

import com.engineersaint.uniReg.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
