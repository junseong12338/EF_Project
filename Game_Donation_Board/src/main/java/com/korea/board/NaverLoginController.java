package com.korea.board;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class NaverLoginController {

	final UserService userService;
	final NaverLoginService naverLoginService;

	@Autowired
	HttpSession session;
	@Autowired
	HttpServletRequest request;
		
	@RequestMapping(value = "login_form", method = { RequestMethod.GET, RequestMethod.POST })
	public String login_form(Model model) {
		/* 네아로 인증 URL을 생성하기 위하여 naverLoginService클래스의 getAuthorizationUrl메소드 호출 */
		String naverAuthUrl = naverLoginService.getAuthorizationUrl(session);
		/* 인증요청문 확인 */
		//System.out.println("네이버:" + naverAuthUrl);
		/* 객체 바인딩 */
		model.addAttribute("urlNaver", naverAuthUrl);
		/* 생성한 인증 URL을 View로 전달 */
		return Common.User.VIEW_PATH + "login_form.jsp";
	}

	// 네이버 로그인 성공시 callback호출 메소드
	@RequestMapping(value = "/callbackNaver.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String callbackNaver(Model model, @RequestParam(required = false, defaultValue = "") String code,
			@RequestParam(required = false, defaultValue = "") String state,
			@RequestParam(required = false, defaultValue = "") String error,
			@RequestParam(required = false, defaultValue = "") String error_description) throws Exception {


		if (!error.equals(""))
			return "redirect:board_list";
		
	
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginService.getAccessToken(session, code, state); // 토큰


		// access_token= 다음에 오는 값의 시작 인덱스와 종료 인덱스를 찾음
		String tokenString = oauthToken.toString();
		int startIndex = tokenString.indexOf("access_token=") + "access_token=".length();
		int endIndex = tokenString.indexOf(",", startIndex);

		// 시작 인덱스부터 종료 인덱스까지의 부분 문자열을 추출하여 access_token 값을 가져옴
		String accessToken = tokenString.substring(startIndex, endIndex);

		// 로그인 사용자 정보를 읽어온다.
		String apiResult = naverLoginService.getUserProfile(oauthToken);

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj;

		jsonObj = (JSONObject) jsonParser.parse(apiResult);
		JSONObject response_obj = (JSONObject) jsonObj.get("response");
		// 프로필 조회

		String email = (String) response_obj.get("email");
		String name = (String) response_obj.get("name");

		UserDTO dto = userService.checkEmail(email);
		// DB에 정보가 없으면 자동으로 회원 가입
		// 비밀번호는 NULL 유효성 처리로 일반 로그인 불가 + 중복 생성 방지
		if (dto == null) {
			dto = new UserDTO();
			dto.setUser_email(email);
			dto.setUser_name(name);
			dto.setUser_social("N");
			userService.userInsert(dto);

		} else if(!dto.getUser_social().equals("N")){
		
		    return "disconnectNaver?accessToken=" + accessToken;
		}
		// 사용자 정보
		session.setAttribute("user_email", dto);
		// accessToken 생성
		session.setAttribute("accessToken", accessToken);

		// 난수 인증 세션 삭제
		session.removeAttribute(state);

		/* 네이버 로그인 성공 페이지 View 호출 */
		return "redirect:board_list";
	}


	@RequestMapping("disconnectNaver")
	public String disconnectNaver(@RequestParam("accessToken") String accessToken) throws Exception {
		boolean success = naverLoginService.disconnectNaver(accessToken);
		if (success) {
			session.invalidate();
			ResponseEntity.ok("success");
			return Common.Board.VIEW_PATH + "duplication.jsp";
			//return "redirect:duplication"; 
		} else {
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("연동 해제 실패");
			return "redirect:board_list";
		}

	}

}
