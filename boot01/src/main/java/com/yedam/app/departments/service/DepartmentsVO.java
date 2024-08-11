package com.yedam.app.departments.service;

import lombok.Data;

@Data
public class DepartmentsVO {
	Integer departmentId;
	String departmentName;
	int managerId;
	int locationId;
	
}
