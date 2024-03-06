package dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.UserDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserDAO {
	
	final SqlSession sqlSession;

	public UserDTO checkEmail(String user_email) {

		return sqlSession.selectOne("u.checkEmail",user_email);
	}
	public UserDTO selectone(int user_idx) {

		return sqlSession.selectOne("u.selectone",user_idx);
	}
	
	public int userInsert(UserDTO dto) {
		return sqlSession.insert("u.insert",dto);
	}
	// ����Ʈ ������Ʈ	
	public int update_point(UserDTO dto) {
		System.out.println("DAO"+dto);
	    return sqlSession.update("u.update_point", dto);
	}

	// ȸ�� Ż��
	public int userDelete(int idx) {
		
		return sqlSession.delete("u.user_delete",idx);
	}
	
	public int userUpdate(UserDTO dto) {
		System.out.println("DAO"+dto);
	    return sqlSession.update("u.update", dto);
	}

		
}
