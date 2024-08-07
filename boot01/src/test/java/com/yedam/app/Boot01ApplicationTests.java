package com.yedam.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpVO;

@SpringBootTest		//테스트 환경에서 IoC 컨테이너 생성
class Boot01ApplicationTests {
	@Autowired		//필드주입(강제)
	EmpMapper empMapper;

	//@Test
	void empList() {	//전체조회
		List<EmpVO> list = empMapper.selectEmpAllList();
		assertTrue(!list.isEmpty());
	}
	
	//@Test
	void empInfo() {	//단건조회
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(100);
		
		EmpVO findVO = empMapper.selectEmpInfo(empVO);
		assertEquals("King", findVO.getLastName());
	}
	
	//@Test
	void empInsert() {	//등록
		EmpVO empVO = new EmpVO();
		empVO.setLastName("Hong");
		empVO.setEmail("Hong11@google.com");
		empVO.setJobId("IT_PROG");
		empVO.setSalary(5000);
		
		int result = empMapper.insertEmpInfo(empVO);
		assertEquals(1, result);
	}
	
	//@Test
	void empUpdate() {	//수정
		// 1. 단건조회 > 수정할 내용입력 > update
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(315);
		
		EmpVO findVO = empMapper.selectEmpInfo(empVO);
		findVO.setLastName("Dong");
		
		int result = empMapper.updateEmpInfo(findVO.getEmployeeId(), findVO);
		assertEquals(1, result);
	}
	
	@Test
	void empDelete() {	//삭제
		int result = empMapper.deleteEmpInfo(315);
		assertEquals(1, result);
	}

}
