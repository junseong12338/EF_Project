package com.korea.board;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.DetailDTO;
import dto.ProjectDTO;
import dto.ReviewDTO;
import dto.UserDTO;

import dto.DetailDTO;
import dto.ProjectDTO;
import lombok.RequiredArgsConstructor;
import service.ProjectService;
import util.Common;

@Controller
@RequiredArgsConstructor
public class ProjectDetailController {
	
	final ProjectService projectService;
	
	@Autowired
	HttpSession session;
	
	@RequestMapping("project_detail")
	public String project_detail(Model model, @RequestParam(required = true, value="project_idx") int project_idx) throws Exception {
		//ex) project_detail?project_idx = 23;
		
		// 반환할 데이터를 담을 객체
		DetailDTO result_dto = new DetailDTO(); 
		
		// project테이블 의 정보를 가져오기 (project_idx)
		// user_idx, project_idx, title, img, target, start, end
		ProjectDTO dto = projectService.selectOne(project_idx);
		
		result_dto.setUser_idx(dto.getUser_idx());
		result_dto.setProject_idx(dto.getProject_idx());
		result_dto.setTitle(dto.getProject_title());
		result_dto.setImg(dto.getProject_img());
		//target -> target_str ( 형식 입력 )
		String target_str = String.format("%,d", dto.getProject_target());
		result_dto.setTarget_str(target_str);
		
		result_dto.setStart(dto.getProject_start());
		result_dto.setEnd(dto.getProject_end());
		
		// donation 테이블의 정보 가져오기 - sum (project_idx)
		// donation, persent, diff_date
		HashMap<String, Object> map = projectService.selectMap(project_idx);
		
		//donation -> donation_str ( 형식 입력 )
		String donation_str = String.format("%,d", map.get("donation")); 
		result_dto.setDonation_str(donation_str);
		result_dto.setPersent( (String)map.get("persent") );
		result_dto.setDiff_date( (String)map.get("diff_date") );
		
		// user 테이블의 정보 가져오기 (project_idx)
		// author
		String name = projectService.selectName(project_idx);
		
		result_dto.setAuthor(name);
		
		// category_name 테이블의 정보 가져오기 (project_idx)
		// category_name - List<String>
		List<String> list = projectService.selectList(project_idx);
		
		result_dto.setCategory_name(list);
		
		// like 테이블의 정보 가져오기 (project_idx)
		// like_cnt (sum)
		int like = projectService.selectLike(project_idx);
		
		result_dto.setLike_cnt(like);
		
		// --------------------------------- heart check
		// 로그인을 했는지 체크
		// jsp 에서 session에 저장된 정보를 dto 불러서 사용할 것
		// 좋아요를 누른건지 체크
		UserDTO dto_user = (UserDTO)session.getAttribute("user_email");
		
		if(dto_user != null) {
			
			// 자기 자신의 포인트 ( 형식 설정 )
			String point_str = String.format("%,d", dto_user.getUser_point());
			
			model.addAttribute("point_str", point_str);
			
			int user_idx = dto_user.getUser_idx();
			
			// project_idx, user_idx(사용자) 두개의 변수를 sql에 보내야하므로 HashMap ㄱㄱ
			HashMap<String, Object> map_idx = new HashMap<String, Object>();
			map_idx.put("project_idx", project_idx);
			map_idx.put("user_idx", user_idx);
			
			// like 테이블에서 두개의 idx와 같은 행 개수 가져오기
			int res = projectService.selectOne(map_idx);
			
			// 좋아요를 눌럿다면 1 아니면 0
			if(res == 1) {
				result_dto.setIs_heart(res);// 1
			}else {
				result_dto.setIs_heart(res);// 0
			}
			
		}
		

		// 바인딩
		model.addAttribute("dto", result_dto);
		
		// 포워딩
		return Common.project.VIEW_PATH + "detail.jsp";
	}
	
	
	// content ajax controller
	@RequestMapping("detail_ajax")
	public String ajax_detail(Model model, @RequestParam(value="wanted") int wanted,
											@RequestParam(value="project_idx") int project_idx) {
		// ajax_detail?wanted=1&project_idx=3
		
		// content
		ProjectDTO dto = projectService.selectOne(project_idx);
		String content = dto.getProject_content();
		
		// review
		List<ReviewDTO> list = projectService.selectList_review(project_idx);
		
		
		model.addAttribute("wanted", wanted);
		model.addAttribute("content", content);
		model.addAttribute("list", list);
		
		return Common.project.VIEW_PATH + "detail_ajax.jsp";
	}
	
	// review register
	@RequestMapping("review_register")
	@ResponseBody
	public void review_register(@RequestParam(value="user_idx") int user_idx,
								@RequestParam(value="project_idx") int project_idx,
								@RequestParam(value="input_content") String input_content) {
		
		// 등록
		int res = projectService.insert_review(user_idx, project_idx, input_content);
		
		if(res > 0) {
			System.out.println("등록 성공");
		}else {
			System.out.println("등록 실패");
		}
	}
	
	
	
	// 좋아요 ajax
//	@SuppressWarnings("unchecked")
	@RequestMapping("heart_ajax")
	@ResponseBody
	public JSONObject heart_plus_ajax(Model model, @RequestParam(value="project_idx") int project_idx,
											@RequestParam(value="heart_check") int heart_check) {
		// heart : 1 -> 하트 수 증가, -1 -> 하트 수 감소, 0 -> 데이터가 안왔다
		
		System.out.println("heart start");
		
		UserDTO dto_user = (UserDTO)session.getAttribute("user_email");
		int user_idx = dto_user.getUser_idx();
		
		HashMap<String, Object> map_idx = new HashMap<String, Object>();
		map_idx.put("project_idx", project_idx);
		map_idx.put("user_idx", user_idx);
		
		int res = 0;
		int like = 0;
		JSONObject obj = new JSONObject();
		
		if(heart_check == 1) {// 하트 수 증가
			res = projectService.insert_heart(map_idx);
			like = projectService.selectLike(project_idx);
			obj.put("heart", like);
		}else if(heart_check == -1) {
			res = projectService.delete_heart(map_idx);
			like = projectService.selectLike(project_idx);
			obj.put("heart", like);
		}
		
		return obj;
	}
	
	
	@RequestMapping("donation")
	public String donation(Model model, int user_idx, int use_point, int project_idx, int user_point) {
		// donation?user_idx=11&user_point=1234&project_idx=321
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("user_idx", user_idx);
		map.put("use_point", use_point);
		map.put("project_idx", project_idx);
		
		int diff_point = (int)(user_point - use_point);
		map.put("diff_point", diff_point);
		
		
		
		// EF_DONATION 테이블의 insert, update
		// 사용자가 이 프로젝트에 후원한적이 있는지 체크
		int yes = projectService.select_used(map);
		int result = 0;
		
		// 후원한 적 YES
		if(yes > 0) {
			result = projectService.update_donation(map);
		}else {// 후원한 적 NO
			result = projectService.insert_donation(map);
		};
		
		
		
		if(result > 0) {
			// EF_USER 테이블의 user_point 차감 - update
			int res = projectService.update_point(map);
			if(res > 0) {
				System.out.println("point update 성공");
			}else {
				System.out.println("point update 실패");
			};
			System.out.println("donation 성공");
		}else {
			System.out.println("donation 실패");
		}
		
		return "redirect:project_detail?project_idx=" + project_idx;
	}
	
}
