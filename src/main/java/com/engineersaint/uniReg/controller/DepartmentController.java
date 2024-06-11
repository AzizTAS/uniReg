package com.engineersaint.uniReg.controller;

import com.engineersaint.uniReg.dto.DepartmentDTO;
import com.engineersaint.uniReg.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody DepartmentDTO departmentDTO) {
        DepartmentDTO createdDepartment = departmentService.createDepartment(departmentDTO);
        return new ResponseEntity<DepartmentDTO>(createdDepartment, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable int id) {
        Optional<DepartmentDTO> department = departmentService.getDepartmentById(id);
        return department.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
