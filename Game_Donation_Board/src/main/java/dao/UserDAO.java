package dao;

import org.apache.ibatis.session.SqlSession;

import dto.UserDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserDAO {
	
	final SqlSession sqlSession;

	public UserDTO loginCheck(String userEmail) {

		return sqlSession.selectOne("u.loginCheck",userEmail);
	}
	
	public int User_insert(UserDTO dto) {
		return sqlSession.insert("u.insert",dto);
	}
	
	

}
