package dto;

import lombok.Data;

@Data
public class ReviewDTO {

	private int user_idx;
	private int project_idx;
	
	private String user_img;
	private String user_name;
	
	private String content;
	private String regdate;
	
	// regdate - sysdate
	private String diff_date;
}
