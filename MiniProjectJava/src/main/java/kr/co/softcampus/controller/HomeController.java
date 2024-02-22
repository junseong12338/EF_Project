package kr.co.softcampus.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
//	@Resource(name = "loginUserBean")
//	private UserBean loginUserBean;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request) {
//		System.out.println(loginUserBean);
		
		// 컴퓨터상 물리적 경로 확인
		// 실제 파일 업로드 경로
		// 이클립스상 작업 시 필요


//		System.out.println(request.getServletContext().getRealPath("/"));
		return "redirect:/main";
	}
}

