package com.korea.board;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	// 진행중인 프로젝트 맵핑
	@RequestMapping(value={"/","project_list"})
	public String project_list(Model model,
								@RequestParam(value="pageNum")int page_num_js,
								@RequestParam(value="sort")int sort_js,
								@RequestParam(value="category_box")List<Integer> category_js) throws Exception{
		
		
		ProjectDTO dto = new ProjectDTO();
		
		// 한페이지에 12개씩 표시할 것
		final int PAGE_PROJECT_COUNT = 12;
		
		// 페이지
		int page_num = 1;//(기본 페이지 1로 설정)
		if(page_num_js != 0) {
			page_num = page_num_js; 
		}
		
		int start_num = 1 + (page_num - 1) * PAGE_PROJECT_COUNT;
		int end_num = start_num + PAGE_PROJECT_COUNT;
		int count = PAGE_PROJECT_COUNT;
		
		dto.setStart(start_num);
		dto.setEnd(end_num);
		dto.setCount(count);
		
		
		// 정렬방식
		int sort = 0;//(인기순 기본정렬)
		if(sort_js != 0) {
			sort = sort_js;
		}
		
		dto.setSort(sort);
		
		// 카테고리
		List<Integer> category = null;//( 카테고리 담을 참조변수 설정)
		if(category_js != null) {
			category = category_js;
		}
		
		dto.setCategory_list(category);
		
		// 여기까지는 ajax_list 랑 같음
		// 카테고리, 정렬, start,end,count를 보내서 정보를 받아오자. (총 갯수)
		// 프로젝트(list) 총 갯수
		int list_count = projectService.selectOne(dto);
		int total_page_count = (int)Math.ceil(count / (double)PAGE_PROJECT_COUNT);
		
		// 바인딩 - list 총 갯수, 총 페이지 반환
		model.addAttribute("list_count", list_count);
		model.addAttribute("total_page_count", total_page_count);
		
		// 포워딩
		return Common.Project_list.VIEW_PATH + "project_list.jsp";
	}
	
	
	@RequestMapping("ajax_list")
	public String ajax_list(Model model,
							@RequestParam(value="pageNum")int page_num_js,
							@RequestParam(value="sort")int sort_js,
							@RequestParam(value="category_box")List<Integer> category_js) throws Exception{
		
		ProjectDTO dto = new ProjectDTO();
		
		// 한페이지에 12개씩 표시할 것
		final int PAGE_PROJECT_COUNT = 12;
		
		// 페이지
		int page_num = 1;//(기본 페이지 1로 설정)
		if(page_num_js != 0) {
			page_num = page_num_js; 
		}
		
		int start_num = 1 + (page_num - 1) * PAGE_PROJECT_COUNT;
		int end_num = start_num + PAGE_PROJECT_COUNT;
		int count = PAGE_PROJECT_COUNT;
		
		dto.setStart(start_num);
		dto.setEnd(end_num);
		dto.setCount(count);
		
		
		// 정렬방식
		int sort = 0;//(인기순 기본정렬)
		if(sort_js != 0) {
			sort = sort_js;
		}
		
		dto.setSort(sort);
		
		// 카테고리
		List<Integer> category = null;//( 카테고리 담을 참조변수 설정)
		if(category_js != null) {
			category = category_js;
		}
		
		dto.setCategory_list(category);
		
		// 여기까지는 ajax_list 랑 같음
		// 카테고리, 정렬, start,end,count를 보내서 정보를 받아오자. (게시물 List)
		List<ProjectDTO> list = projectService.selectList(dto);
		
		// 달성 퍼센트, 남은 기간
		int persent = 0;
		int date = 0;
		
		persent = Integer.parseInt(dto.getProject_donation()) / dto.getProject_target();
		String.format("%,d__ %", persent);
		
		
		
		// 포워딩
		model.addAttribute("list", list);
		
		return Common.Project_list.VIEW_PATH + "project_list_ajax.jsp";
	}

}
