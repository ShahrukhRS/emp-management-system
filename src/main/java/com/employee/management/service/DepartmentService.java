package com.employee.management.service;

import com.employee.management.exception.definition.DepartmentNotFoundException;
import com.employee.management.payload.DepartmentDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.management.entity.Department;
import com.employee.management.repository.DepartmentRepository;

@Service
@Slf4j
public class DepartmentService {
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	public Department findDepartmentById(int id)
	{
		Department department=null;
		try {
			 department = departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException("Bad Request: Department not found"));
		}
		catch (DepartmentNotFoundException e) {
			log.error("Department not found");
		}
		return department;
	}

    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
		Department department=mapToEntity(departmentDto);
		Department tempDepartment=departmentRepository.save(department);
		log.info("Department created with id {}: ",tempDepartment.getId());
		return mapToDTO(tempDepartment);
    }

	private DepartmentDto mapToDTO(Department tempDepartment) {
		DepartmentDto departmentDto=new DepartmentDto();
		departmentDto.setId(tempDepartment.getId());
		departmentDto.setDeptName(tempDepartment.getDeptName());
		return departmentDto;
	}

	private Department mapToEntity(DepartmentDto departmentDto) {
		Department department=new Department();
		department.setDeptName(departmentDto.getDeptName());
		return department;
	}
}
