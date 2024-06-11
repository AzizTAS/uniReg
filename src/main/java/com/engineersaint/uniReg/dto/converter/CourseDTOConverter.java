package com.engineersaint.uniReg.dto.converter;

import com.engineersaint.uniReg.dto.CourseDTO;
import com.engineersaint.uniReg.model.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseDTOConverter {

    public CourseDTO convert(Course course) {
        return new CourseDTO(
                course.getId(),
                course.getName()
        );
    }
}