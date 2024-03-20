package com.korea.board;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.NoticeDAO;
import dto.NoticeDTO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NoticeController {

	final NoticeDAO notice_dao;

	
//	  @RequestMapping("notice_list") 
//	  public String select(Model model) {
//		  List<NoticeDTO> list = notice_dao.selectList();
//		  model.addAttribute("list",list); 
//		  return "/WEB-INF/views/notice/notice_list.jsp";	
//		  
//	  }
//	  
//	  @RequestMapping("insert") 
//	  public String insert(String user_name,String notice_content) { 
//		  
//		  System.out.println(user_name);
//	  
//		 return "redirect:notice_list"; 
//	 }
//	  
//	  @RequestMapping("notice_insert_form")
//		 public String imsert_form(String user_name,String notice_content) {
//			 return  "/WEB-INF/views/notice/notice_insert_form.jsp";
//	 }
//	 
//	  @RequestMapping("delete")
//	  public String delete(int idx, String pwd) {
//		  HashMap<String, Object> map = new HashMap<String, Object>();
//		  map.put("idx", idx);
//		  map.put("pwd", pwd);
//		  
//		  
//				  
//	  }
}
