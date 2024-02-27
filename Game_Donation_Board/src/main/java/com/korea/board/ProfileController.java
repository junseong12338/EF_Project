package com.korea.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

import util.Common;

@Controller
@RequiredArgsConstructor
public class ProfileController {
	
	
//	@Autowired
//	HttpServletRequest request;
//	
//	@Autowired
//	HttpSession session;
	
	//public final static String VIEW_PATH = "/WEB-INF/views/profile/";
	
	@RequestMapping("mypage_view")
	public String mypage_view() {
		
		return Common.profile.VIEW_PATH + "mypage_view.jsp";
		

	}
}
