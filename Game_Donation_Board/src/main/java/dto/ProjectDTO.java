package dto;

import java.util.List;

import lombok.Data;

@Data
public class ProjectDTO {
	
	private int project_idx;
	private int user_idx;
	private int project_status;
	private int project_target;
	
	private String project_title;
	
	private String project_content;
	private String project_img;
	private String project_start;
	private String project_end;
	
	// DB에 추가해야한다
	private String project_subtitle;
	// 따와야함
	private String project_author;
	// 총 펀딩금액 - sql에서 alias 해서 긁어올 것임
	private String project_donation;
	
	// 카테고리 10개
	private List<Integer> category_list;
	// 정렬방식
	private int sort;
	// list Start,End,Count Num
	private int start;
	private int end;
	private int count;
	
}
