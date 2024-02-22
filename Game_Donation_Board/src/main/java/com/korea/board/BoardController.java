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
import dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import service.BoardService;
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

	@RequestMapping("view")
	public String view(Model model, int idx, int page) {

		BoardDTO dto = boardService.selectOne(idx);

		HttpSession session = request.getSession();
		String show = (String) session.getAttribute("show");
		if (show == null) {
			int res = boardService.update_readhit(idx);
			session.setAttribute("show", "0");
		}

		model.addAttribute("dto", dto);
		return Common.Board.VIEW_PATH + "board_view.jsp?page=" + page;
	}

	@RequestMapping("insert_form")
	public String insert_form() {
		MemberDTO show = (MemberDTO) session.getAttribute("id");
		if (show == null)
			return Common.Member.VIEW_PATH + "login_form.jsp";

		return Common.Board.VIEW_PATH + "insert_form.jsp";
	}

	@RequestMapping("insert")
	public String insert(BoardDTO dto) {

		String ip = request.getRemoteAddr();
		dto.setIp(ip);
		int res = boardService.insert(dto);

		if (res > 0)
			return "redirect:board_list";
		return null;

	}

	// �亯 ���
	@RequestMapping("reply_form")
	public String reply_from(int idx, int page) {
		return Common.Board.VIEW_PATH + "reply_form.jsp?idx=" + idx + "&page=" + page;
	}

	@RequestMapping("reply")
	public String reply(BoardDTO dto, int idx, int page) {
		String ip = request.getRemoteAddr();

		

		BoardDTO base_dto = boardService.selectOne(idx);

		
		int res = boardService.update_step(base_dto); 
		dto.setIp(ip);

	
		dto.setRef(base_dto.getRef());
		dto.setStep(base_dto.getStep() + 1);
		dto.setDepth(base_dto.getDepth() + 1);

		res = boardService.reply(dto);

		if (res > 0)
			return "redirect:board_list?page=" + page;

		return null;
	}

	
	@RequestMapping("del")
	public String del(int idx) {
	
		BoardDTO dto = boardService.selectOne(idx);
		int res = boardService.del(idx);

		if (res > 0)
			return "redirect:board_list";
		return null;

	}

	
	@RequestMapping("login")
	@ResponseBody
	public String login(String id, String pwd) {
		MemberDTO dto = boardService.check_id(id);

	
		if (dto == null)
			return "[{'param':'no_id'}]";

		if (!dto.getPwd().equals(pwd))
			return "[{'param':'no_pwd'}]";

		
		session.setMaxInactiveInterval(3600);
		session.setAttribute("id", dto);
		return "[{'param':'clear'}]";
	}

	@RequestMapping("login_form")
	public String login_form() {
		return Common.Member.VIEW_PATH + "login_form.jsp";

	}


	@RequestMapping("logout")
	public String logout() {
		session.removeAttribute("id");

		return "redirect:board_list";
	}

	
	@RequestMapping("check_id")
	@ResponseBody
	public String check_id(String id) {
		MemberDTO dto = boardService.check_id(id);

		
		if (dto == null)
			return "[{'res':'yes'}]";

		return "[{'res':'no'}]";
	}
	
	@RequestMapping("member_insert_form")
	public String member_insert_form() {
		return Common.Member.VIEW_PATH + "member_insert_form.jsp";
	}
	
	@RequestMapping("member_insert")
	public String member_insert(MemberDTO dto) {
		int res = boardService.Member_insert(dto);
		if (res > 0)return "redirect:board_list";
		
		return null;

	}

}