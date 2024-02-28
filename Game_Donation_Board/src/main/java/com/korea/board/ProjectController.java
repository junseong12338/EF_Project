package com.korea.board;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dto.ProjectDTO;
import lombok.RequiredArgsConstructor;
import service.ProjectService;
import util.Common;

@Controller
@RequiredArgsConstructor
public class ProjectController {
	
	final ProjectService projectService;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpSession session;

	@RequestMapping(value={"/","project_list"})
	public String project_list(Model model) {
		
		// 한페이지에 12개씩 표시할 것
		final int PAGE_PROJECT_COUNT = 12;
		// 저장된 프로젝트(list) 총 갯수
		int list_count = projectService.selectOne();
		// totalPageCount
		int total_page_count = (int)Math.ceil(list_count / (double)PAGE_PROJECT_COUNT);
		
		model.addAttribute("list_total_count", list_count);
		model.addAttribute("total_page_count", total_page_count);
		
		return Common.Project_list.VIEW_PATH + "project_list.jsp";
	}
	
	
	@RequestMapping("ajax_list")
	public String ajax_list(Model model, int pageNum) {
		
		// 한페이지에 12개씩 표시할 것
		final int PAGE_PROJECT_COUNT = 12;

		int page_num = 1;
		
		if(pageNum != 0) {
			page_num = pageNum;
		}
		
		int start_list_num = 1 + (page_num - 1) * PAGE_PROJECT_COUNT;
		int list_count = PAGE_PROJECT_COUNT;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start_list_num", start_list_num);
		map.put("list_count", list_count);
		
		List<ProjectDTO> list = projectService.selectList(map);
		
		
		
		
		return Common.Project_list.VIEW_PATH + "project_list_ajax.jsp";
	}

}
