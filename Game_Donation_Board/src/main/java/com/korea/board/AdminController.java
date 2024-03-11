package com.korea.board;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dto.PageDTO;
import dto.ProjectDTO;
import dto.ProjectStatus;
import lombok.RequiredArgsConstructor;
import service.ProjectService;
import util.Common;


@Controller
@RequiredArgsConstructor
public class AdminController {	
	final ProjectService projectService;	
		
	 @RequestMapping("AdminPage")
		public String AdminPage(Model model, @RequestParam(value = "page", defaultValue = "1")int page) {

			int start = (page-1) * Common.Admin.BLOCKLIST + 1;
			int end = start + Common.Admin.BLOCKLIST - 1;

			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("start",start);
			map.put("end",end);

			//페이지 번호에 따른 전체 게시글 조회
			List<ProjectDTO> ProectList = projectService.getRowTotal(map);		
			
			PageDTO pageDTO = projectService.getContentCnt(page);
			model.addAttribute("ProectList",ProectList);
			model.addAttribute("page",pageDTO);		
			return Common.User.VIEW_PATH + "AdminPage.jsp?page="+page;
		}
	 
	 
	 
	 @PostMapping("Status")
	 public String handleStatusUpdate(@RequestBody List<ProjectStatus> statusList) {
	     for (ProjectStatus projectStatus : statusList) {
	         int project_status = projectStatus.getProject_status();
	         int project_idx= projectStatus.getProject_idx();
	         System.out.println("Status: " + project_status + " " + project_idx);
	         projectService.updateStatus(project_idx);
	     }
	     return "redirect:AdminPage";
	 }


	
	    

}
