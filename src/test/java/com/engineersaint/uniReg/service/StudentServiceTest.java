package com.engineersaint.uniReg.service;

import com.engineersaint.uniReg.dto.StudentDTO;
import com.engineersaint.uniReg.dto.converter.StudentDTOConverter;
import com.engineersaint.uniReg.model.Course;
import com.engineersaint.uniReg.model.Department;
import com.engineersaint.uniReg.model.Student;
import com.engineersaint.uniReg.repository.CourseRepository;
import com.engineersaint.uniReg.repository.DepartmentRepository;
import com.engineersaint.uniReg.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

public class StudentServiceTest {

    private StudentRepository studentRepository;
    private DepartmentRepository departmentRepository;
    private CourseRepository courseRepository;
    private StudentDTOConverter studentDTOConverter;
    private StudentService studentService;

    @BeforeEach
    public void setUp() {
        studentRepository = mock(StudentRepository.class);
        departmentRepository = mock(DepartmentRepository.class);
        courseRepository = mock(CourseRepository.class);
        studentDTOConverter = mock(StudentDTOConverter.class);
        studentService = new StudentService(studentRepository, departmentRepository, courseRepository, studentDTOConverter);
    }

    @Test
    public void testCreateStudent() {
        Department department = new Department(1, "Engineering", null);
        Course course = new Course(1, "Mathematics and Cooking", null);
        Student student = new Student(1, "Micheal", "Ken", "papipapichurlo@bluewin.com", department, course);
        StudentDTO studentDTO = new StudentDTO(1, "Jordan", "Block", "papipapichurlo@bluewin.com", 1, 1);
        Student savedStudent = new Student(1, "John", "Doe", "ziza@hotmail.mock", department, course);
        StudentDTO expectedStudentDTO = new StudentDTO(1, "John", "Doe", "papipapichurlo@bluewin.com", 1, 1);

        when(departmentRepository.findById(1)).thenReturn(Optional.of(department));
        when(courseRepository.findById(1)).thenReturn(Optional.of(course));
        when(studentRepository.save(any(Student.class))).thenReturn(savedStudent);
        when(studentDTOConverter.convert(savedStudent)).thenReturn(expectedStudentDTO);

        StudentDTO result = studentService.createStudent(studentDTO);

        assertNotNull(result, "Result should not be null");
        assertEquals(expectedStudentDTO, result);
    }

    @Test
    public void testGetStudentById_whenStudentExists_shouldReturnStudent() {
        Department department = new Department(1, "Engineering", null);
        Course course = new Course(1, "Mathematics and Cooking", null);
        Student student = new Student(1, "Micheal", "Ken", "papipapichurlo@bluewin.com", department, course);
        StudentDTO studentDTO = new StudentDTO(1, "Jordan", "Block", "papipapichurlo@bluewin.com", 1, 1);

        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        when(studentDTOConverter.convert(student)).thenReturn(studentDTO);

        Optional<StudentDTO> result = studentService.getStudentById(1);

        assertEquals(Optional.of(studentDTO), result);
    }

    @Test
    public void testGetStudentById_whenStudentDoesNotExist_shouldReturnEmpty() {
        when(studentRepository.findById(1)).thenReturn(Optional.empty());

        Optional<StudentDTO> result = studentService.getStudentById(1);

        assertEquals(Optional.empty(), result);
        verifyNoInteractions(studentDTOConverter);
    }
}