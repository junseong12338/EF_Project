package com.korea.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@RequestMapping("myinfo_view")
	public String myinfo_view() {
		
		return Common.profile.VIEW_PATH + "myinfo_view.jsp";
	
	}
	
	@RequestMapping("charge_view")
	public String charge_view() {
		
		return Common.profile.VIEW_PATH + "charge_view.jsp";
	
	}
	
	@RequestMapping("Sponsorshipdetails_view")
	public String Sponsorshipdetails_view() {
		
		return Common.profile.VIEW_PATH + "Sponsorshipdetails_view.jsp";
	
	}

}
