package util;

public class Common {
	
	//일반 게시판용
	
	public static class Board{
		public final static String VIEW_PATH = "/WEB-INF/views/board/";
		//한페이지에 보여줄 게시물 개수
		public final static int BLOCKLIST = 10;
		//페이지 메뉴 수
		public final static int BLOCKPAGE = 3;
	}
	
	
	//공지사항 게시판용
	public static class Notice{
		//한 페이지에서 보여줄 게시물 수
		public final static int BLOCKLIST = 20;
		public final static int BLOCKPAGE = 5;
	}
	// 회원 경로
	public static class Member{
		public final static String VIEW_PATH = "/WEB-INF/views/member/";
	}
}
