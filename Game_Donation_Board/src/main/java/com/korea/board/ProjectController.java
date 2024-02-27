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
		
		int pageNum = 1;
		String strPageNum = request.getParameter("pageNum");
		
		if(strPageNum != null) {
			pageNum = Integer.parseInt(strPageNum);
		}
		
		int startList = (pageNum - 1) * PAGE_PROJECT_COUNT;
		int endList = pageNum * PAGE_PROJECT_COUNT;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startList", startList);
		map.put("endList", endList);
		
		HashMap<String, Object> selectMap = projectService.selectList(map);
		
		// 저장된 프로젝트(list) 총 갯수
		int listTotal = (int)selectMap.get("listTotal");
		// 불러올 프로젝트(list) - 페이지번호에 해당하는 !
		List<ProjectDTO> list = (List<ProjectDTO>)selectMap.get("list");
		
		model.addAttribute("listTotal", listTotal);
		model.addAttribute("list", list);
		
		return Common.Board.VIEW_PATH + "project_list.jsp";
	}
	
	
}
