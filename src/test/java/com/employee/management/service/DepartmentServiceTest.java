package com.employee.management.service;

import com.employee.management.entity.Department;
import com.employee.management.payload.DepartmentDto;
import com.employee.management.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    @Test
    public void testCreateDepartment() {
        Department department = new Department();
        department.setId(1);
        department.setDeptName("Manager");

        DepartmentDto departmentDto= new DepartmentDto();
        departmentDto.setId(1);
        departmentDto.setDeptName("Manager");


        given(departmentRepository.save(any())).willReturn(department);

        DepartmentDto response=departmentService.createDepartment(departmentDto);

        assertNotNull(response);
        assertEquals(departmentDto.getDeptName(), response.getDeptName());
    }

    @Test
    public void testFindDepartmentById() {
        Department department = new Department();
        department.setId(1);
        department.setDeptName("Manager");

        Optional<Department> optionalDepartment= Optional.of(department);

        given(departmentRepository.findById(anyInt())).willReturn(optionalDepartment);

        Department response= departmentService.findDepartmentById(1);

        assertNotNull(response);
        assertEquals(department.getDeptName(), response.getDeptName());


    }
}
