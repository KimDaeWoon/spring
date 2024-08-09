package com.yedam.app.emp.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@Controller // route : 사용자의 요청 (endpoint)와 그에 대한 처리
			// : URL + METHOD => Service => View
			// 서비스 화면? 연결 역할
public class EmpController {
	// 해당 컨트롤러에서 제공하는 서비스
	private EmpService empService;

	@Autowired
	EmpController(EmpService empService) {
		this.empService = empService;
	}

	// GET : 조회, 빈페이지 (보완 취약 하다)
	// POST : 데이터 조작(등록, 수정, 삭제)

	// 전체조회 : GET
	@GetMapping("empList") // Model = Request + Response
	public String empList(Model model) {
		// 1) 해당 기능 수행 => Service
		List<EmpVO> list = empService.empList();

		// 2) 클라이언트에 전달할 데이터 담기
		model.addAttribute("emps", list);

		return "emp/list"; // 3) 데이터를 출력할 페이지 결정
		// classpath:/templates/emp/list.html
		// prefix--------------return--subfix

	}

	// 단건조회 Get 방식 => QueryString 방식 많이 사용
	@GetMapping("empInfo")
	public String empInfo(EmpVO empVO, Model model) {
		// 1) 해당 기능 수행 => Service
		EmpVO findVO = empService.empInfo(empVO);

		// 2) 클라이언트에 전달할 데이터 담기
		model.addAttribute("emp", findVO);

		return "emp/info";// 3) 데이터를 출력할 페이지 결정
		// classpath:/templates/emp/info.html
		// prefix--------------return--subfix
		// return "redirect:empList";
	}

	// 등록 - 페이지 : GET
	@GetMapping("empInsert")
	public String empInsertForm() {
		return "emp/insert";
	}

	// 등록 - 처리 : POST => form 태그를 통한 submit
	@PostMapping("empInsert")
	public String empInsertProcess(EmpVO empVO) {
		int eid = empService.empInsert(empVO);
		String url = null;
		if (eid > -1) {
			// 정상적으로 등록된 경우
			url = "redirect:empInfo?employeeId=" + eid;
		} else {
			url = "redirect:empList";
		}
		return url;
	}

	// 수정 - 페이지 : GET <=> 단건조회
	@GetMapping("empUpdate")
	public String empUpdateForm(EmpVO empVO, Model model) {
		EmpVO findVO = empService.empInfo(empVO);
		model.addAttribute("emp", findVO);
		return "emp/update";
	}

	// 수정 - 처리 AJAX => QueryString
	// @PostMapping("empUpdate")
	@ResponseBody // @ResponseBody얘가 AJAX를 의미 한다
	public Map<String, Object> empupdateAJAXQueryString(EmpVO empVO) {
		return empService.empUpdate(empVO);
	}

	// 수정 - 처리 AJAX => JSON (= @RequestBody)
	@PostMapping("empUpdate")
	@ResponseBody // @ResponseBody얘가 AJAX를 의미 한다
	public Map<String, Object> empupdateAJAXJSON(@RequestBody EmpVO empVO) {
		return empService.empUpdate(empVO);
	}

	// 삭제 - 처리 : GET
	@GetMapping("empDelete")
	public String empDelete(Integer employeeId) {
		empService.empDelete(employeeId);
		return "redirect:empList"; // 현재 실시간 조회로 data를 즉시 삭제 처리 하기위 해 redirect 로 하였습니당
	}

}
