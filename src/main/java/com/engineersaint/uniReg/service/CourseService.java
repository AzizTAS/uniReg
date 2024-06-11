package com.engineersaint.uniReg.service;

import com.engineersaint.uniReg.dto.CourseDTO;
import com.engineersaint.uniReg.dto.converter.CourseDTOConverter;
import com.engineersaint.uniReg.model.Course;
import com.engineersaint.uniReg.repository.CourseRepository;
import org.springframework.stereotype.Service;


import java.util.Optional;


@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseDTOConverter courseDTOConverter;

    public CourseService(CourseRepository courseRepository, CourseDTOConverter courseDTOConverter) {
        this.courseRepository = courseRepository;
        this.courseDTOConverter = courseDTOConverter;
    }

    public CourseDTO createCourse(CourseDTO courseDTO){
        Course course =courseRepository.save( new Course(null, courseDTO.getName(),null));
        return courseDTOConverter.convert(course);
    }

    public Optional<CourseDTO> getCourseById(int id) {
        Optional<Course> course = courseRepository.findById(id);
        return course.map(courseDTOConverter::convert);
    }
}