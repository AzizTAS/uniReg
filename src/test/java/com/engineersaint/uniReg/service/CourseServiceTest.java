package com.engineersaint.uniReg.service;

import com.engineersaint.uniReg.dto.CourseDTO;
import com.engineersaint.uniReg.dto.converter.CourseDTOConverter;
import com.engineersaint.uniReg.model.Course;
import com.engineersaint.uniReg.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;

public class CourseServiceTest {

    private CourseRepository courseRepository;
    private CourseDTOConverter courseDTOConverter;
    private CourseService courseService;

    @BeforeEach
    public void setUp() {
        courseRepository = mock(CourseRepository.class);
        courseDTOConverter = mock(CourseDTOConverter.class);
        courseService = new CourseService(courseRepository, courseDTOConverter);
    }

    @Test
    public void testCreateCourse() {
        CourseDTO courseDTO = new CourseDTO(null, "Computer Engineering");
        Course course = new Course(null, "Computer Engineering", null);
        Course savedCourse = new Course(1, "Computer Engineering", null);

        Mockito.when(courseRepository.save(course)).thenReturn(savedCourse);
        Mockito.when(courseDTOConverter.convert(savedCourse)).thenReturn(new CourseDTO(1, "Computer Engineering"));

        CourseDTO result = courseService.createCourse(courseDTO);

        assertEquals(new CourseDTO(1, "Computer Engineering"), result);
    }

    @Test
    public void testGetCourseById_whenCourseExists_shouldReturnCourse() {
        Course course = new Course(1, "Computer Engineering", null);
        CourseDTO courseDTO = new CourseDTO(1, "Computer Engineering");

        Mockito.when(courseRepository.findById(1)).thenReturn(Optional.of(course));
        Mockito.when(courseDTOConverter.convert(course)).thenReturn(courseDTO);

        Optional<CourseDTO> result = courseService.getCourseById(1);

        assertEquals(Optional.of(courseDTO), result);
    }

    @Test
    public void testGetCourseById_whenCourseDoesNotExist_shouldReturnEmpty() {
        Mockito.when(courseRepository.findById(1)).thenReturn(Optional.empty());

        Optional<CourseDTO> result = courseService.getCourseById(1);

        assertEquals(Optional.empty(), result);
        verifyNoInteractions(courseDTOConverter);
    }
}