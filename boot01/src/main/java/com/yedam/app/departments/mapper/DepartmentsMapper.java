package com.yedam.app.departments.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.app.departments.service.DepartmentsVO;

public interface DepartmentsMapper {
	
	//전체 조회
	public List<DepartmentsVO> selectDepartsList();
	//단건 조회
	public DepartmentsVO selectDepartsInfo(DepartmentsVO departVO);
	//등록
	public int insertDeparts(DepartmentsVO departVO);
	//수정
	public int updateDeparts(@Param("id") int departmentId, @Param("depart") DepartmentsVO departVO);
	//삭제
	public int deleteDeparts(int departmentId);
}
