package com.korea.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.ReviewDAO;
import dto.ReviewDTO;
import util.Common;

@Controller
public class ReviewController {
	ReviewDAO review_dao;
	
	public ReviewController(ReviewDAO review_dao) {
		this.review_dao = review_dao;
	}
	
	@RequestMapping("review_list")
	public String select(Model model) {
		List<ReviewDTO> list = review_dao.selectList();
		model.addAttribute("list",list);
		return Common.review.VIEW_PATH+"review_list.jsp";
				
	}
	

	@RequestMapping("insert")
	public String insert(ReviewDTO dto, HttpServletRequest request) {
		int res = review_dao.insert(dto);
		return "redirect:review_list";
				
	}
}
