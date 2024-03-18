package com.korea.board;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import dto.AdminInfoDTO;
import dto.PageDTO;
import dto.ProjectStatusDTO;
import lombok.RequiredArgsConstructor;
import service.ProjectService;
import util.Common;


@Controller
@RequiredArgsConstructor
public class AdminController {	
	final ProjectService projectService;	
			
	@RequestMapping(value = "AdminPage", method = RequestMethod.GET)
	public String getAdminPage(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
	    int start = (page - 1) * Common.Admin.BLOCKLIST + 1;
	    int end = start + Common.Admin.BLOCKLIST - 1;
	    
	    HashMap<String, Integer> map = new HashMap<String, Integer>();
	    map.put("start", start);
	    map.put("end", end);
	    
	    List<AdminInfoDTO> adminInfo = projectService.getRowTotal(map);
	    
	    System.out.println(adminInfo);
	    PageDTO pageDTO = projectService.getContentCnt(page);
	    	    
	    model.addAttribute("AdminInfo", adminInfo);
	    model.addAttribute("page", pageDTO);
	    
	    return Common.User.VIEW_PATH + "AdminPage.jsp";
	}
	 
	 
	 @PostMapping("Status")
	 public String handleStatusUpdate(@RequestBody List<ProjectStatusDTO> statusList) {
	     for (ProjectStatusDTO projectStatus : statusList) {
	         int project_idx= projectStatus.getProject_idx();
	         projectService.updateStatus(project_idx);
	     }
	     return "redirect:AdminPage";
	 }


	
	    

}
