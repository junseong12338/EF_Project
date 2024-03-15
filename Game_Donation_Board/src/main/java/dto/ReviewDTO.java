package dto;

import lombok.Data;

@Data
public class ReviewDTO {
	private int review_idx,project_idx,user_idx;
	private String review_content, user_name, regdate;
	
	
}
