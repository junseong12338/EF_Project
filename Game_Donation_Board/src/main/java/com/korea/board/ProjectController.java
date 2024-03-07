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
	
	
	
	@RequestMapping("project_list")
	public String project_list(Model model){
		
		
		int total_page_count = 1;
		
		
		model.addAttribute("total_page_count", total_page_count);
		
		
		return "/WEB-INF/views/project/list.jsp";
	}
	
	
	@RequestMapping("ajax_list")
	public String ajax_list(Model model,
							@RequestParam(defaultValue = "1", value="pageNum" )int page_num_js,
							@RequestParam(defaultValue = "0", value="sort")int sort_js,
							@RequestParam(defaultValue = "0", value="sort_date")int sort_date_js,
							@RequestParam(required = true, value="category_box[]")List<String> category_js) throws Exception{
		
		// 변수를 넘겨주기위한 dto
		ProjectDTO dto = new ProjectDTO();
		
		
		final int PAGE_PROJECT_COUNT = 12;
		
		
		int page_num = 1;
		
		int start_num = 1 + (page_num - 1) * PAGE_PROJECT_COUNT;// 1, 13, 25 ...
		
		int end_num = page_num * PAGE_PROJECT_COUNT;// 12, 24, 36 ...
		
		
		
		
		dto.setStart(start_num);
		dto.setEnd(end_num);
		
		
		
		int sort = 0;
		if(sort_js != 0) {
			sort = sort_js;
		}
		
		dto.setSort(sort);
		
		// 진행여부 
		int sort_date = 0;
		if(sort_date_js != 0) {
			sort_date = sort_date_js;
		}
		
		dto.setSort_date(sort_date);
		
		// 카테고리
		List<Integer> category = new ArrayList<>();
		
		if(category_js != null) {
			for(int i = 0; i < category_js.size(); i++) {
				if(!category_js.get(i).equals("0")) {
					category.add(Integer.parseInt(category_js.get(i)));
				}
				
			}
		}
		
		dto.setCategory_list(category);
		
		System.out.println(category);
		System.out.println(sort);
		System.out.println(sort_date);
		

		int list_count = projectService.selectOne(dto);
		
		int total_page_count = (int)Math.ceil(list_count / (double)PAGE_PROJECT_COUNT);
		
		
		List<ProjectDTO> list = projectService.selectList(dto);
		
		
		int persent = 0;
		String diff_date = "";
		long diff = 0;
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		Date now = new Date();
		
		
		for(int i = 0; i < list.size(); i++) {
			// 퍼센트 dto.setPersent
			persent = list.get(i).getProject_donation() / list.get(i).getProject_target();
			String persent_str  = String.format("%,d", persent);
			list.get(i).setPersent(persent_str + " %");
			
			// 남은기간 dto.setDiff_date
			Date start_date = sdf.parse(list.get(i).getProject_start());
			
			Date end_date = sdf.parse(list.get(i).getProject_end());
			
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
				diff_date = "진행예정";
			}else if(diff == -1){
				diff_date = "마감";
			}else {
				diff_date = String.format("%d 일", ( diff / (24 * 60 * 60 * 1000L) ) % 365);
			}
			
			list.get(i).setDiff_date(diff_date);
			
		}
		
		
		
		// 바인딩
		model.addAttribute("total_page_count", total_page_count);
		model.addAttribute("list_count", list_count);
		model.addAttribute("list", list);
		model.addAttribute("page_num", page_num);
		
		// ajax - 포워딩
		return "/WEB-INF/views/project/list_ajax.jsp";
	}

}
