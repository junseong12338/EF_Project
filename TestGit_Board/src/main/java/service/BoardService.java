package service;

import java.util.HashMap;
import java.util.List;

import dao.BoardDAO;
import dao.MemberDAO;
import dto.BoardDTO;
import dto.MemberDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardService {

	final BoardDAO boardDAO;
	final MemberDAO memberDAO;
	//페이지별 게시글 조회
	public HashMap<String, Object> selectList( HashMap<String, Integer> map){
		
		HashMap<String, Object> selectMap = new HashMap<>();
		
		//페이지 번호에 따른 전체 게시글 조회
		List<BoardDTO> list = boardDAO.selectList(map);
		
		//전체 게시물 수 조회
		int rowTotal = boardDAO.getRowTotal();
		
		selectMap.put("list", list);
		selectMap.put("rowTotal", rowTotal);
		
		return selectMap;
	}
	
	
	//게시글 상세 조회
	public BoardDTO selectOne(int idx) {
		return boardDAO.selectOne(idx);
	}
	
	//조회수 증가
	public int update_readhit(int idx) {
		return boardDAO.update_readhit(idx);
	}
	
	//게시글 추가하기
	public int insert(BoardDTO dto) {
		return boardDAO.insert(dto); 
	}
	
	// 게시글 삭제
	public int del(int idx) {
		return boardDAO.del(idx);
	}
	
	public int update_step(BoardDTO base_dto) {
		return boardDAO.update_step(base_dto);
	}


	public int reply(BoardDTO dto) {
		return boardDAO.reply(dto);
	}


	public MemberDTO check_id(String id) {
		return memberDAO.loginCheck(id);
	}
	
	public int Member_insert(MemberDTO dto) {
		return memberDAO.member_insert(dto);
	}
	
	


	
	
	
}