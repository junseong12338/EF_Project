package dto;

import lombok.Data;

@Data
public class NoticeDTO {

	private int notice_idx;
	private int project_idx;
	
	private String notice_title;
	private String notice_content;
	private String user_name;
}
