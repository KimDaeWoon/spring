package com.yedam.app.departments.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.departments.mapper.DepartmentsMapper;
import com.yedam.app.departments.service.DepartmentsService;
import com.yedam.app.departments.service.DepartmentsVO;
import com.yedam.app.emp.mapper.EmpMapper;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor 
public class DepartmentsServiceImpl implements DepartmentsService{

	private DepartmentsMapper departmentsMapper;
	
	
	@Override
	public List<DepartmentsVO> DepartList() {
		// TODO Auto-generated method stub
		return departmentsMapper.selectDepartsList();
	}

	@Override
	public DepartmentsVO DepartInfo(DepartmentsVO departVO) {
		// TODO Auto-generated method stub
		return departmentsMapper.selectDepartsInfo(departVO);
	}

	@Override
	public int DepartInsert(DepartmentsVO departVO) {
		// TODO Auto-generated method stub
		int result = departmentsMapper.insertDeparts(departVO);
		return result == 1 ? departVO.getDepartmentId() : -1;
	}

	@Override
	public Map<String, Object> DepartUpdate(DepartmentsVO departVO) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = departmentsMapper.updateDeparts(departVO.getDepartmentId(), departVO);
		
		if(result == 1) {
			isSuccessed = true;
		}
		map.put("result", isSuccessed);
		map.put("target", departVO);
		
		return map;
	}

	@Override
	public Map<String, Object> DepartDelete(int departmentId) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		
		int result = departmentsMapper.deleteDeparts(departmentId);
		
		if(result == 1) {
			map.put("departmentId", departmentId);
		}
		return map;
	}

}
