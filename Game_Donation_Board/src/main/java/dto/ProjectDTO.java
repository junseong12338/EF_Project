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
	
	// column 새로 만들거임
	private String project_subtitle;
	// sql에서 project_author로 받아와야함
	private String project_author;
	// sql에서 project_donation로 받아와야함 
	private int project_donation;
	

	private List<Integer> category_list;
	// 정렬
	private int sort;
	// 시작, 끝 값으로 불러올 거임
	private int start;
	private int end;
	private int count;
	// 달성 퍼센트, 남은 날짜
	private String persent;
	private String diff_date;
}
