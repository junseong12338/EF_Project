package com.korea.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import util.Common;

@Controller
public class BoardController {

	@RequestMapping(value = { "/", "board_list" })
	public String list() {
		// 한글 깨짐 테스트
		return Common.Board.VIEW_PATH + "board_list.jsp";
	}




}
