package com.korea.board;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.scribejava.core.model.OAuth2AccessToken;

import dto.UserDTO;
import lombok.RequiredArgsConstructor;
import service.NaverLoginService;
import service.UserService;
import util.Common;

@Controller
@RequiredArgsConstructor
public class NaverController {
	
	final UserService userService;
	/* naverLoginService */
	final NaverLoginService naverLoginService;
	
	private String apiResult = null;
		
	// 로그인페이지
	//로그인 첫 화면 요청 메소드
	
	
	
	@RequestMapping(value = "login_form", method = { RequestMethod.GET, RequestMethod.POST })
	public String login_form(Model model, HttpSession session) {
		System.out.println("로그인로그인");
		/* 네아로 인증 URL을 생성하기 위하여 naverLoginService클래스의 getAuthorizationUrl메소드 호출 */
		String naverAuthUrl = naverLoginService.getAuthorizationUrl(session);
		/* 인증요청문 확인 */
		System.out.println("네이버:" + naverAuthUrl);
		/* 객체 바인딩 */
		model.addAttribute("urlNaver", naverAuthUrl);

		/* 생성한 인증 URL을 View로 전달 */
		return Common.User.VIEW_PATH + "login_form.jsp";
	}
	
	//네이버 로그인 성공시 callback호출 메소드
	@RequestMapping(value = "/callbackNaver.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String callbackNaver(Model model, @RequestParam String code, @RequestParam String state, HttpSession session)
			throws Exception {
		System.out.println("로그인 성공 callbackNaver");
		OAuth2AccessToken oauthToken;
        oauthToken = naverLoginService.getAccessToken(session, code, state); // 토큰 
        //로그인 사용자 정보를 읽어온다.
	    apiResult = naverLoginService.getUserProfile(oauthToken);
	    
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj;
		
		jsonObj = (JSONObject) jsonParser.parse(apiResult);
		JSONObject response_obj = (JSONObject) jsonObj.get("response");			
		// 프로필 조회
		
		String email = (String) response_obj.get("email");
		String name = (String) response_obj.get("name");
		
		System.out.println(email);
		System.out.println(name);
		UserDTO dto = userService.checkEmail(email);
		
		System.out.println(dto);
		if(dto == null) {
			dto.setUser_email(email);
			dto.setUser_name(name);
			
			int res = userService.userInsert(dto);
		}
		
		// 세션에 사용자 정보 등록
		// session.setAttribute("islogin_r", "Y");
		session.setAttribute("signIn", apiResult);
		session.setAttribute("user_email", dto);
	    session.setAttribute("accessToken", oauthToken); // 토큰 저장

        /* 네이버 로그인 성공 페이지 View 호출 */
		return "redirect:board_list";
	}
    
	// 소셜 로그인 성공 페이지
	
	// 로그아웃 -> UserController 
	
	
	

	
}
