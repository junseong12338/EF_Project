package dto;

import lombok.Data;

@Data
public class EFUserDTO {
	private int user_idx; 
	private int user_grade; 
	private int user_status; 
	private int user_point;
	
	private String user_name;  
	private String user_email; 
	private String user_pw; 
	private String  user_img;
	private String user_content;
	private String user_addr;
}
