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
	
	
	// 占쎈늄嚥≪뮇�젰占쎈뱜 疫꿸퀗而숋옙�뱽 �⑥쥓�젻占쎈릭筌욑옙 占쎈륫占쏙옙 疫꿸퀡�궚占쎌읅占쎌뵥 占쎈늄嚥≪뮇�젰占쎈뱜 �뵳�딅뮞占쎈뱜 筌띾벏釉�
	// + 占쎈뼄 占쎄국占쎄돌�⑨옙 筌욊쑵六억옙�굙占쎌젟, 筌욊쑵六얌빳占�, 筌띾뜃而� 占쎄돌占쎈땭椰꾧퀣�뿫
	@RequestMapping("project_list")
	public String project_list(Model model){
		
		// 占쎈뮞占쎄쾿嚥∽옙 占쎌뵠甕겹끋�뱜�몴占� 占쎌맄占쎈립 �룯占� 占쎈읂占쎌뵠筌욑옙 占쎈땾 ( �룯�뜃由겼첎占� )
		int total_page_count = 1;
		
		// 獄쏅뗄�뵥占쎈뎃
		model.addAttribute("total_page_count", total_page_count);
		
		// 占쎈７占쎌뜖占쎈뎃
		return "/WEB-INF/views/project/list.jsp";
	}
	
	
	@RequestMapping("ajax_list")
	public String ajax_list(Model model,
							@RequestParam(defaultValue = "1", value="pageNum" )int page_num_js,
							@RequestParam(defaultValue = "0", value="sort")int sort_js,
							@RequestParam(required = true, value="category_box[]")List<String> category_js) throws Exception{
		
		// 변수를 넘겨주기위한 dto
		ProjectDTO dto = new ProjectDTO();
		
		// 占쎈립 占쎈읂占쎌뵠筌욑옙占쎈퓠 占쎈굶占쎈선揶쏉옙 占쎈늄嚥≪뮇�젰占쎈뱜 占쎈땾
		final int PAGE_PROJECT_COUNT = 12;
		
		// �룯�뜃由곤옙肉� 占쎌뒄筌ｏ옙占쎌뵠 占쎈씨占쎌뱽野껋럩�뒭 default = 1
		int page_num = 1;
		// 占쎈늄嚥≪뮇�젰占쎈뱜 占쎈뻻占쎌삂 �뵳�딅뮞占쎈뱜 占쎄퐜甕곤옙 ( 占쎈읂占쎌뵠筌욑옙 甕곕뜇�깈 筌띾뜄�뼄 )
		int start_num = 1 + (page_num - 1) * PAGE_PROJECT_COUNT;// 1, 13, 25 ...
		// 占쎈늄嚥≪뮇�젰占쎈뱜 筌띾뜆占쏙쭕占� �뵳�딅뮞占쎈뱜 占쎄퐜甕곤옙 ( 占쎈읂占쎌뵠筌욑옙 甕곕뜇�깈 筌띾뜄�뼄 )
		int end_num = page_num * PAGE_PROJECT_COUNT;// 12, 24, 36 ...
		// �룯占� 占쎈늄嚥≪뮇�젰占쎈뱜 占쎈땾�몴占� �뤃�뗫릭疫꿸퀣�맄占쎈립 �룯占� 占쎈늄嚥≪뮇�젰占쎈뱜 占쎈땾
		
		
		// 占쎈읂占쎌뵠筌욑옙筌띾뜄�뼄 揶쏉옙占쎌죬占쎌궞 list start ~ end
		dto.setStart(start_num);
		dto.setEnd(end_num);
		
		
		// 占쎌젟占쎌졊 獄쎻뫖苡� ( 0, 1, 2 )
		int sort = 0;// 疫꿸퀡�궚揶쏉옙 占쎌뵥疫꿸퀣�떄 
		if(sort_js != 0) {
			sort = sort_js;
		}
		
		dto.setSort(sort);
		
		// 燁삳똾�믤�⑥쥓�봺 占쏙옙占쎌삢 List<Integer>
		List<Integer> category = new ArrayList<>();
		if(category_js != null) {
			for(int i = 0; i < category_js.size(); i++) {
				category.add(Integer.parseInt(category_js.get(i)));
			}
		}
		
		dto.setCategory_list(category);
		
		System.out.println(category);
		System.out.println(sort);
		

		int list_count = projectService.selectOne(dto);
		// 占쎈뮞占쎄쾿嚥∽옙 占쎌뵠甕겹끋�뱜�몴占� 占쎌맄占쎈립 �룯占� 占쎈읂占쎌뵠筌욑옙 占쎈땾
		int total_page_count = (int)Math.ceil(list_count / (double)PAGE_PROJECT_COUNT);
		
		// 癰귨옙占쎈땾占쎈퓠 占쎈뎡�몴占� ajax 占쎈늄嚥≪뮇�젰占쎈뱜 list		
		List<ProjectDTO> list = projectService.selectList(dto);
		
		// 占쏙옙占쎈뎃 �룯�빓�닊占쎈만, 占쎄텚占쏙옙疫꿸퀗而� 癰귨옙占쎈땾 占쎈�묕옙�샒
		int persent = 0;
		String diff_date = "";
		long diff = 0;
		
		// 占쎄텚占쏙옙 占쎄텊筌욑옙 獄쏆꼹�넎
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		// 占쎌겱占쎌삺
		Date now = new Date();
		
		
		for(int i = 0; i < list.size(); i++) {
			// 퍼센트 dto.setPersent
			persent = list.get(i).getProject_donation() / list.get(i).getProject_target();
			String persent_str  = String.format("%,d", persent);
			list.get(i).setPersent(persent_str + " %");
			
			// 남은기간 dto.setDiff_date
			Date start_date = sdf.parse(list.get(i).getProject_start());
			// 占쎄국
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
