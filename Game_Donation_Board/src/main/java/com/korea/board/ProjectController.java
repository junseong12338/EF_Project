package com.korea.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProjectController {

	@RequestMapping("ajax_list")
	public String ajax_list(HttpServletRequest request, HttpSession session) {
		
	}
	
}
