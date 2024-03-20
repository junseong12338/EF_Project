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

	// sql에서 project_author로 받아와야함
	private String project_author;
	// sql에서 project_donation로 받아와야함 
	private int project_donation;
	// donation String.format
	private String donation_str;
	
	// 카테고리
	private List<Integer> category_list;
	// 케테고리 사이즈
	private int category_list_size;
	// 정렬
	private int sort;
	// 진행여부 날짜
	private int sort_date;
	// 시작, 끝 값으로 불러올 거임
	private int start;
	private int end;
	private int count;
	// 달성 퍼센트, 남은 날짜
	private String persent;
	private String diff_date;
	// like count
	private int like_count;
}
