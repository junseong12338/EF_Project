package com.korea.board;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dto.ProjectDTO;
import lombok.RequiredArgsConstructor;
import service.ProjectService;

@Controller
@RequiredArgsConstructor
public class ProjectDetailController {
	
	final ProjectService projectService;
	
	@RequestMapping("project_detail")
	public String project_detail(Model model, int project_idx) {
		//ex) project_detail?project_idx = 32;
		
		ProjectDTO dto = projectService.select_detail(project_idx);
		
		
		
		return "";
	}
}
