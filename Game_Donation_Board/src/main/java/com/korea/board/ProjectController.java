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
	
	@RequestMapping(value={"/","now_project_list"})
	public String project_list(HttpServletRequest request, HttpSession session) {
		
		// 한페이지에 12개씩 표시할 것
		final int PAGE_PROJECT_COUNT = 12;
		// 저장된 프로젝트(list) 총 갯수
		int list_count = projectService.selectOne();
		// totalPageCount
		int total_page_count = (int)Math.ceil(list_count / (double)PAGE_PROJECT_COUNT);
		
		request.setAttribute("list_total_count", list_count);
		request.setAttribute("total_page_count", total_page_count);
		
		return Common.Project_list.VIEW_PATH + "project_list.jsp";
	}
	
	
	@RequestMapping("ajax_list")
	public String ajax_list(HttpServletRequest request, HttpSession session) {
		
		// 한페이지에 12개씩 표시할 것
		final int PAGE_PROJECT_COUNT = 12;

		int page_num = 1;
		
		
		
		
		int start_list_num = 1 + (page_num - 1) * PAGE_PROJECT_COUNT;
		int end_list_num = start_list_num + PAGE_PROJECT_COUNT;
		int list_count = PAGE_PROJECT_COUNT;
		
		
		// 검색어 처리
		
		
		
		
		
		
		// 수정해야댐
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start_list_num", start_list_num);
		map.put("list_count", list_count);
		
		List<ProjectDTO> list = projectService.selectList(map);
		
		
		
		
		return Common.Project_list.VIEW_PATH + "project_list_ajax.jsp";
	}

}
