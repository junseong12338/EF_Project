package dao;

import org.apache.ibatis.session.SqlSession;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserDAO {

		final SqlSession sqlSession;
		
		// 포인트 업데이트	
		public int update_point(int point) {
			
			return sqlSession.update("project.update_point",point);
						
		}
		
		// 회원 탈퇴
		public int delete(int idx) {
			
			return sqlSession.delete("project.user_delete",idx);
		}
		
}
