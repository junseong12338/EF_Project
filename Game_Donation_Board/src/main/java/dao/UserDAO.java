package dao;

import org.apache.ibatis.session.SqlSession;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserDAO {

		final SqlSession sqlSession;
		
		// ����Ʈ ������Ʈ	
		public int update_point(int point) {
			
			return sqlSession.update("project.update_point",point);
						
		}
		
		// ȸ�� Ż��
		public int delete(int idx) {
			
			return sqlSession.delete("project.user_delete",idx);
		}
		
}
