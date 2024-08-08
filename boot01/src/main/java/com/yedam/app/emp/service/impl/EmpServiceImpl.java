package com.yedam.app.emp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

import lombok.AllArgsConstructor;

@Service // 우선 1순위 작성
@AllArgsConstructor // 매개변수를 받는 모든 필드를 생성자 함수로 만들어 준다
public class EmpServiceImpl implements EmpService {

	private EmpMapper empMapper;

	@Override
	public List<EmpVO> empList() {
		// TODO Auto-generated method stub
		return empMapper.selectEmpAllList();
	}

	@Override
	public EmpVO empInfo(EmpVO empVO) {
		// TODO Auto-generated method stub
		return empMapper.selectEmpInfo(empVO);
	}

	@Override
	public int empInsert(EmpVO empVO) {
		// TODO Auto-generated method stub
		int result = empMapper.insertEmpInfo(empVO);
		return result == 1 ? empVO.getEmployeeId() : -1;
	}

	@Override
	public Map<String, Object> empUpdate(EmpVO empVO) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>(); // type 이 다를때 Map 으로 보내기가 수월하다
		boolean isSuccessed = false;

		int result = empMapper.updateEmpInfo(empVO.getEmployeeId(), empVO);

		if (result == 1) {
			isSuccessed = true;
		}
		map.put("result", isSuccessed);
		map.put("target", empVO);

		/*
		 * {"result" : true, "target" : { employeeId : 100, lastName : "king" }} 요따구로
		 * 보내어 진다
		 **/

		return map;
	}

	@Override
	public Map<String, Object> empDelete(int empId) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();

		int result = empMapper.deleteEmpInfo(empId);

		if (result == 1) {
			map.put("employeeId", empId);
		}
		return map;
	}

}
