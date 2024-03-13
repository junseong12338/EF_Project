package com.korea.board;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import service.ProjectService;
import util.Common;

@Controller
@RequiredArgsConstructor
public class BoardController {

	final ProjectService projectService;	
	
	@RequestMapping(value = { "/", "board_list" })	
	public String list(Model model) {
		System.out.println("list controller test..");
//		List<ProjectDTO> EF_PROJECT = projectService.selectList_all_list();
//		model.addAttribute("ProectList",EF_PROJECT);		
		return Common.Board.VIEW_PATH + "board_list.jsp";
	}

	@RequestMapping("login_filter")
	 public String login_filter(Model model,@RequestParam(value="status", defaultValue="-1") int status) {
		
		model.addAttribute("status",status);
		
		return Common.Board.VIEW_PATH + "login_filter.jsp";
		
	}

	

}
