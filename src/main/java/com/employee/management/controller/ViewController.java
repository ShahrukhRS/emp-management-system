package com.employee.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
	
	@RequestMapping("/view-employees")
	public String getEmployeeList()
	{
		return "employee-list";
	}

	@RequestMapping("/showAddForm")
	public String getEmployeeForm()
	{
		return "employee-form";
	}

	@RequestMapping("/update-form")
	public String getUpdateForm()
	{
		return "employee-update";
	}

	@RequestMapping("/showAddDeptForm")
	public String getDepartmentForm()
	{
		return "department-form";
	}



}
