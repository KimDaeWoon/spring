package com.yedam.app.departments;

import java.util.List;

import org.apache.ibatis.annotations.Param;


public interface DepartmentsMapper {
	
	//전체 조회
	public List<DepartmentsVO> selectDepart();
	//단건 조회
	public DepartmentsVO selectDepartInfo(DepartmentsVO departVO);
	//등록
	public int insertDepartInfo(DepartmentsVO departVO);
	//수정
	public int updateDepartInfo(@Param("id") int departmentId, @Param("depart") DepartmentsVO departVO);
	//삭제
	public int deleteDepartInfo(int departmentId);
}
