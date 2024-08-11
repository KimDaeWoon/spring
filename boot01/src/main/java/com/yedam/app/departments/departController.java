package com.yedam.app.departments;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;





@Controller
public class departController {

	private DepartmentsService departService;
	
	@Autowired
	departController(DepartmentsService departService){
		this.departService = departService;
	}
	
	@GetMapping("departList")
	public String departList(Model model) {
		List<DepartmentsVO> list = departService.DepartList();
		
		model.addAttribute("departs", list);
		return "depart/list";
	}
}
