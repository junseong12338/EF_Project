package com.korea.board;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
		
//		List<ProjectDTO> EF_PROJECT = projectService.ProjectList();
//		model.addAttribute("ProectList",EF_PROJECT);		
		return Common.Board.VIEW_PATH + "board_list.jsp";
	}

	@RequestMapping("editor_test")
	 public String editor_test() {
		return Common.Board.VIEW_PATH + "editor_test.jsp";
	}

	

}
