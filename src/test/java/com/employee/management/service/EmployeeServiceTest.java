package com.employee.management.service;

import com.employee.management.entity.Department;
import com.employee.management.entity.Employee;
import com.employee.management.model.MessageResponse;
import com.employee.management.payload.EmployeeDto;
import com.employee.management.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock; 
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;


@ExtendWith(SpringExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private  DepartmentService departmentService;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void testCreateEmployee() {
        EmployeeDto employeeDto = createEmployeeDto();

        Employee employee = createEmployee();

        given(departmentService.findDepartmentById(anyInt())).willReturn(employee.getDepartment());
        given(employeeRepository.save(any())).willReturn(employee);

        EmployeeDto response= employeeService.createEmployee(employeeDto);

        assertNotNull(response);
        assertEquals(employee.getId(), response.getId());
    }

    @Test
    public void testUpdateEmployee() {
        EmployeeDto employeeDto =createEmployeeDto();

        Employee employee = createEmployee();

        given(employeeRepository.findById(anyInt())).willReturn(Optional.of(employee));
        given(employeeRepository.save(any())).willReturn(employee);

        EmployeeDto response= employeeService.updateEmployee(employeeDto,1);
        assertNotNull(response);
        assertEquals(employee.getId(), response.getId());


    }

    @Test
    public void testDeleteEmployee() {
        Employee employee =createEmployee();

        given(employeeRepository.findById(anyInt())).willReturn(Optional.of(employee));
        willDoNothing().given(employeeRepository).delete(any());

        MessageResponse messageResponse= employeeService.deleteEmployeeById(1);

        assertNotNull(messageResponse);

    }

    @Test
    public void testFindAll() {
        List<Employee> employees = new ArrayList<>();

        Employee employee = createEmployee();
        employees.add(employee);


        given(employeeRepository.findAll()).willReturn(employees);

        List<EmployeeDto> employeeDtoList= employeeService.findAll();

        assertNotNull(employeeDtoList);
        assertEquals(employeeDtoList.size(), 1);


    }

    private Employee createEmployee() {

        Employee employee = new Employee();
        employee.setId(1);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmail("john@doe.com");
        employee.setDepartment(createDepartment());

        return employee;
    }

    private Department createDepartment() {
        Department department = new Department();
        department.setId(1);
        department.setDeptName("Manager");
        return department;
    }

    private EmployeeDto createEmployeeDto() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("John");
        employeeDto.setLastName("Doe");
        employeeDto.setEmail("john@doe.com");
        return employeeDto;
    }
}
