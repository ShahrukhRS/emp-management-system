package com.employee.management.controller;

import java.util.List;

import com.employee.management.model.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.employee.management.payload.EmployeeDto;
import com.employee.management.service.EmployeeService;

@RestController
@RequestMapping("/api/employee-management")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employees")
	public List<EmployeeDto> getAllEmployees() {

		List<EmployeeDto> employeeDto = employeeService.findAll();
		return employeeDto;

	}
	@PostMapping("/")
	public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto)
	{
		return new ResponseEntity<>(employeeService.createEmployee(employeeDto),HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable(name = "id") int id) {
		return ResponseEntity.ok(employeeService.getEmployeeById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable(name = "id") int id) {

		EmployeeDto employeeDtoResponse = employeeService.updateEmployee(employeeDto, id);

		
		return new ResponseEntity<>(employeeDtoResponse, HttpStatus.OK);
	}

	
	@DeleteMapping("/{id}")
	public ResponseEntity<MessageResponse> deleteEmployee(@PathVariable(name = "id") int id) {

		MessageResponse messageResponse=employeeService.deleteEmployeeById(id);

		return new ResponseEntity<>(messageResponse, HttpStatus.OK);
	}

}
