package com.engineersaint.uniReg.service;

import com.engineersaint.uniReg.dto.DepartmentDTO;
import com.engineersaint.uniReg.dto.converter.DepartmentDTOConverter;
import com.engineersaint.uniReg.model.Department;
import com.engineersaint.uniReg.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentDTOConverter departmentDTOConverter;

    public DepartmentService(DepartmentRepository departmentRepository, DepartmentDTOConverter departmentDTOConverter) {
        this.departmentRepository = departmentRepository;
        this.departmentDTOConverter = departmentDTOConverter;
    }

    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        Department department = new Department(null, departmentDTO.getName(),null);
        Department savedDepartment = departmentRepository.save(department);
        return departmentDTOConverter.convert(savedDepartment);
    }

    public Optional<DepartmentDTO> getDepartmentById(int id) {
        Optional<Department> department = departmentRepository.findById(id);
        return department.map(departmentDTOConverter::convert);
    }
}