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

		int start = (page - 1) * Common.Board.BLOCKLIST + 1;
		int end = start + Common.Board.BLOCKLIST - 1;

		HashMap<String, Integer> map = new HashMap<>();

		map.put("start", start);
		map.put("end", end);

		HashMap<String, Object> selectMap = boardService.selectList(map);

		int rowTotal = (int) selectMap.get("rowTotal");
		List<BoardDTO> list = (List<BoardDTO>) selectMap.get("list");

		// 페이지 메뉴 생성하기
		String pageMenu = Page.getPaging("board_list", page, rowTotal, Common.Board.BLOCKLIST, Common.Board.BLOCKPAGE);

		// 새로고침 마구 클릭하여 조회수가 증가하지 못하도록 세션 사용하기
		request.getSession().removeAttribute("show");

		model.addAttribute("list", list);
		model.addAttribute("pageMenu", pageMenu);

		return Common.Board.VIEW_PATH + "board_list.jsp?page=" + page;
	}

	// 게시글 상세보기
	@RequestMapping("view")
	public String view(Model model, int idx, int page) {

		// 게시글 한건에 대한 조회
		BoardDTO dto = boardService.selectOne(idx);

		// 조회수 증가
		HttpSession session = request.getSession();
		String show = (String) session.getAttribute("show");// 없으면 null
		if (show == null) {
			int res = boardService.update_readhit(idx);
			session.setAttribute("show", "0");
		}

		// 상세보기 페이지로 전환하기 위한 바인딩과 포워딩
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

	// 답변 등록
	@RequestMapping("reply_form")
	public String reply_from(int idx, int page) {
		return Common.Board.VIEW_PATH + "reply_form.jsp?idx=" + idx + "&page=" + page;
	}

	@RequestMapping("reply")
	public String reply(BoardDTO dto, int idx, int page) {
		String ip = request.getRemoteAddr();

		// 같은 레퍼런스를 가지고 있는 데이터들 중에서 지금 내가 추가하려고 하는
		// step값 이상인 애들을 +1을 해놔야 하기 때문에 insert를 먼저하지 않는다.

		// 기준글의 idx를 이용해서 댓글을 달고싶은 게시글의 정보를 가져온다.

		BoardDTO base_dto = boardService.selectOne(idx);

		// 기준글에 step이상 값은 step = step + 1 처리
		int res = boardService.update_step(base_dto); // -> dao에 만들러 가기

		dto.setIp(ip);

		// 댓글이 들어갈 위치 선정
		dto.setRef(base_dto.getRef());
		dto.setStep(base_dto.getStep() + 1);
		dto.setDepth(base_dto.getDepth() + 1);

		res = boardService.reply(dto);

		if (res > 0)
			return "redirect:board_list?page=" + page;

		return null;
	}

	// 게시글 삭제
	@RequestMapping("del")
	public String del(int idx) {
		// 게시글 한건에 대한 조회
		BoardDTO dto = boardService.selectOne(idx);
		int res = boardService.del(idx);

		if (res > 0)
			return "redirect:board_list";
		return null;

	}

	// 로그인
	@RequestMapping("login")
	@ResponseBody
	public String login(String id, String pwd) {
		MemberDTO dto = boardService.check_id(id);

		// dto가 null 일경우 아이디가 없다는 뜻
		if (dto == null)
			return "[{'param':'no_id'}]";

		if (!dto.getPwd().equals(pwd))
			return "[{'param':'no_pwd'}]";

		// 아이디와 비밀번호 체크에 문제가 없다면 세션에 바인딩한다.
		// 세션은 서버의 메모리를 사용하기 때문에 많이 사용할 수록 성능 저하.
		// 필요한 경우에서만 사용하자.
		session.setMaxInactiveInterval(3600);
		session.setAttribute("id", dto);
		return "[{'param':'clear'}]";
	}

	@RequestMapping("login_form")
	public String login_form() {
		return Common.Member.VIEW_PATH + "login_form.jsp";

	}

	// 로그아웃
	@RequestMapping("logout")
	public String logout() {
		session.removeAttribute("id");

		return "redirect:board_list";
	}

	// 회원 가입 아이디 중복 처리
	@RequestMapping("check_id")
	@ResponseBody
	public String check_id(String id) {
		MemberDTO dto = boardService.check_id(id);

		// dto가 null 일경우 아이디가 없다는 뜻
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
