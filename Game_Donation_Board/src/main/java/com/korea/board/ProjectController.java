package com.korea.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import service.ProjectService;

@Controller
@RequiredArgsConstructor
public class ProjectController {
	
	final ProjectService projectService;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpSession session;

	@RequestMapping(value={"/","project_list"})
	public String project_list(HttpServletRequest request, HttpSession session) {
		
		// 한페이지에 12개씩 표시할 것
		final int PAGE_PROJECT_COUNT = 12;
		
		int pageNum = 1;
		String strPageNum = request.getParameter("pageNum");
		
		if(strPageNum != null) {
			pageNum = Integer.parseInt(strPageNum);
		}
		
		int startList = (pageNum - 1) * PAGE_PROJECT_COUNT;
		int endList = pageNum * PAGE_PROJECT_COUNT;
		int listCount = PAGE_PROJECT_COUNT;
		
		
		
		
		
		
	}
	
}
