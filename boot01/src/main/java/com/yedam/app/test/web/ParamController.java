package com.yedam.app.test.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpVO;

@CrossOrigin(origins = "*")
@Controller
public class ParamController {
	// Query String 문자열 처리 key = value & key = value
	// Content-type : application/x-www-form-urlencode
	// Method : 상관 없음

	// QueryString => 커맨드 객체(어노테이션 x, 객체)
	@RequestMapping(path = "comobj", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String commandObject(EmpVO empVO) {
		String result = "";
		result += "Path: /comobj\n";
		result += "\t employee_id : " + empVO.getEmployeeId();
		result += "\t, last_name : " + empVO.getLastName();
		return result;
	}

	// QueryString => @RequestParam : 기본타입 , 단일값
	@RequestMapping(path = "reqparam", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String requestParam(@RequestParam Integer employeeId, // 필수
			String lastName, // 생략가능
			@RequestParam(name = "message", defaultValue = "No message", required = true) String msg) {
		String result = "";
		result += "path : /reqparam \n";
		result += "\t employee_id : " + employeeId;
		result += "\t last_name : " + lastName;
		result += "\t message : " + msg;
		return result;
	}

	// @PathVariable : 경로에 값을 포함하는 방식, 단일 값
	// Method 는 상관없음
	// Content-type도 상관 없음
	@RequestMapping("path/{id}") // path/Hong, path/1234 여기서 path 만 사용 할 경우 status : 404 경로가 다르다고 뜬다
	@ResponseBody
	public String pathVariable(@PathVariable String id) {
		String result = "";
		result += "path : /path \n";
		result += "\t id : " + id;
		return result;
	}

	// @RequestBody : JOSN 포멧, 배열 or 객체
	// Method : POST, PUT
	// Content-type : application/json
	@RequestMapping("resbody")
	@ResponseBody
	public String requestBody(@RequestBody EmpVO empVO) {
		String result = "";
		result += "Path : /resbody : /resbody \n";
		result += "\t employee_id :" + empVO.getEmployeeId();
		result += "\t last_name :" + empVO.getLastName();
		return result;
	}

	@RequestMapping("resbodyList")
	@ResponseBody
	public String requsetBodyList(@RequestBody List<EmpVO> list) {
		String result = "path : /resbodyList \n";
		for (EmpVO empVO : list) {
			result += "\t employee_id :" + empVO.getEmployeeId();
			result += "\t last_name :" + empVO.getLastName();
			result += "\n";
		}
		return result;
	}
}
