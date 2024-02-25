package com.korea.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.UserDTO;
import lombok.RequiredArgsConstructor;
import service.UserService;
import util.Common;

@Controller
@RequiredArgsConstructor
public class UserController {
	final UserService userService;
	
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpSession session;

	// 로그인 로그아웃
	@RequestMapping("login")
	@ResponseBody
	public String login(String userEmail, String userPw) {
	
		UserDTO dto = userService.check_Email(userEmail);
		
		// 이메일 유무 확인 
		if (dto == null) return "[{'param':'no_email'}]";
		if (!dto.getUSER_PW().equals(userPw)) return "[{'param':'no_pw'}]";
		
		session.setMaxInactiveInterval(3600);
		session.setAttribute("userEmail", dto);
		
		return "[{'param':'clear'}]";
	}

	@RequestMapping("login_form")
	public String login_form() {
		return Common.Member.VIEW_PATH + "login_form.jsp";
	}

	@RequestMapping("logout")
	public String logout() {
		session.removeAttribute("userEmail");
		
		return "redirect:board_list";
	}
	
	
	// 회원 가입

	// 회원 가입 값 입력
	@RequestMapping("user_insert")
	public String user_insert(UserDTO dto) {
		int res = userService.user_insert(dto);
		
		if (res > 0) return "redirect:board_list";
		
		return null;
	}
	
	// 아이디 중복 확인
	@RequestMapping("check_Email")
	@ResponseBody
	public String check_id(String userEmail) {
		UserDTO dto = userService.check_Email(userEmail);
		
		if (dto == null) return "[{\"res\":\"yes\"}]"; 
		
		else return "[{\"res\":\"no\"}]"; 
	}
}
