package com.engineersaint.uniReg;

import com.engineersaint.uniReg.model.Course;
import com.engineersaint.uniReg.model.Department;
import com.engineersaint.uniReg.model.Student;
import com.engineersaint.uniReg.repository.CourseRepository;
import com.engineersaint.uniReg.repository.DepartmentRepository;
import com.engineersaint.uniReg.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UniRegApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniRegApplication.class, args);
    }


}
