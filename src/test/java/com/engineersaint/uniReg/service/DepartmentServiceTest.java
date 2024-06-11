package com.engineersaint.uniReg.service;

import com.engineersaint.uniReg.dto.DepartmentDTO;
import com.engineersaint.uniReg.dto.converter.DepartmentDTOConverter;
import com.engineersaint.uniReg.model.Department;
import com.engineersaint.uniReg.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;

public class DepartmentServiceTest {

    private DepartmentRepository departmentRepository;
    private DepartmentDTOConverter departmentDTOConverter;
    private DepartmentService departmentService;

    @BeforeEach
    public void setUp() {
        departmentRepository = mock(DepartmentRepository.class);
        departmentDTOConverter = mock(DepartmentDTOConverter.class);
        departmentService = new DepartmentService(departmentRepository, departmentDTOConverter);
    }

    @Test
    public void testCreateDepartment() {
        DepartmentDTO departmentDTO = new DepartmentDTO(null, "Engineering");
        Department department = new Department(null, "Engineering", null);
        Department savedDepartment = new Department(1, "Engineering", null);

        Mockito.when(departmentRepository.save(department)).thenReturn(savedDepartment);
        Mockito.when(departmentDTOConverter.convert(savedDepartment)).thenReturn(new DepartmentDTO(1, "Engineering"));

        DepartmentDTO result = departmentService.createDepartment(departmentDTO);

        assertEquals(new DepartmentDTO(1, "Engineering"), result);
    }

    @Test
    public void testGetDepartmentById_whenDepartmentExists_shouldReturnDepartment() {
        Department department = new Department(1, "Engineering", null);
        DepartmentDTO departmentDTO = new DepartmentDTO(1, "Engineering");

        Mockito.when(departmentRepository.findById(1)).thenReturn(Optional.of(department));
        Mockito.when(departmentDTOConverter.convert(department)).thenReturn(departmentDTO);

        Optional<DepartmentDTO> result = departmentService.getDepartmentById(1);

        assertEquals(Optional.of(departmentDTO), result);
    }

    @Test
    public void testGetDepartmentById_whenDepartmentDoesNotExist_shouldReturnEmpty() {
        Mockito.when(departmentRepository.findById(1)).thenReturn(Optional.empty());

        Optional<DepartmentDTO> result = departmentService.getDepartmentById(1);

        assertEquals(Optional.empty(), result);
        verifyNoInteractions(departmentDTOConverter);
    }
}