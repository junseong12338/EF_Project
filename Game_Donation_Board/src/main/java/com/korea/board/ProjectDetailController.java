package com.korea.board;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String project_detail(Model model, @RequestParam(required = true, value="dto") ProjectDTO dto) {
		//ex) project_detail?dto = dto;
		
		DetailDTO new_dto = new DetailDTO();
		
		// list_ajax 에서 param으로 보낸 데이터를 새로운 DTO에 저장
		new_dto.setTitle(dto.getProject_title());
		new_dto.setImg(dto.getProject_img());
		new_dto.setDonation(dto.getProject_donation());
		new_dto.setDiff_date(dto.getDiff_date());
		new_dto.setAuthor(dto.getProject_author());
		new_dto.setTarget(dto.getProject_target());
		new_dto.setStart(dto.getProject_start());
		new_dto.setEnd(dto.getProject_end());
		new_dto.setLike(dto.getLike_count());
		new_dto.setContent(dto.getProject_content());
		
		// 좋아요 수정 할 수 있는 ajax
		// 카테고리 정보 - 가져와야함
		// 리뷰 정보 - 가져와야함
		// 공지 정보 - 가져와야함

		
		// 바인딩
		model.addAttribute("dto", dto);
		
		return Common.project.VIEW_PATH + "detail.jsp";
	}
}
