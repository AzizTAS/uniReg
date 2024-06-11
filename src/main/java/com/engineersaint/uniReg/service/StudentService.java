package com.engineersaint.uniReg.service;

import com.engineersaint.uniReg.dto.StudentDTO;
import com.engineersaint.uniReg.dto.converter.StudentDTOConverter;
import com.engineersaint.uniReg.model.Course;
import com.engineersaint.uniReg.model.Department;
import com.engineersaint.uniReg.model.Student;
import com.engineersaint.uniReg.repository.CourseRepository;
import com.engineersaint.uniReg.repository.DepartmentRepository;
import com.engineersaint.uniReg.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;
    private final CourseRepository courseRepository;
    private final StudentDTOConverter studentDTOConverter;

    public StudentService(StudentRepository studentRepository,
                          DepartmentRepository departmentRepository,
                          CourseRepository courseRepository,
                          StudentDTOConverter studentDTOConverter) {
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
        this.courseRepository = courseRepository;
        this.studentDTOConverter = studentDTOConverter;
    }

    public StudentDTO createStudent(StudentDTO studentDTO) {
        Department department = departmentRepository.findById(studentDTO.getDepartmentId())
                .orElseThrow(() -> new IllegalArgumentException("This id is invalid for Department."));
        Course course = courseRepository.findById(studentDTO.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("This id is invalid for Course."));

        Student student = new Student(
                null,
                studentDTO.getName(),
                studentDTO.getSurname(),
                studentDTO.getEmail(),
                department,
                course
        );

        Student s=studentRepository.save(student);
        return studentDTOConverter.convert(s);
    }

    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(studentDTOConverter::convert)
                .collect(Collectors.toList());
    }

    public Optional<StudentDTO> getStudentById(int id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.map(studentDTOConverter::convert);
    }
}