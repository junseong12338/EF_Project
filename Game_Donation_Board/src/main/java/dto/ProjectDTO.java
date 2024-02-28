package dto;

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
	
	
	// 카테고리 10개
	boolean category_01 = false;
	boolean category_02 = false;
	boolean category_03 = false;
	boolean category_04 = false;
	boolean category_05 = false;
	boolean category_06 = false;
	boolean category_07 = false;
	boolean category_08 = false;
	boolean category_09 = false;
	boolean category_10 = false;
	
	
}
