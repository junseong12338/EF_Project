package com.korea.board;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dto.ProjectDTO;
import dto.ProjectMainListDTO;
import lombok.RequiredArgsConstructor;
import service.ProjectService;
import util.Common;

@Controller
@RequiredArgsConstructor
public class BoardController {

	final ProjectService projectService;	
	
	@RequestMapping(value = { "/", "board_list" })	
	public String list(Model model) {
		
		List<ProjectMainListDTO> Main_New_registration_list = projectService.Main_New_registration_list();
		List<ProjectMainListDTO> Main_donation_list = projectService.Main_donation_list();
		List<ProjectMainListDTO> Main_To_be_released_list = projectService.Main_To_be_released_list();
		List<ProjectMainListDTO> Main_Like_Project_list = projectService.Main_Like_Project_list();

		
		model.addAttribute("Main_New_registration_list",Main_New_registration_list);	
		model.addAttribute("Main_donation_list",Main_donation_list);		
		model.addAttribute("Main_To_be_released_list",Main_To_be_released_list);		
		model.addAttribute("Main_Like_Project_list",Main_Like_Project_list);	

		return Common.Board.VIEW_PATH + "board_list.jsp";
	}

	@RequestMapping("login_filter")
	 public String login_filter(Model model,@RequestParam(value="status", defaultValue="-1") int status) {
		
		model.addAttribute("status",status);
		
		return Common.Board.VIEW_PATH + "login_filter.jsp";
		
	}

	

}
