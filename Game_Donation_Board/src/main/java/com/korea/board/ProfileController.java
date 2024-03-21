package com.korea.board;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.DonationDTO;
import dto.ProjectDTO;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.scribejava.core.model.Response;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dto.DonationDTO;
import dto.ProjectDTO;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.scribejava.core.model.Response;
import com.google.gson.JsonObject;

import aspect.ModelAndViewRedirectException;



import dto.ReviewDTO;
import dto.UserDTO;
import lombok.RequiredArgsConstructor;
import service.ProfileService;
import service.UserService;
import util.Common;

@Controller
@RequiredArgsConstructor
public class ProfileController {

	final UserService userService;
	final ProfileService profileService;

	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpSession session;

  //Common클래스의 img_root값 넣어주기 각컴퓨터의 이미지 경로
	final String contextRoot = Common.img_root.IMG_ROOT;
	
	//public final static String VIEW_PATH = "/WEB-INF/views/profile/";
	
	// 로그인체크 로그인이 안걸려있으면 ModelAndViewRedirectException예외생성
	// redirect:/로 이동
    @ExceptionHandler(ModelAndViewRedirectException.class)
    public ModelAndView handleRedirectException(ModelAndViewRedirectException ex) {
        return ex.getModelAndView();
    }
	
	
	@RequestMapping("mypage_view")
	public String mypage_view(Model model) {
		UserDTO userdto = (UserDTO)request.getSession().getAttribute("user_email");
//		 리뷰 , 내가 등록한 프로젝트, 후원한 프로젝트,
		int user_idx = userdto.getUser_idx();
		int review = profileService.reviewCount(user_idx);
		int sponsored_Project_Details = profileService.sponsored_Project_Details(user_idx);
		int registered_project = profileService.registered_project(user_idx); 
	    
//	  가져온 각각의 갯수를 Model에 담아서 View로 전달
		model.addAttribute("dto",userdto);
	    model.addAttribute("reviewCount", review);
	    model.addAttribute("registered_project", registered_project);
	    model.addAttribute("sponsored_Project_Details", sponsored_Project_Details);
	    
		return Common.profile.VIEW_PATH + "mypage_view.jsp";

	}
	

	@RequestMapping("myinfo_view")
	public String myinfo_view(Model model) {
		UserDTO userDTO = (UserDTO)request.getSession().getAttribute("user_email");
			
		String email = userDTO.getUser_email();
		String name = userDTO.getUser_name();
		model.addAttribute("user",userDTO);
		model.addAttribute("email",email);
		model.addAttribute("name",name);
		return Common.profile.VIEW_PATH + "myinfo_view.jsp";
	
	}

	@RequestMapping("charge_view")
	public String charge_view() {
		
		return Common.profile.VIEW_PATH + "charge_view.jsp";
	
	}
	
	
	@RequestMapping("notice_list")
	public String notice() {

		return "/WEB-INF/views/notice/notice_list.jsp";
	
	}
	
	 @RequestMapping("insert") 
	  public String insert(String user_name,String notice_content) { 		  
	  
		 return "redirect:notice_list"; 
	 }
	
	 @RequestMapping("notice_insert_form")
	 public String imsert_form(String user_name,String notice_content) {
		 return  "/WEB-INF/views/notice/notice_insert_form.jsp";
	 }
	 
	@RequestMapping("review")
	public String review(Model model, @RequestParam(value = "user_idx") int user_idx) {
		
		List<ReviewDTO> EF_REVIEW = userService.reviewList(user_idx);	
		model.addAttribute("reviewList", EF_REVIEW);
		return Common.profile.VIEW_PATH + "review.jsp";
	
	}
	
	@RequestMapping("address_update")

	public String address_update(String user_name,String user_addr,String user_email) {
		UserDTO dto = userService.checkEmail(user_email);	
		dto.setUser_addr(user_addr);
		dto.setUser_name(user_name);
		int res = userService.userUpdate(dto);

		if (res > 0) {

			
			user_session(user_name,user_addr);
				
			
			return "redirect:board_list";
		}

		return null;
	}

	@RequestMapping("delete_account")
	public String deleteAccount(int user_idx) {

		// UserService에 UserDTO 객체를 전달하여 사용자 삭제
		int res = userService.userDelete(user_idx);
		
		if (res > 0) {
			return "redirect:board_list";
		}
		return null;
	}
	
	  @RequestMapping("registered_Project") 
	  public String getProjectList(Model model,  @RequestParam(value = "user_idx") int user_idx) { 
	  List<ProjectDTO> EF_PROJCET = userService.ProjectList(user_idx);
	  
	   //모델에 프로젝트 목록 추가
	   model.addAttribute("projectList", EF_PROJCET);
	  
	   //프로젝트 목록 페이지로 포워딩 
	   return Common.profile.VIEW_PATH +"registered_Project.jsp"; 
	}
	  
	  // 후원 한 프로젝트
	  @RequestMapping("sponsorshipdetails_view") 
	  public String sponsorshipdetails_view(Model model, @RequestParam(value = "user_idx") int user_idx) { 
		  
	  List<DonationDTO> EF_DONATION = userService.donationList(user_idx);
	  
	  model.addAttribute("donationlist", EF_DONATION);
	  
	  return Common.profile.VIEW_PATH +"sponsorshipdetails_view.jsp";
	  
	  }
	
	@RequestMapping(value = "user_point_update")
	@ResponseBody
	public String user_point_update(String user_email,int payment) {
		UserDTO dto = userService.checkEmail(user_email);
		int point = dto.getUser_point();
		dto.setUser_point(point+payment);
		int res = userService.userPointUpdate(dto);
		user_point_session(point+payment);

		
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

	//정진수
	
	//이미지 선택 메서드
	@RequestMapping(value="/user_img_upload", produces = "application/json; charset=utf8")
	@ResponseBody
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile)  {
		JsonObject jsonObject = new JsonObject();
		String userEmail = ((UserDTO)request.getSession().getAttribute("user_email")).getUser_email();
		
		// 실질적인 파일 저장루트, 파일이름에 session으로 받은 유저 이메일을 이미지 이름에 포함하여 관리
		String fileRoot = contextRoot+"user/"+userEmail;
		
		//파일의 본래이름
		String originalFileName = multipartFile.getOriginalFilename();
		//파일의 확장자 분리 후 저장
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
		//파일이름을 암호화한 저장할 이름 설정
		String savedFileName = UUID.randomUUID() + extension;	
		
		File targetFile = new File(fileRoot + savedFileName);	
		try(InputStream fileStream = multipartFile.getInputStream();) {
				
			//지정된 경로에 파일 저장
			FileUtils.copyInputStreamToFile(fileStream, targetFile);	
			
			
			// 화면에 출력할 이미지 경로 설정
			jsonObject.addProperty("url","/ef_project_img/user/"+userEmail+savedFileName);
			
			jsonObject.addProperty("responseCode", "success");
				
		} catch (IOException e) {
			//예외 발생 시 등록된 파일 제거
			FileUtils.deleteQuietly(targetFile);	
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		}
		System.out.println(jsonObject.get("url"));
		String a = jsonObject.toString();
		
		
		return a;
	}

	
	// 세션에 저장되어있는 포인트 최신화하는 메서드
	public void user_session(String user_name,String user_addr) {
		UserDTO test = (UserDTO) request.getSession().getAttribute("user_email");
		test.setUser_addr(user_addr);
		test.setUser_name(user_name);
	}

	
	//이미지 업데이트 메서드
	@RequestMapping("user_img_update")
	public String user_img_update(String user_main_img) {
		
		System.out.println(user_main_img);
		int user_idx = ((UserDTO)request.getSession().getAttribute("user_email")).getUser_idx();
		
		UserDTO dto = new UserDTO();
		
		dto.setUser_idx(user_idx);
		dto.setUser_img(user_main_img);
		
		
		int res = userService.updateUserImg(dto);
		
		if(res > 0) {
			String user_folder = contextRoot + "user/";
			String user_img_name = user_main_img.substring(user_main_img.indexOf("/ef_project_img/user/") + "/ef_project_img/user/".length());
			removeDummyFiles(getFileNamesFromFolder(user_folder, user_img_name), user_folder);
			
			//이미지 등록 후 세션 업데이트
			UserDTO resDTO = userService.selectone(user_idx);
			
			session.invalidate();
			session.setAttribute("user_email", resDTO);
		}
		
		return "redirect:mypage_view";		
	}
	
	//유저이미지 삭제(기본이미지로 업데이트) 메서드
	@RequestMapping("user_img_delete")
	public String user_img_delete(){
		int user_idx = ((UserDTO)request.getSession().getAttribute("user_email")).getUser_idx();
		UserDTO dto = new UserDTO();
		
		dto.setUser_idx(user_idx);
		dto.setUser_img("resources/assets/images/user.png");
		
		int res = userService.updateUserImg(dto);
		
		if(res > 0) {
			String user_folder = contextRoot + "user/";
			String user_img_name = "";
			removeDummyFiles(getFileNamesFromFolder(user_folder, user_img_name), user_folder);
			
			//이미지 등록 후 세션 업데이트
			UserDTO resDTO = userService.selectone(user_idx);
			
			session.invalidate();
			session.setAttribute("user_email", resDTO);
		}
		
		return "redirect:mypage_view";		
	}
	
	// user폴더 안 session에 저장된 userEmail값이 이름에 포함된 이미지파일 이름을 리스트로 반환
	private List<String> getFileNamesFromFolder(String folderName,String user_img_name) {
	    // 파일이름을 담을 리스트 객체
	    List<String> fileNames = new ArrayList<>();
	    //세션에 저장된 userEmail
	    String userEmail = ((UserDTO)request.getSession().getAttribute("user_email")).getUser_email();
	    
	    // 폴더 경로 설정
	    File folder = new File(folderName);
	    // 폴더 안 파일이름을 배열로 받아옴
	    File[] files = folder.listFiles();
	    if (files != null) {
	        // 파일이름 배열안 파일이름이 userEmail을 포함하고 user_main_img에 포함되어있지 않다면 반환할 리스트에 저장
	        for (File file : files) {
	            if (file.isFile()) {
	            	if(!(user_img_name.isEmpty()))
	            	{
	            		if(file.getName().contains(userEmail) && !(file.getName().contains(user_img_name))) {
	            			fileNames.add(file.getName());	            		
	            		}	            		
	            	}else {
	            		if(file.getName().contains(userEmail)) {
	            			fileNames.add(file.getName());	            		
	            		}
	            	}
	            }
	        }
	    }
	    // 파일이름 리스트 반환
	    return fileNames;
	}
	
	// user폴더 안 더미데이터 삭제
	private void removeDummyFiles(List<String> fileNames, String filePath) {
	    // 파일이름 리스트 반복
	    for (String fileName : fileNames) {
	        // filePath안 filname이름의 파일 삭제
	        
	            deleteFile(filePath, fileName);
	        
	    }
	}
	
	// 이미지 한건 삭제 메서드
		private void deleteFile(String filePath, String fileName) {
		    Path path = Paths.get(filePath, fileName);
		    try {
		        Files.delete(path);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
	
	
}
	

