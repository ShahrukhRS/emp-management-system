package com.employee.management.service;

import com.employee.management.payload.DepartmentDto;
import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.management.entity.Department;
import com.employee.management.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	public Department findDepartmentById(int id)
	{
		java.util.Optional<Department> departmentOptional=departmentRepository.findById(id);
		
		Department department= departmentOptional.get();
		
		return department;
	}

    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
		Department department=mapToEntity(departmentDto);
		Department tempDepartment=departmentRepository.save(department);
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
