package dto;

import lombok.Data;

@Data
public class DonationDTO {

	private int project_idx;
	private int donation_money;
	private int user_idx;
	private int user_grade;
	
	private String project_title;
	private String project_img;
	private String project_content;
}
