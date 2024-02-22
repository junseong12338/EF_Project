package dto;

import lombok.Data;

@Data
public class BoardDTO {
	private int idx; //글 번호
	private int readhit; //조회수
	private int ref; //참조글 번호
	private int step; //순서
	private int depth; //댓글 깊이
	private int del_info; //삭제여부
	
	/// 
	private String name; //작성자
	private String subject; //제목
	private String content; //내용
	private String pwd; //비밀번호
	private String  ip; //ip
	private String regdate; //작성날짜
}
