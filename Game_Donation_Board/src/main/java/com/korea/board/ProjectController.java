package com.korea.board;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
	
	@RequestMapping("project_test")
	public String project_view(Model model) {
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		ProjectDTO dto = projectService.select_project(idx);
		
		model.addAttribute("dto",dto);
		
		return Common.Board.VIEW_PATH + "project_view.jsp";
	}
	
	
	// 프로젝트 기간을 고려하지 않은 기본적인 프로젝트 리스트 맵핑
	// + 다 끝나고 진행예정, 진행중, 마감 나눌거임
	@RequestMapping("project_list")
	public String project_list(Model model){
		
		// 스크롤 이벤트를 위한 총 페이지 수 ( 초기값 )
		int total_page_count = 1;
		
		// 바인딩
		model.addAttribute("total_page_count", total_page_count);
		
		// 포워딩
		return "/WEB-INF/views/project_list.jsp";
	}
	
	
	@RequestMapping("ajax_list")
	public String ajax_list(Model model,
							@RequestParam(defaultValue = "1", value="pageNum" )int page_num_js,
							@RequestParam(defaultValue = "0", value="sort")int sort_js,
							@RequestParam(required = true, value="category_box[]")List<String> category_js) throws Exception{
		
		ProjectDTO dto = new ProjectDTO();
		
		// 한 페이지에 들어갈 프로젝트 수
		final int PAGE_PROJECT_COUNT = 12;
		
		// 초기에 요청이 없을경우 default = 1
		int page_num = 1;
		// 프로젝트 시작 리스트 넘버 ( 페이지 번호 마다 )
		int start_num = 1 + (page_num - 1) * PAGE_PROJECT_COUNT;// 1, 13, 25 ...
		// 프로젝트 마지막 리스트 넘버 ( 페이지 번호 마다 )
		int end_num = page_num * PAGE_PROJECT_COUNT;// 12, 24, 36 ...
		// 총 프로젝트 수를 구하기위한 총 프로젝트 수
		int count = projectService.selectOne(dto);
		
		// 스크롤 이벤트를 위한 총 페이지 수
		int total_page_count = (int)Math.ceil(count / (double)PAGE_PROJECT_COUNT);
		
		// 페이지마다 가져올 list start ~ end
		dto.setStart(start_num);
		dto.setEnd(end_num);
		
		
		// 정렬 방법 ( 0, 1, 2 )
		int sort = 0;// 기본값 인기순 
		if(sort_js != 0) {
			sort = sort_js;
		}
		
		dto.setSort(sort);
		
		// 카테고리 저장 List<Integer>
		List<Integer> category = new ArrayList<>();
		if(category_js != null) {
			for(int i = 0; i < category_js.size(); i++) {
				category.add(Integer.parseInt(category_js.get(i)));
			}
		}
		
		dto.setCategory_list(category);
		
		System.out.println(category);
		
		// 변수에 따른 ajax 프로젝트 list
		int list_count = projectService.selectOne(dto);
		List<ProjectDTO> list = projectService.selectList(dto);
		
		// 펀딩 총금액, 남은기간 변수 셋팅
		int persent = 0;
		String diff_date = "";
		long diff = 0;
		
		// 펀딩 총 금액
		persent = Integer.parseInt(dto.getProject_donation()) / dto.getProject_target();
		String persent_str  = String.format("%,d %", persent);
		
		
		// 남은 날짜 반환
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		// 현재
		Date now = new Date();
		// 시작
		Date start_date = sdf.parse(dto.getProject_start());
		// 끝
		Date end_date = sdf.parse(dto.getProject_end());
		
		if(now.getTime() > start_date.getTime()) {
			diff = end_date.getTime() - now.getTime();
		}else if(now.getTime() > end_date.getTime()){
			diff = -1;
		}
		
		if(now.getTime() > end_date.getTime()) {
			diff = -1;
		}else if(now.getTime() > start_date.getTime()) {
			diff = end_date.getTime() - now.getTime();
		}else{
			diff = 0;
		}
		
		if(diff == 0) {
			diff_date = "진행 예정";
		}else if(diff == -1){
			diff_date = "마감";
		}else {
			diff_date = String.format("%d 일", ( diff / (24 * 60 * 60 * 1000L) ) % 365);
		}
		
		
		// 바인딩
		model.addAttribute("total_page_count", total_page_count);
		model.addAttribute("diff_date", diff_date);
		model.addAttribute("persent", persent_str);
		model.addAttribute("list_count", list_count);
		model.addAttribute("list", list);
		model.addAttribute("page_num", page_num);
		
		
		// ajax 포워딩
		return "/WEB-INF/views/project_list_ajax.jsp";
	}

}
