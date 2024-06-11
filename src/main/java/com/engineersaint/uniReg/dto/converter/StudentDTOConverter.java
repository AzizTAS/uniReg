package com.engineersaint.uniReg.dto.converter;

import com.engineersaint.uniReg.dto.StudentDTO;
import com.engineersaint.uniReg.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentDTOConverter {

    public StudentDTO convert(Student student) {
        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getSurname(),
                student.getEmail(),
                student.getDepartment().getId(),
                student.getCourse().getId()
        );
    }
}