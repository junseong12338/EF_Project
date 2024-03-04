package com.korea.board;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import dto.UserDTO;
import lombok.RequiredArgsConstructor;
import service.UserService;
import util.Common;

@Controller
@RequiredArgsConstructor
public class ProfileController {
	
	final UserService userService;
		
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
	
	@RequestMapping("review")
	public String review() {
		
		return Common.profile.VIEW_PATH + "review.jsp";
	
	}
	
	@RequestMapping("address_update")
	public String address_update(String user_email,String user_addr) {
		System.out.println(user_email);
		UserDTO dto = userService.checkEmail(user_email);
		dto.setUser_addr(user_addr);
		int res = userService.userUpdate(dto);
		System.out.println(res);
		
		if (res > 0) {
			
			return "redirect:board_list";
		}
		
		return null;
	}
//		return Common.profile.VIEW_PATH + "review.jsp";
	}
	

