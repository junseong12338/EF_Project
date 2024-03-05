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
	
	
	// �봽濡쒖젥�듃 湲곌컙�쓣 怨좊젮�븯吏� �븡�� 湲곕낯�쟻�씤 �봽濡쒖젥�듃 由ъ뒪�듃 留듯븨
	// + �떎 �걹�굹怨� 吏꾪뻾�삁�젙, 吏꾪뻾以�, 留덇컧 �굹�닃嫄곗엫
	@RequestMapping("project_list")
	public String project_list(Model model){
		
		// �뒪�겕濡� �씠踰ㅽ듃瑜� �쐞�븳 珥� �럹�씠吏� �닔 ( 珥덇린媛� )
		int total_page_count = 1;
		
		// 諛붿씤�뵫
		model.addAttribute("total_page_count", total_page_count);
		
		// �룷�썙�뵫
		return "/WEB-INF/views/project_list.jsp";
	}
	
	
	@RequestMapping("ajax_list")
	public String ajax_list(Model model,
							@RequestParam(defaultValue = "1", value="pageNum" )int page_num_js,
							@RequestParam(defaultValue = "0", value="sort")int sort_js,
							@RequestParam(required = true, value="category_box[]")List<String> category_js) throws Exception{
		
		ProjectDTO dto = new ProjectDTO();
		
		// �븳 �럹�씠吏��뿉 �뱾�뼱媛� �봽濡쒖젥�듃 �닔
		final int PAGE_PROJECT_COUNT = 12;
		
		// 珥덇린�뿉 �슂泥��씠 �뾾�쓣寃쎌슦 default = 1
		int page_num = 1;
		// �봽濡쒖젥�듃 �떆�옉 由ъ뒪�듃 �꽆踰� ( �럹�씠吏� 踰덊샇 留덈떎 )
		int start_num = 1 + (page_num - 1) * PAGE_PROJECT_COUNT;// 1, 13, 25 ...
		// �봽濡쒖젥�듃 留덉�留� 由ъ뒪�듃 �꽆踰� ( �럹�씠吏� 踰덊샇 留덈떎 )
		int end_num = page_num * PAGE_PROJECT_COUNT;// 12, 24, 36 ...
		// 珥� �봽濡쒖젥�듃 �닔瑜� 援ы븯湲곗쐞�븳 珥� �봽濡쒖젥�듃 �닔
		
		
		// �럹�씠吏�留덈떎 媛��졇�삱 list start ~ end
		dto.setStart(start_num);
		dto.setEnd(end_num);
		
		
		// �젙�젹 諛⑸쾿 ( 0, 1, 2 )
		int sort = 0;// 湲곕낯媛� �씤湲곗닚 
		if(sort_js != 0) {
			sort = sort_js;
		}
		
		dto.setSort(sort);
		
		// 移댄뀒怨좊━ ���옣 List<Integer>
		List<Integer> category = new ArrayList<>();
		if(category_js != null) {
			for(int i = 0; i < category_js.size(); i++) {
				category.add(Integer.parseInt(category_js.get(i)));
			}
		}
		
		dto.setCategory_list(category);
		
		System.out.println(category);
		

		int list_count = projectService.selectOne(dto);
		// �뒪�겕濡� �씠踰ㅽ듃瑜� �쐞�븳 珥� �럹�씠吏� �닔
		int total_page_count = (int)Math.ceil(list_count / (double)PAGE_PROJECT_COUNT);
		
		// 蹂��닔�뿉 �뵲瑜� ajax �봽濡쒖젥�듃 list		
		List<ProjectDTO> list = projectService.selectList(dto);
		
		// ���뵫 珥앷툑�븸, �궓��湲곌컙 蹂��닔 �뀑�똿
		int persent = 0;
		String diff_date = "";
		long diff = 0;
		
		// ���뵫 珥� 湲덉븸
		persent = Integer.parseInt(dto.getProject_donation()) / dto.getProject_target();
		String persent_str  = String.format("%,d %", persent);
		
		
		// �궓�� �궇吏� 諛섑솚
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		// �쁽�옱
		Date now = new Date();
		// �떆�옉
		Date start_date = sdf.parse(dto.getProject_start());
		// �걹
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
			diff_date = "吏꾪뻾 �삁�젙";
		}else if(diff == -1){
			diff_date = "留덇컧";
		}else {
			diff_date = String.format("%d �씪", ( diff / (24 * 60 * 60 * 1000L) ) % 365);
		}
		
		
		// 諛붿씤�뵫
		model.addAttribute("total_page_count", total_page_count);
		model.addAttribute("diff_date", diff_date);
		model.addAttribute("persent", persent_str);
		model.addAttribute("list_count", list_count);
		model.addAttribute("list", list);
		model.addAttribute("page_num", page_num);
//		model.addAttribute("category", dto.getCategory_list());
		
		
		// ajax �룷�썙�뵫
		return "/WEB-INF/views/project_list_ajax.jsp";
	}

}
