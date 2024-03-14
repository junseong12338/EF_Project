package com.korea.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.ReviewDAO;
import dto.ReviewDTO;
import lombok.RequiredArgsConstructor;
import service.ReviewService;
import util.Common;

@Controller
@RequiredArgsConstructor
public class ReviewController {

	final ReviewService reviewService;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpSession session;
	
	@RequestMapping("review_list")
	public String select(Model model,ReviewDTO dto) {
		List<ReviewDTO> list = reviewService.selectList();
		model.addAttribute("list",list);
		return Common.review.VIEW_PATH+"review_list.jsp";
				
	}
	

	@RequestMapping("insert")
	public String insert(ReviewDTO dto) {
		int res = reviewService.reviewInsert(dto);
		
		if(res > 0) {
			return "redirect:review_list";
		}
		return null;
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public String delete(int review_idx) {
		int res = reviewService.reviewDelete(review_idx);
		
		String result="no";

		if( res == 1) {
			result="yes";
		}

		String finRes= String.format("[{'res':'%s'}]",result);
		
		return finRes;
	}
	
}
