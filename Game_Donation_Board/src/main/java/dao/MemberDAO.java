package dao;

import org.apache.ibatis.session.SqlSession;

import dto.EF_UserDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberDAO {
	
	final SqlSession sqlSession;

	public  EF_UserDTO loginCheck(String id) {
		
		return sqlSession.selectOne("m.loginCheck",id);
	}

	public int member_insert(EF_UserDTO dto) {
		
		
		return sqlSession.insert("m.insert",dto);
	}
	
	

}
