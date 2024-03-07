package com.korea.board;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dto.UserDTO;
import lombok.RequiredArgsConstructor;
import service.KakaoLoginService;
import service.UserService;
import util.Common;

@Controller
@RequiredArgsConstructor
public class KakaoLoginController {
	final UserService userService;
	final KakaoLoginService kakaoLoginService;

	@RequestMapping(value = "/kakaoLogin", method = RequestMethod.GET)
	public String kakaoLogin(@RequestParam(value = "code", required = false) String code, HttpSession session)
			throws Exception {
		System.out.println("#########" + code);

		// 위에서 만든 코드 아래에 코드 추가
		String access_Token = kakaoLoginService.getAccessToken(code);
		System.out.println("###access_Token#### : " + access_Token);

		HashMap<String, Object> userInfo = kakaoLoginService.getUserInfo(access_Token);
		System.out.println("###access_Token#### : " + access_Token);
		System.out.println("###nickname#### : " + userInfo.get("nickname"));
		System.out.println("###email#### : " + userInfo.get("email"));

		session.setAttribute("access_Token", access_Token);
		
		String email = (String) userInfo.get("email");
		String name = (String) userInfo.get("nickname");
		UserDTO dto = userService.checkEmail(email);

		// DB에 정보가 없으면 자동으로 회원 가입
		// 비밀번호는 NULL 유효성 처리로 일반 로그인 불가 + 중복 생성 방지
		if (dto == null) {

			dto = new UserDTO();
			dto.setUser_email(email);
			dto.setUser_name(name);
			dto.setUser_social("K");
			userService.userInsert(dto);

		} else if(!dto.getUser_social().equals("K")){
		    return "unlinkKakaoAccount?accessToken=" + access_Token;
		}
		
		// 사용자 정보
		session.setAttribute("user_email", dto);

		return "redirect:board_list";

	}
	
	
	@RequestMapping("unlinkKakaoAccount")
    public String unlinkKakaoAccount(@RequestParam String accessToken) {
        try {
        	ResponseEntity.ok(kakaoLoginService.unlinkKakaoAccount(accessToken));
			return Common.Board.VIEW_PATH + "duplication.jsp";

        } catch (Exception e) {
            e.printStackTrace();
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to unlink Kakao account: " + e.getMessage());
			return Common.Board.VIEW_PATH + "duplication.jsp";

        }
    }

	

}
