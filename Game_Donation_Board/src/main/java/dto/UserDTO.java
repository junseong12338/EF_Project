package dto;

import lombok.Data;

@Data
public class UserDTO {
	
	private int userIdx;
	private int userGrade;
					
	private String userEmail;
	private String userName;
	private String userPw;
	private String userImg;
	private int userStatus;
	private String userContent;
	private String userAddr;
	private int userPoint;
	
}

