package com.korea.board;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import aspect.ModelAndViewRedirectException;
import dto.AdminInfoDTO;
import dto.AdminNoticeDTO;
import dto.DetailDTO;
import dto.PageDTO;
import dto.ProjectDTO;
import dto.ProjectStatusDTO;
import dto.UserDTO;
import lombok.RequiredArgsConstructor;

import service.ProjectService;
import service.UserService;
import util.Common;


@Controller
@RequiredArgsConstructor
public class AdminController {	
	final ProjectService projectService;	
	final UserService userService;

	@RequestMapping(value = "AdminPage", method = RequestMethod.GET)
	public String getAdminPage(@RequestParam(value = "page", defaultValue = "1") int page,
							   @RequestParam(value = "status",defaultValue = "0")int status,
							   Model model) {
	    int start = (page - 1) * Common.Admin.BLOCKLIST + 1;
	    int end = start + Common.Admin.BLOCKLIST - 1;
		HashMap<String, Integer> map = new HashMap<String, Integer>();

	    map.put("start", start);
	    map.put("end", end);
	    map.put("status",status);
	    
	    PageDTO pageDTO = projectService.getContentCnt(page,status);
	   
	    List<AdminInfoDTO> adminInfo = projectService.getRowTotal(map);
	    PageDTO NoticepageDTO = userService.admin_notice_count(page);
	    
	    List<AdminNoticeDTO> NoticeList = userService.admin_notice_list();
		 

	    model.addAttribute("status", status);
	    model.addAttribute("AdminInfo", adminInfo);
	    model.addAttribute("page", pageDTO);
	    
	    model.addAttribute("Noticepage", NoticepageDTO);
	    model.addAttribute("NoticeList", NoticeList);
	    	    
	    return Common.User.VIEW_PATH + "AdminPage.jsp";
	}
	 
	 
	 @PostMapping("Status")
	 public String handleStatusUpdate(@RequestBody List<ProjectStatusDTO> statusList) {
		 for (ProjectStatusDTO projectStatus : statusList) {
			 
		  	 HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		     int project_idx = projectStatus.getProject_idx();
		     
		     if(projectStatus.getProject_status() == 1) map.put("status", 1);
		     else map.put("status", 0);
		     
		     map.put("project_idx", project_idx);	         
		     projectService.updateStatus(map);
		 }
		 return "redirect:AdminPage";
	 }
	 
	 @RequestMapping(value = "notice", method = RequestMethod.GET)
		public String NoticePage(@RequestParam(value = "page", defaultValue = "1") int page,
								   Model model) {
		    int start = (page - 1) * Common.Admin.BLOCKLIST + 1;
		    int end = start + Common.Admin.BLOCKLIST - 1;
			HashMap<String, Integer> map = new HashMap<String, Integer>();

		    map.put("start", start);
		    map.put("end", end);
		    
		   
		    PageDTO NoticepageDTO = userService.admin_notice_count(page);
		    
		    List<AdminNoticeDTO> NoticeList = userService.admin_notice_list();
			 
		    
		    model.addAttribute("Noticepage", NoticepageDTO);
		    model.addAttribute("NoticeList", NoticeList);
		    	    
		    return Common.User.VIEW_PATH + "notice.jsp";
		}
	 
	 
	 
	 // 공지 사항 상세보기
	 @RequestMapping("notice_detail")
		public String notice_detail(Model model, @RequestParam(required = true, value="ad_notice_idx") int ad_notice_idx, @RequestParam(value = "page", defaultValue = "1") int page) throws Exception {
		 
		 

		 	AdminNoticeDTO dto = userService.notice_select(ad_notice_idx);

			// 바인딩
			model.addAttribute("dto", dto);


			// 포워딩
			return Common.User.VIEW_PATH + "notice_detail.jsp";
		}
	 


	
   //정진수
   //운영자 공지사항 작성 페이지
   @RequestMapping("admin_notice_editor")
   public String adminNoticeEditor() {
     return Common.User.VIEW_PATH + "admin_notice_editor.jsp";
   }	 
   
   
   
  // 로그인체크 로그인이 안걸려있으면 ModelAndViewRedirectException예외생성
	// redirect:/로 이동
  @ExceptionHandler(ModelAndViewRedirectException.class)
  public ModelAndView handleRedirectException(ModelAndViewRedirectException ex) {
      return ex.getModelAndView();
  }
}
