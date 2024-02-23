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


	
	@RequestMapping("login")
	@ResponseBody
	public String login(String email, String pw) {
		UserDTO dto = userService.check_Email(email);

		if (dto == null)
			return "[{'param':'no_id'}]";

		if (!dto.getUserPw().equals(pw))
			return "[{'param':'no_pwd'}]";

		
		session.setMaxInactiveInterval(3600);
		session.setAttribute("email", dto);
		return "[{'param':'clear'}]";
	}


	
	
	@RequestMapping("login_form")
	public String login_form() {
		
		return Common.Member.VIEW_PATH + "login_form.jsp";
	}

	@RequestMapping("logout")
	public String logout() {
		
		session.removeAttribute("email");
		return "redirect:board_list";
	}
	
	@RequestMapping("user_insert")
	public String User_insert(UserDTO dto) {
		int res = userService.User_insert(dto);
		
		if (res > 0)return "redirect:board_list";
		
		return null;

	}
}
