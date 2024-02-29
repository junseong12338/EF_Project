package dao;

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
	public int update_point(int point) {
		
		return sqlSession.update("project.update_point",point);
					
	}

	// ȸ�� Ż��
	public int delete(int idx) {
		
		return sqlSession.delete("project.user_delete",idx);
	}
		
}
