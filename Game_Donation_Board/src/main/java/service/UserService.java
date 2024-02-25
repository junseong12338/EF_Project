package service;

import dao.UserDAO;
import dto.UserDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserService {
	final UserDAO userDAO;

	public UserDTO check_Email(String userEmail) {
		return userDAO.loginCheck(userEmail);
	}
	
	public int user_insert(UserDTO dto) {
		return userDAO.User_insert(dto);
	}
	
}
