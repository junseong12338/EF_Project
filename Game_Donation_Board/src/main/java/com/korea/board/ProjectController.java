package com.korea.board;

import javax.servlet.http.HttpServletRequest;
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
	public String project_list(Model model,
								@RequestParam(value="pageNum", defaultValue="1")int page_num_js,
								@RequestParam(value="sort",defaultValue="0")int sort_js,
								@RequestParam(value="category_box",required = false)List<Integer> category_js
								) throws Exception{
		
		
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
		
		// ��������� ajax_list �� ����
		// ī�װ���, ����, start,end,count�� ������ ������ �޾ƿ���. (�� ����)
		// ������Ʈ(list) �� ����
		int list_count = projectService.selectOne(dto);
		int total_page_count = (int)Math.ceil(count / (double)PAGE_PROJECT_COUNT);
		
		// ���ε� - list �� ����, �� ������ ��ȯ
		model.addAttribute("list_count", list_count);
		model.addAttribute("total_page_count", total_page_count);
		
		// ������
		return "/WEB-INF/views/project_list.jsp";
	}
	
	
	@RequestMapping("ajax_list")
	public String ajax_list(Model model,
							@RequestParam(value="pageNum")int page_num_js,
							@RequestParam(value="sort")int sort_js,
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
		
		// ��������� ajax_list �� ����
		// ī�װ���, ����, start,end,count�� ������ ������ �޾ƿ���. (�Խù� List)
		List<ProjectDTO> list = projectService.selectList(dto);
		
		// �޼� �ۼ�Ʈ, ���� �Ⱓ
		int persent = 0;
		int date = 0;
		
		persent = Integer.parseInt(dto.getProject_donation()) / dto.getProject_target();
		String.format("%,d__ %", persent);
		
		
		
		// ������
		model.addAttribute("list", list);
		
		return Common.Board.VIEW_PATH + "project_list_ajax.jsp";
	}

}
