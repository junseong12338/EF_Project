package dao;

import org.apache.ibatis.session.SqlSession;

import dto.UserDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserDAO {
	
	final SqlSession sqlSession;

	public UserDTO loginCheck(String email) {
		return sqlSession.selectOne("u.loginCheck",email);
	}

	public int User_insert(UserDTO dto) {
		return sqlSession.insert("u.insert",dto);
	}
	
	

}
