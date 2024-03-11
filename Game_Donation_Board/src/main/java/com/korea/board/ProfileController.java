package com.korea.board;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.scribejava.core.model.Response;

import dto.UserDTO;
import lombok.RequiredArgsConstructor;
import service.UserService;
import util.Common;

@Controller
@RequiredArgsConstructor
public class ProfileController {
	
	final UserService userService;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpSession session;
	
	
	
	

	
	//public final static String VIEW_PATH = "/WEB-INF/views/profile/";
	
	@RequestMapping("mypage_view")
	public String mypage_view() {
		UserDTO userdto = (UserDTO)request.getSession().getAttribute("user_email");
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
	
	@RequestMapping("sponsorshipdetails_view")
	public String sponsorshipdetails_view() {
		
		return Common.profile.VIEW_PATH + "sponsorshipdetails_view.jsp";
	
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
	
	@RequestMapping("delete_account")
    public String deleteAccount(Integer user_idx){  

	    // UserService에 UserDTO 객체를 전달하여 사용자 삭제
	    int res = userService.userDelete(user_idx);
	    System.out.println(res);
	    if (res > 0) {
	        return "redirect:board_list";		
	    }	    
	    return null;
	}
	
	@RequestMapping(value = "user_point_update")
	@ResponseBody
	public String user_point_update(String user_email,int payment) {
		System.out.println(user_email);
		UserDTO dto = userService.checkEmail(user_email);
		int point = dto.getUser_point();
		dto.setUser_point(point+payment);
		int res = userService.userPointUpdate(dto);
		System.out.println(res);
		user_point_session(point+payment);
//		request.getSession().setAttribute("user_email",test);
		
		if (res > 0) {
			
			return "redirect:board_list";
		}
		
		return null;
	}
	
	// 세션에 저장되어있는 포인트 최신화하는 메서드
	public void user_point_session(int user_point) {
		UserDTO test = (UserDTO) request.getSession().getAttribute("user_email");
		test.setUser_point(user_point);
	}

}
	

