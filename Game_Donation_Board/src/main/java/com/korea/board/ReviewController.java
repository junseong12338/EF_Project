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
	

	
}
