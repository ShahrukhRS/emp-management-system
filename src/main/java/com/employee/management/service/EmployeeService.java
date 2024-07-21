package com.employee.management.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.employee.management.model.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.employee.management.entity.Department;
import com.employee.management.entity.Employee;
import com.employee.management.exception.definition.EmployeeNotFoundException;
import com.employee.management.payload.DepartmentDto;
import com.employee.management.payload.EmployeeDto;
import com.employee.management.repository.EmployeeRepository;


import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private DepartmentService departmentService;
	
	public List<EmployeeDto> findAll()
	{
		List<Employee> employees= employeeRepository.findAll();
		
		return employees.stream().map(emmployee->mapToDto(emmployee)).collect(Collectors.toList());
	}
	
	
	public EmployeeDto getEmployeeById(int id)
	{
		Employee tempEmployee = employeeRepository.findById(id).orElseThrow(()->new EmployeeNotFoundException("Bad Request: Employee not found"));
		return mapToDto(tempEmployee);
	}
	
	public  EmployeeDto updateEmployee(EmployeeDto employeeDto, int id) {
		 	Employee tempEmployee = employeeRepository.findById(id).orElseThrow(()->new EmployeeNotFoundException("Bad Request: Employee not found"));

		 	tempEmployee.setFirstName(employeeDto.getFirstName());
		 	tempEmployee.setLastName(employeeDto.getLastName());
		 	tempEmployee.setEmail(employeeDto.getEmail());
			 Employee employee= employeeRepository.save(tempEmployee);
			 return mapToDto(employee);
	}
	


	public MessageResponse deleteEmployeeById(int id) {
		MessageResponse messageResponse = new MessageResponse();
		Optional<Employee> employeeOptional = employeeRepository.findById(id);
		Employee tempEmployee= employeeOptional.get();
	 	employeeRepository.delete(tempEmployee);
		messageResponse.setMessage("Employee deleted successfully.");
		messageResponse.setCode(HttpStatus.OK.value());
		return messageResponse;
	}


	private EmployeeDto mapToDto(Employee employee)
	{
		EmployeeDto employeeDto= new EmployeeDto();
		employeeDto.setId(employee.getId());
		employeeDto.setFirstName(employee.getFirstName());
		employeeDto.setLastName(employee.getLastName());
		employeeDto.setEmail(employee.getEmail());
		employeeDto.setDeptId(employee.getDepartment().getId());
		employeeDto.setDeptName(employee.getDepartment().getDeptName());
		
		return employeeDto;
	}
	private DepartmentDto mapDepartmentDto(Employee employee) {
		DepartmentDto departmentDto= new DepartmentDto();
		
		departmentDto.setId(employee.getDepartment().getId());
		departmentDto.setDeptName(employee.getDepartment().getDeptName());
		
		return departmentDto;
	}

	private Employee mapToEntity(EmployeeDto employeedto)
	{
		Employee employee= new Employee();
		employee.setId(employeedto.getId());
		employee.setFirstName(employeedto.getFirstName());
		employee.setLastName(employeedto.getLastName());
		employee.setEmail(employeedto.getEmail());		
		return employee;
	}

	private Department mapToDepartmentEnity(EmployeeDto employeedto) {
		
		Department department= new Department();
		department.setId(employeedto.getDeptId());
		department.setDeptName(employeedto.getDeptName());
		
		return department;
		
	}


	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
	
        Employee employee = mapToEntity(employeeDto);
        Department department= departmentService.findDepartmentById(employeeDto.getDeptId());
        employee.setDepartment(department);
        Employee newEmployee = employeeRepository.save(employee);
        return mapToDto(newEmployee);
		
	}


	
}
