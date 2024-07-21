package com.employee.management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Department {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="dept_id")
	private int id;
	
	@Column(name="dept_name")
	private String deptName;
	
	
	
	public Department() {
		super();
	}
	
	public Department(String deptName) {
		super();
		this.deptName = deptName;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	@Override
	public String toString() {
		return "Department [id=" + id + ", deptName=" + deptName + "]";
	}
	
	
}
