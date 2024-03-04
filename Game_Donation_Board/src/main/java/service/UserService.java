package service;

import java.util.HashMap;

import dao.UserDAO;
import dto.UserDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserService {
	final UserDAO userDAO;

	public UserDTO checkEmail(String user_email) {
		return userDAO.checkEmail(user_email);
	}
	
	public int userInsert(UserDTO dto) {
		return userDAO.userInsert(dto);
	}
	
	public int userUpdate(UserDTO dto) {
		return userDAO.userUpdate(dto);
	}
	
}
