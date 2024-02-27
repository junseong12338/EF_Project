package com.korea.board;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.BoardDTO;
import dto.UserDTO;
import lombok.RequiredArgsConstructor;
import service.BoardService;
import service.UserService;
import util.Common;
import util.Page;

@Controller
@RequiredArgsConstructor
public class BoardController {

	final BoardService boardService;
	

	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpSession session;

	@RequestMapping(value = { "/", "board_list" })
	public String list(Model model, @RequestParam(required = false, defaultValue = "1") int page) {
		// 한글 깨짐 테스트
		int start = (page - 1) * Common.Board.BLOCKLIST + 1;
		int end = start + Common.Board.BLOCKLIST - 1;

		HashMap<String, Integer> map = new HashMap<>();

		map.put("start", start);
		map.put("end", end);

		HashMap<String, Object> selectMap = boardService.selectList(map);

		int rowTotal = (int) selectMap.get("rowTotal");
		List<BoardDTO> list = (List<BoardDTO>) selectMap.get("list");

		String pageMenu = Page.getPaging("board_list", page, rowTotal, Common.Board.BLOCKLIST, Common.Board.BLOCKPAGE);

		request.getSession().removeAttribute("show");

		model.addAttribute("list", list);
		model.addAttribute("pageMenu", pageMenu);

		return Common.Board.VIEW_PATH + "board_list.jsp?page=" + page;
	}




}
