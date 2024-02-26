package dao;

import org.apache.ibatis.session.SqlSession;

import dto.MemberDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberDAO {
	
	final SqlSession sqlSession;

	public  MemberDTO loginCheck(String id) {
		
		return sqlSession.selectOne("m.loginCheck",id);
	}

	public int member_insert(MemberDTO dto) {
		
		
		return sqlSession.insert("m.insert",dto);
	}
	

	public int select_user(int idx) {
		return sqlSession.delete("m.select_user",idx);
	}

}
