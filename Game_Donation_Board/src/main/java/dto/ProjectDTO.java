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
}
