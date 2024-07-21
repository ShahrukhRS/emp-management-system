package com.employee.management.controller;

import com.employee.management.payload.DepartmentDto;
import com.employee.management.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class DepartmentControllerTest {

    @Mock
    DepartmentService departmentService;

    @InjectMocks
    DepartmentController departmentController;

    @Test
    public void testCreateDepartment() {
        DepartmentDto departmentDto = new DepartmentDto("Manager");

        given(departmentService.createDepartment(any())).willReturn(departmentDto);

        ResponseEntity<DepartmentDto> response = departmentController.createDepartment(departmentDto);

        assertNotNull(response);
        assertEquals(departmentDto.getDeptName(), response.getBody().getDeptName());

    }



}
