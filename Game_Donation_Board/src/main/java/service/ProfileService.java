package service;

import dao.ProfileDAO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProfileService {

	final ProfileDAO profileDAO;
	
	  public int reviewCount(int user_idx) {
		  return profileDAO.reviewCount(user_idx);
	  }
	  
	  public int sponsored_Project_Details(int user_idx) {
		  return profileDAO.sponsored_Project_Details(user_idx);
	  }
	  
	  public int registered_project(int user_idx) {
		  return profileDAO.Registered_project(user_idx);
	  }
	
}
