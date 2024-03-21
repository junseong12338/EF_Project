package util;

public class Common {
	// 경로를 관리함
	public static class Board{
		public final static String VIEW_PATH = "/WEB-INF/views/board/";
		public final static int BLOCKLIST = 10;
		public final static int BLOCKPAGE = 5;
	}
	
	
	public static class Notice{
		public final static int BLOCKLIST = 20;
		public final static int BLOCKPAGE = 5;
	}
	public static class User{
		public final static String VIEW_PATH = "/WEB-INF/views/user/";
	}
	
	public static class profile{
		public final static String VIEW_PATH = "/WEB-INF/views/profile/";
	}
	
	public static class project{
		public final static String VIEW_PATH = "/WEB-INF/views/project/";
	}
	
	public static class img_root{
		//각자 컴퓨터 이미지 기본경로 설정 
		//예: C:\\Users\\admin\\Desktop\\EF_work\\EF_Project\\util\\ef_project_img : 이준성 학원 경로
	    // C:\\Users\\junhyuk\\Desktop\\이준성\\공부\\GitHub\\EF_Project\\util\\ef_project_img" : 이준성 집 
		// C:\\jjs_project\\spring\\koricWorkspace\\EF_Project\\util\\ef_project_img : 정진수 노트북
		public final static String IMG_ROOT = "C:\\Users\\admin\\Desktop\\EF_work\\EF_Project\\util\\ef_project_img\\";
	}
	
	
	//공지사항 게시판용
	public static class Admin{
		//한 페이지에서 보여줄 게시물 수 6개 0부터 시작 그래서 5개
		public final static int BLOCKLIST = 6;
		public final static int BLOCKPAGE = 5;
	}
}
