package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectMainListDTO {
	
	private int project_idx;
	private int user_idx;
	private int project_status;
	private String project_title;
	private String project_img;
	private String project_start;
	private String project_end;
	private String user_name;
	private String user_img;
	private String project_target;
	private int donation_money;
	private int project_like;
	
}
