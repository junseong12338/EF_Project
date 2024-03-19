package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.NoticeDTO;

public class NoticeDAO {

	SqlSession sqlSession;
	
	public NoticeDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	
	// 공지사항 전체 조회
	public List<NoticeDTO> selectList(){
		return sqlSession.selectOne("notice.select_notice");
	}
	
	// 공지사항 추가
	public int insert_notice(NoticeDTO dto) {
		return sqlSession.insert("notice.notice_update",dto);
		
	}
	
	// 공지사항 삭제
	public int delete(HashMap<String, Object> map) {
		return sqlSession.delete("notice.notice_delete",map);
	}
	
}
