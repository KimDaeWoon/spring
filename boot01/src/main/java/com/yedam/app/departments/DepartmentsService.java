package com.yedam.app.departments;

import java.util.List;
import java.util.Map;

public interface DepartmentsService {
	
	// 전체 조회
	public List<DepartmentsVO> DepartList();
	
	// 단건 조회
	public DepartmentsVO DepartInfo(DepartmentsVO departVO);
	
	// 등록
	public int DepartInsert(DepartmentsVO departVO);
	
	// 수정
	public Map<String, Object> DepartUpdate(DepartmentsVO departVO);
	
	// 삭제
	public Map<String, Object> DepartDelete(int departmentId);
}
