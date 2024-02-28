package com.korea.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



import com.github.scribejava.core.model.OAuth2AccessToken;


@Controller
public class CityTurtleController {

	/* NaverLoginBO */
	private NaverLoginBO naverLoginBO;
	private String apiResult = null;
	
	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}
	
	// 로그인페이지
	//로그인 첫 화면 요청 메소드
	
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(Model model, HttpSession session) {
		
		/* 네아로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		/* 인증요청문 확인 */
		System.out.println("네이버:" + naverAuthUrl);
		/* 객체 바인딩 */
		model.addAttribute("urlNaver", naverAuthUrl);

		/* 생성한 인증 URL을 View로 전달 */
		return "login";
	}
	
	//네이버 로그인 성공시 callback호출 메소드
	@RequestMapping(value = "/callbackNaver.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String callbackNaver(Model model, @RequestParam String code, @RequestParam String state, HttpSession session)
			throws Exception {
		System.out.println("로그인 성공 callbackNaver");
		OAuth2AccessToken oauthToken;
        oauthToken = naverLoginBO.getAccessToken(session, code, state); // 토큰 
        //로그인 사용자 정보를 읽어온다.
	    apiResult = naverLoginBO.getUserProfile(oauthToken);
	    
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj;
		
		jsonObj = (JSONObject) jsonParser.parse(apiResult);
		JSONObject response_obj = (JSONObject) jsonObj.get("response");			
		// 프로필 조회
		String email = (String) response_obj.get("email");
		String name = (String) response_obj.get("name");
		// 세션에 사용자 정보 등록
		// session.setAttribute("islogin_r", "Y");
		session.setAttribute("signIn", apiResult);
		session.setAttribute("email", email);
		session.setAttribute("name", name);
	    session.setAttribute("accessToken", oauthToken); // 토큰 저장

        /* 네이버 로그인 성공 페이지 View 호출 */
		return "redirect:/loginSuccess.do";
	}
    
	// 소셜 로그인 성공 페이지
	@RequestMapping("/loginSuccess.do")
	public String loginSuccess() {
		return "loginSuccess";
	}
	
	
	
	
	
	 @RequestMapping("/logout.do")
	    public String logout(HttpSession session) {
	        // 세션에서 토큰을 삭제합니다.
		 System.out.println("로그아웃 완료");
	        session.removeAttribute("accessToken");
	    	session.removeAttribute("signIn");
	        // 추가적으로 필요한 작업을 수행할 수 있습니다.
	        
	    	 return "redirect:/login.do";
	    }
	

}