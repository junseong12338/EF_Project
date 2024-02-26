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
	public String login(String user_email, String user_pw) {
	
		UserDTO dto = userService.checkEmail(user_email);
		// 이메일 유무 확인 
		if (dto == null) return "[{\"param\":\"no_email\"}]";
		if (!dto.getUser_pw().equals(user_pw)) return "[{\"param\":\"no_pw\"}]";
		
		session.setMaxInactiveInterval(3600);
		session.setAttribute("user_email", dto);
	
		return "[{\"param\":\"clear\"}]";
	}

	@RequestMapping("login_form")
	public String login_form() {
		return Common.User.VIEW_PATH + "login_form.jsp";
	}

	@RequestMapping("logout")
	public String logout() {
		session.removeAttribute("user_email");
		
		return "redirect:board_list";
	}
	
	
	// 회원 가입

	// 회원 가입 값 입력
	@RequestMapping("user_insert")
	public String user_insert(UserDTO dto) {
		int res = userService.userInsert(dto);
		
		if (res > 0) return "redirect:board_list";
		
		return null;
	}
	
	// 아이디 중복 확인
	@RequestMapping("check_email")
	@ResponseBody
	public String check_email(String user_email) {
		UserDTO dto = userService.checkEmail(user_email);
		
		if (dto == null) return "[{\"res\":\"yes\"}]"; 
		
		else return "[{\"res\":\"no\"}]"; 
	}
}
