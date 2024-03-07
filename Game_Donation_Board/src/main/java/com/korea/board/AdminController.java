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
public class AdminController {
	final ProjectService projectService;	

	
	@RequestMapping("AdminList")	
	public String AdminList(Model model) {
		List<ProjectDTO> EF_PROJECT = projectService.ProjectList();
		model.addAttribute("ProectList",EF_PROJECT);	
		

		return Common.User.VIEW_PATH + "AdminList.jsp";
	}
	
}
