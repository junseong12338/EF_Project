package service;

import java.util.HashMap;
import java.util.List;

import dao.BoardDAO;
import dao.MemberDAO;
import dto.BoardDTO;
import dto.EF_UserDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardService {

	final BoardDAO boardDAO;
	final MemberDAO memberDAO;
	public HashMap<String, Object> selectList( HashMap<String, Integer> map){
		
		HashMap<String, Object> selectMap = new HashMap<>();
		
		List<BoardDTO> list = boardDAO.selectList(map);
		
		int rowTotal = boardDAO.getRowTotal();
		
		selectMap.put("list", list);
		selectMap.put("rowTotal", rowTotal);
		
		return selectMap;
	}
	
	
	public BoardDTO selectOne(int idx) {
		return boardDAO.selectOne(idx);
	}
	
	public int update_readhit(int idx) {
		return boardDAO.update_readhit(idx);
	}
	
	public int insert(BoardDTO dto) {
		return boardDAO.insert(dto); 
	}
	
	public int del(int idx) {
		return boardDAO.del(idx);
	}
	
	public int update_step(BoardDTO base_dto) {
		return boardDAO.update_step(base_dto);
	}


	public int reply(BoardDTO dto) {
		return boardDAO.reply(dto);
	}


	public EF_UserDTO check_id(String id) {
		return memberDAO.loginCheck(id);
	}
	
	public int Member_insert(EF_UserDTO dto) {
		return memberDAO.member_insert(dto);
	}
	
	


	
	
	
}