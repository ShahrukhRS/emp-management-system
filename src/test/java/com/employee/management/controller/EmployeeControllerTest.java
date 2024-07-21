package com.employee.management.controller;

import com.employee.management.model.MessageResponse;
import com.employee.management.payload.EmployeeDto;
import com.employee.management.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;



@ExtendWith(SpringExtension.class)
public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @Test
    public void testGetAllEmployees() {

        //Given
        EmployeeDto employee = new EmployeeDto(1,"Shahrukh","Patwekari","Sharukhattar029@gmail.com",1,"Manager");
        EmployeeDto anotherEmployee = new EmployeeDto(2,"Shahrukh","Patwekari","Sharukhattar029@gmail.com",1,"Manager");
        List<EmployeeDto> employees = new ArrayList<>();
        employees.add(employee);
        employees.add(anotherEmployee);

        //when
        given(employeeService.findAll()).willReturn(employees);

        //then
        List<EmployeeDto> employeeDtos= employeeController.getAllEmployees();
        assertNotNull(employeeDtos);
        assertEquals(employeeDtos.size(),2);

    }

    @Test
    public void testGetEmployeeById() {
        //given
        EmployeeDto employee = new EmployeeDto(1,"Shahrukh","Patwekari","Sharukhattar029@gmail.com",1,"Manager");

        //when
        given(employeeService.getEmployeeById(anyInt())).willReturn(employee);
        ///then
        ResponseEntity<EmployeeDto> responseEntity = employeeController.getEmployeeById(1);
        assertNotNull(responseEntity);
        assertEquals(employee.getId(),responseEntity.getBody().getId());
    }

    @Test
    public void testCreateEmployee() {
        EmployeeDto employee = new EmployeeDto(1,"Shahrukh","Patwekari","Sharukhattar029@gmail.com",1,"Manager");

        given(employeeService.createEmployee(any())).willReturn(employee);

        ResponseEntity<EmployeeDto> responseEntity = employeeController.createEmployee(employee);

        assertNotNull(responseEntity);

        assertEquals(employee.getId(),responseEntity.getBody().getId());
    }

    @Test
    public void testUpdateEmployee() {

        EmployeeDto employee = new EmployeeDto(1,"Shahrukh","Patwekari","Sharukhattar029@gmail.com",1,"Manager");
        given(employeeService.updateEmployee(any(),anyInt())).willReturn(employee);

        ResponseEntity<EmployeeDto> responseEntity = employeeController.updateEmployee(employee,1);
        assertNotNull(responseEntity);
        assertEquals(employee.getId(),responseEntity.getBody().getId());
    }

    @Test
    public void testDeleteEmployee() {
        //given
        int id= 1;
        MessageResponse  messageResponse=new MessageResponse();
        messageResponse.setMessage("Employee deleted successfully.");
        messageResponse.setCode(HttpStatus.OK.value());
        given(employeeService.deleteEmployeeById(anyInt())).willReturn(messageResponse);
        ///then
        ResponseEntity<MessageResponse> response=employeeController.deleteEmployee(id);

        assertNotNull(response);
        assertEquals(messageResponse.getMessage(), response.getBody().getMessage());
    }
}
