package dto;

import java.util.List;

import lombok.Data;

@Data
public class DetailDTO {
	
	private int user_idx;
	private int project_idx;
	
	private String title;
	private String img;
	
	// 총 후원금액
	private int donation;
	private String donation_str;
	// 남은기간
	private String diff_date;
	// 작성자
	private String author;
	// 목표금액
	private int target;
	private String target_str;
	// 달성 퍼센트
	private String persent;
	// 펀딩기간
	private String start;
	private String end;
	// 카테고리 이름
	private List<String> category_name;

	// like 수
	private int like_cnt;
	// 현재 사용자가 좋아요를 누른건지(1) 아닌지(0)
	private int is_heart;
	
}
