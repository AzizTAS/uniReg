package com.engineersaint.uniReg.dto.converter;

import com.engineersaint.uniReg.dto.DepartmentDTO;
import com.engineersaint.uniReg.model.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentDTOConverter {

    public DepartmentDTO convert(Department department) {
        return new DepartmentDTO(
                department.getId(),
                department.getName()
        );
    }
}