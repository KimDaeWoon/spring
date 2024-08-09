package com.yedam.app.emp.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@RestController // 기존의 @Controller + @ResponseBody를 적용 한다
				// @ResponseBody 는 AJAX 를 쓰겠다 의미
				// 이는 page를 필요 하지 않으니 따로 page를 만들 필요 없다
				// page 를 보여주는 것이 아닌 순수 data 만 뿌려주는 형태 이다
public class EmpRestController {

	private EmpService empService;

	@Autowired
	EmpRestController(EmpService empService) {
		this.empService = empService;
	}

	// 전체 조회 : GET => emps
	@GetMapping("emps")
	public List<EmpVO> empList() {
		return empService.empList();
	}

	// 단건 조회 : GET => emps/100 (경로에 조회할 대상 필요)
	@GetMapping("emps/{eid}")
	public EmpVO empInfo(@PathVariable(name = "eid") Integer employeeId) {
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(employeeId);
		return empService.empInfo(empVO);
	}

	// 등록 : POST => emps
	@PostMapping("emps") // @RequestBody 는 JSON 으로 DATA 를 받는다
	public int empInsert(@RequestBody EmpVO empVO) {
		return empService.empInsert(empVO);
	}

	// 수정 : PUT => emps/100 ()
	@PutMapping("emps/{employeeId}") // @RequestBody 는 JSON 으로 DATA 를 받는당! >3<
	// @PathVariable 경로를 통해서 수정 할 대상을 받아 온당!
	public Map<String, Object> empUpdate(@PathVariable Integer employeeId, @RequestBody EmpVO empVO) {

		empVO.setEmployeeId(employeeId); // 경로 data 와 body data를 합쳐 준당!
		return empService.empUpdate(empVO);
	}

	// 유진이는 바보고, 경민이는 천재다. 대운이는 멍청이 ㅎ
	// 삭제 : DELETE => emps/100 ()
	@DeleteMapping("emps/{employeeId}")
	public Map<String, Object> empDelete(@PathVariable Integer employeeId) {
		return empService.empDelete(employeeId);
	}

}
