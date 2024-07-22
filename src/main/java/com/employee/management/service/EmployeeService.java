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
		List<EmployeeDto> employeeDtoList = null;
		if(!employees.isEmpty())
		{
			employeeDtoList=employees.stream().map(this::mapToDto).collect(Collectors.toList());
		}
		else
		{
			log.error("Employee list is empty");
		}
		
		return  employeeDtoList;
	}
	
	
	public EmployeeDto getEmployeeById(int id)
	{
		Employee tempEmployee=null;
		EmployeeDto employeeDto = null;
		try {
			tempEmployee = employeeRepository.findById(id).orElseThrow(()->new EmployeeNotFoundException("Bad Request: Employee not found"));
			employeeDto = mapToDto(tempEmployee);
		}
		catch(EmployeeNotFoundException e)
		{
			log.error("Employee not found with the given id {} :",id);
		}

        return employeeDto;
	}
	
	public  EmployeeDto updateEmployee(EmployeeDto employeeDto, int id) {
		Employee employee;
		EmployeeDto employeeDtoResponse=null;
		try {
			Employee tempEmployee = employeeRepository.findById(id).orElseThrow(()->new EmployeeNotFoundException("Bad Request: Employee not found"));
			tempEmployee.setFirstName(employeeDto.getFirstName());
			tempEmployee.setLastName(employeeDto.getLastName());
			tempEmployee.setEmail(employeeDto.getEmail());
			employee= employeeRepository.save(tempEmployee);
			employeeDtoResponse= mapToDto(employee);
			log.info("Employee updated successfully with id {} :",id);

		}
		catch(EmployeeNotFoundException e)
		{
			log.error("Employee not found with the given id {} :",id);
		}

		return employeeDtoResponse;
	}
	


	public MessageResponse deleteEmployeeById(int id) {
		MessageResponse messageResponse = new MessageResponse();
		try {
			Employee employee= employeeRepository.findById(id).orElseThrow(()->new EmployeeNotFoundException("Bad Request: Employee not found"));
			employeeRepository.delete(employee);
			messageResponse.setMessage("Employee deleted successfully.");
			messageResponse.setCode(HttpStatus.OK.value());
			log.info("Employee deleted successfully with id {} :",id);

		}
		catch(EmployeeNotFoundException e)
		{
			log.error("employee not found with the given id {} :",id);
			messageResponse.setMessage("Employee not found.");
			messageResponse.setCode(HttpStatus.NOT_FOUND.value());
		}

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

	private Employee mapToEntity(EmployeeDto employeedto)
	{
		Employee employee= new Employee();
		employee.setId(employeedto.getId());
		employee.setFirstName(employeedto.getFirstName());
		employee.setLastName(employeedto.getLastName());
		employee.setEmail(employeedto.getEmail());		
		return employee;
	}



	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
	
        Employee employee = mapToEntity(employeeDto);
        Department department= departmentService.findDepartmentById(employeeDto.getDeptId());
        employee.setDepartment(department);
        Employee newEmployee = employeeRepository.save(employee);

		log.info("Employee created successfully with id {} :",newEmployee.getId());

        return mapToDto(newEmployee);
		
	}


}
