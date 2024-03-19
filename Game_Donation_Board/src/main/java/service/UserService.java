package service;

import dao.UserDAO;
import java.util.List;

import dao.UserDAO;
import dto.AdminNoticeDTO;
import dto.DonationDTO;
import dto.PageDTO;
import dto.ProjectDTO;
import dto.UserDTO;
import lombok.RequiredArgsConstructor;
import util.Common;

@RequiredArgsConstructor
public class UserService {
	final UserDAO userDAO;
// --------------------------- 이준성
	public UserDTO checkEmail(String user_email) {
		return userDAO.checkEmail(user_email);
	}
	
	public UserDTO checkSocial(String user_social) {
		return userDAO.checkSocial(user_social);
	}
	public UserDTO selectone(int user_idx) {
		return userDAO.selectone(user_idx);
	}
	
	public int userInsert(UserDTO dto) {
		return userDAO.userInsert(dto);
	}
	
	public int userUpdate(UserDTO dto) {
		return userDAO.userUpdate(dto);
	}
	
	public List<AdminNoticeDTO> admin_notice_list() {
		return userDAO.admin_notice_list();
	}
	
	public PageDTO admin_notice_count(int currentPage) {
		int content_cnt = userDAO.admin_notice_count();
		PageDTO pageDTO = new PageDTO(content_cnt,currentPage,Common.Admin.BLOCKLIST,Common.Admin.BLOCKPAGE);
		return pageDTO;
	}
	
	public AdminNoticeDTO notice_select(int ad_notice_idx) {
		return userDAO.notice_select(ad_notice_idx);
	}
	
	
	// --------------------------- 이준성
	
	public int userDelete(int idx) {
		return userDAO.userDelete(idx);
	}
	
	public int userPointUpdate(UserDTO dto) {
		return userDAO.userPointUpdate(dto);
	}
	
	public UserDTO selectName(int user_idx) {
		return userDAO.selectOne(user_idx);
	}

	
	  public UserDTO selectOne(int idx) {
		  return userDAO.selectOne(idx); 
		  
	  }
	  
	  // --------- 이영찬
	  public List<ProjectDTO> ProjectList(int userIdx) { 
		  // 실제로는 데이터베이스에서 프로젝트 목록을 조회하여 반환하는코드가 들어갑니다. 
		  List<ProjectDTO> list = userDAO.selectProjectList(userIdx);
//		  System.out.println(list); 
		  return list; 
	}
	  
	  // 후원 금액 
	  public List<DonationDTO> donationList() { 
		  List<DonationDTO> list = userDAO.selectdonationList(); 
		  return userDAO.selectdonationList(); 
	}
	  
	//-------------- 정진수
	  
	 //유저 이미지 업데이트
	 public int updateUserImg(UserDTO dto) {
		 
		 return userDAO.userImgUpdate(dto);
	 }
	 
}
