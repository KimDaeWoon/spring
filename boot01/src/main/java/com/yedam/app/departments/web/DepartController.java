package com.yedam.app.departments.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.departments.service.DepartmentsService;
import com.yedam.app.departments.service.DepartmentsVO;

@Controller
public class DepartController {

	private DepartmentsService departService;
	
	@Autowired
	public DepartController(DepartmentsService departService) {
		this.departService = departService;
	}
	
	// 전체 조회
	@GetMapping("departList")
	public String DepartList(Model model) {
		List<DepartmentsVO> list = departService.DepartList();
		
		model.addAttribute("departs", list);
		return "depart/list";
	}
	
	// 단건 조회
	@GetMapping("departInfo")
	public String DepartInfo(DepartmentsVO departmentsVO, Model model) {
		DepartmentsVO finVo = departService.DepartInfo(departmentsVO);
		
		model.addAttribute("depart",finVo);
		
		return "depart/info";
	}
	
	// 등록 페이지 
	@GetMapping("departInsert")
	public String DepartInsert() {
		return "depart/insert";
	}
	
	// 등록 처리
	@PostMapping("departInsert")
	public String DepartInsertProcess(DepartmentsVO departmentsVO) {
		int departmentId = departService.DepartInsert(departmentsVO);
		String url = null;
		if(departmentId > -1) {
			url = "redirect:departInfo?departmentId="+ departmentId;
			
		}else {
			url = "redirect:departList";
		}
		return url;
	}
	
	// 수정 페이지
	@GetMapping("departUpdate")
	public String departUpdateForm(DepartmentsVO departmentsVO, Model model) {
		DepartmentsVO finVo = departService.DepartInfo(departmentsVO);
		model.addAttribute("depart",finVo);
		return "depart/update";
	}
	
	// 수정 처리
	@PostMapping("departUpdate")
	@ResponseBody
	public Map<String, Object> departUpdateQuery(DepartmentsVO departmentsVO){
		return departService.DepartUpdate(departmentsVO);
	}
	
	// 삭제 처리
	@GetMapping("departDelete")
	public String departDelete(Integer departmentId) {
		departService.DepartDelete(departmentId);
		return "redirect:departList";
	}
}
