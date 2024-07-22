package com.employee.management.exception.advice;

import com.employee.management.exception.definition.DepartmentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.employee.management.exception.definition.EmployeeManagementException;
import com.employee.management.exception.definition.EmployeeNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler({EmployeeNotFoundException.class})
	public ResponseEntity<EmployeeManagementException> handleEmployeeNotFoundException()
	{
		EmployeeManagementException employeeManagementException= new EmployeeManagementException();
		HttpStatus httpStatus=HttpStatus.BAD_REQUEST;
		
		employeeManagementException.setCode(String.valueOf(httpStatus.value()));
		employeeManagementException.setMesssage("Bad Request: Employee not found");
		return new ResponseEntity<>(employeeManagementException,httpStatus);
		
		
	}
	@ExceptionHandler({DepartmentNotFoundException.class})
	public ResponseEntity<EmployeeManagementException> handleDepartmentNotFoundException()
	{
		EmployeeManagementException employeeManagementException= new EmployeeManagementException();
		HttpStatus httpStatus=HttpStatus.BAD_REQUEST;

		employeeManagementException.setCode(String.valueOf(httpStatus.value()));
		employeeManagementException.setMesssage("Bad Request: Department not found");

		return new ResponseEntity<>(employeeManagementException,httpStatus);


	}

}
