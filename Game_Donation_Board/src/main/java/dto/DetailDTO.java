package dto;

import lombok.Data;

@Data
public class DetailDTO {

	private String title;
	private String img;
	
	// 총 후원금액
	private int donation;
	// 남은기간
	private String diff_date;
	// 작성자
	private String author;
	// 목표금액
	private int target;
	// 펀딩기간
	private String start;
	private String end;
	
	// like 수
	private int like;
	
	// 내용
	private String content;
	
	
}
