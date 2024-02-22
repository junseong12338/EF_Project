package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.BoardDTO;
import dto.MemberDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardDAO {
	
	final SqlSession sqlSession;

	//페이지별로 게시글 조회
	public List<BoardDTO> selectList(HashMap<String, Integer> map){
		List<BoardDTO> list = sqlSession.selectList("b.board_list", map);
		return list;
	}

	//전체 게시물 수 조회
	public int getRowTotal() {
		int count = sqlSession.selectOne("b.board_count");
		return count;
	}
	
	
	public BoardDTO selectOne (int idx) {
		return  sqlSession.selectOne("b.board_one", idx);
	}
	
	public int update_readhit(int idx) {
		return sqlSession.update("b.update_readhit",idx);
	}
	
	// 게시글 추가
	public int insert(BoardDTO dto) {
		return sqlSession.insert("b.board_insert",dto);
	}
	
	//댓글추가
	public int reply(BoardDTO dto) {
		int res = sqlSession.insert("b.board_reply",dto);
		return res;
	}

	
	//댓글추가를 위한 step+1
	public int update_step(BoardDTO dto) {
		int res = sqlSession.update("b.board_update_step",dto);
		return res;
	}
	
	// 게시글 삭제
	public int del(int idx) {
		return sqlSession.delete("b.board_delete",idx);
	}


}
