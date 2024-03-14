package com.korea.board;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dto.ProjectDTO;
import lombok.RequiredArgsConstructor;
import service.ProjectService;
import util.Common;

@Controller
@RequiredArgsConstructor
public class BoardController {

	final ProjectService projectService;	
	
	@RequestMapping(value = { "/", "board_list" })	
	public String list(Model model) {
		List<ProjectDTO> Main_limit_list = projectService.Main_limit_list();
		model.addAttribute("Main_limit_list",Main_limit_list);		
		return Common.Board.VIEW_PATH + "board_list.jsp";
	}

	@RequestMapping("login_filter")
	 public String login_filter(Model model,@RequestParam(value="status", defaultValue="-1") int status) {
		
		model.addAttribute("status",status);
		
		return Common.Board.VIEW_PATH + "login_filter.jsp";
		
	}

	

}
