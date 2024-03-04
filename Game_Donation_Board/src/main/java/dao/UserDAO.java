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
	
	public int userInsert(UserDTO dto) {
		return sqlSession.insert("u.insert",dto);
	}
	// ����Ʈ ������Ʈ	
	public int update_point(int userId, int point) {
		
		Map<String, Integer> params = new HashMap<>();
	    params.put("userId", userId);
	    params.put("point", point);
	    return sqlSession.update("u.update_point_by_user_id", params);
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
