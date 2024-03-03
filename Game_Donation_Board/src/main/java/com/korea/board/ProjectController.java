package com.korea.board;

import java.text.SimpleDateFormat;
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
	
	@RequestMapping("project_test")
	public String project_view(Model model) {
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		ProjectDTO dto = projectService.select_project(idx);
		
		model.addAttribute("dto",dto);
		
		return Common.Board.VIEW_PATH + "project_view.jsp";
	}
	@Autowired
	HttpSession session;
	
	// �������� ������Ʈ ����
	@RequestMapping("project_list")
	public String project_list(Model model){
		
		// ���������� 12���� ǥ���� ��
		final int PAGE_PROJECT_COUNT = 12;
		
		// ������
		int page_num = 1;//(�⺻ ������ 1�� ����)
		
		int start_num = 1 + (page_num - 1) * PAGE_PROJECT_COUNT;
		int end_num = start_num + PAGE_PROJECT_COUNT;
		int count = PAGE_PROJECT_COUNT;
		
		
		int total_page_count = (int)Math.ceil(count / (double)PAGE_PROJECT_COUNT);
		
		// ���ε� - list �� ����, �� ������ ��ȯ
		
		// listcount 가 ajax로 가야댐 (수정해야댐)
		model.addAttribute("total_page_count", total_page_count);
		
		// ������
		return "/WEB-INF/views/project_list.jsp";
	}
	
	
	@RequestMapping("ajax_list")
	public String ajax_list(Model model,
							@RequestParam(defaultValue = "1", value="pageNum" )int page_num_js,
							@RequestParam(defaultValue = "0", value="sort")int sort_js,
							@RequestParam(value="category_box")List<Integer> category_js) throws Exception{
		
		ProjectDTO dto = new ProjectDTO();
		
		// ���������� 12���� ǥ���� ��
		final int PAGE_PROJECT_COUNT = 12;
		
		// ������
		int page_num = 1;//(�⺻ ������ 1�� ����)
		if(page_num_js != 0) {
			page_num = page_num_js; 
		}
		
		int start_num = 1 + (page_num - 1) * PAGE_PROJECT_COUNT;
		int end_num = start_num + PAGE_PROJECT_COUNT;
		int count = PAGE_PROJECT_COUNT;
		
		dto.setStart(start_num);
		dto.setEnd(end_num);
		dto.setCount(count);
		
		
		
		// ���Ĺ��
		int sort = 0;//(�α�� �⺻����)
		if(sort_js != 0) {
			sort = sort_js;
		}
		
		dto.setSort(sort);
		
		// ī�װ���
		List<Integer> category = null;//( ī�װ��� ���� �������� ����)
		if(category_js != null) {
			category = category_js;
		}
		
		dto.setCategory_list(category);
		
		System.out.println(category);
		
		// ��������� ajax_list �� ����
		// ī�װ���, ����, start,end,count�� ������ ������ �޾ƿ���. (�Խù� List)
		int list_count = projectService.selectOne(dto);
		List<ProjectDTO> list = projectService.selectList(dto);
		
		// �޼� �ۼ�Ʈ, ���� �Ⱓ
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
		
		// ������
		model.addAttribute("diff_date", diff_date);
		model.addAttribute("persent", persent_str);
		model.addAttribute("list_count", list_count);
		model.addAttribute("list", list);
		model.addAttribute("page_num", page_num);
		
		
		
		return "/WEB-INF/views/project_list_ajax.jsp";
	}

}
