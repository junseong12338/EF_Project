package com.korea.board;

import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.DetailDTO;
import dto.ProjectDTO;
import lombok.RequiredArgsConstructor;
import service.ProjectService;
import util.Common;

@Controller
@RequiredArgsConstructor
public class ProjectDetailController {
	
	final ProjectService projectService;
	
	@RequestMapping("project_detail")
	public String project_detail(Model model, @RequestParam(required = true, value="project_idx") int project_idx) {
		//ex) project_detail?project_idx = 23;
		
		// 반환할 데이터를 담을 객체
		DetailDTO result_dto = new DetailDTO(); 
		
		// project테이블 의 정보를 가져오기 (project_idx)
		// user_idx, project_idx, title, img, target, start, end
		ProjectDTO dto = projectService.selectOne(project_idx);
		
		result_dto.setUser_idx(dto.getUser_idx());
		result_dto.setProject_idx(dto.getProject_idx());
		result_dto.setTitle(dto.getProject_title());
		result_dto.setImg(dto.getProject_img());
		result_dto.setTarget(dto.getProject_target());
		result_dto.setStart(dto.getProject_start());
		result_dto.setEnd(dto.getProject_end());
		
		// donation 테이블의 정보 가져오기 - sum (project_idx)
		// donation, persent, diff_date
		HashMap<String, Object> map = projectService.selectMap(project_idx);
		
		result_dto.setDonation( (int)map.get("donation") );
		result_dto.setPersent( (String)map.get("persent") );
		result_dto.setDiff_date( (String)map.get("diff_date") );
		
		// user 테이블의 정보 가져오기 (project_idx)
		// author
		String name = projectService.selectName(project_idx);
		
		result_dto.setAuthor(name);
		
		// category_name 테이블의 정보 가져오기 (project_idx)
		// category_name - List<String>
		List<String> list = projectService.selectList(project_idx);
		
		result_dto.setCategory_name(list);
		
		// like 테이블의 정보 가져오기 (project_idx)
		// like_cnt (sum)
		int like = projectService.selectLike(project_idx);
		
		result_dto.setLike_cnt(like);

		// 바인딩
		model.addAttribute("dto", result_dto);
		
		// 포워딩
		return Common.project.VIEW_PATH + "detail.jsp";
	}
	
	
	// content ajax controller
	@RequestMapping("ajax_detail")
	public String ajax_detail(Model model, @RequestParam(value="wanted") int wanted,
											@RequestParam(value="project_idx") int project_idx) {
		// ajax_detail?wanted=1&project_idx=3
		
		ProjectDTO dto = projectService.selectOne(project_idx);
		String content = dto.getProject_content();
		
		model.addAttribute("wanted", wanted);
		model.addAttribute("content", content);
//		model.addAttribute("review", review);
		
		return Common.project.VIEW_PATH + "detail_ajax.jsp";
	}
	
	
	// 좋아요 수정 할 수 있는 ajax
	@RequestMapping("heart_ajax")
	@ResponseBody
	public String heart_ajax(Model model) {
		
		// 로그인을 했는지 체크
		// 좋아요를 누른건지 체크
		// 좋아요 추가, 취소
		
		
		return "";
	}
}
