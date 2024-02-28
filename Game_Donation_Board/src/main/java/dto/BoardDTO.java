package dto;

import lombok.Data;

@Data
public class BoardDTO {
	private int idx; 
	private int readhit; 
	private int ref;
	private int step; 
	private int depth; 
	private int del_info; 
	
	private String name;  
	
	
	private String subject; 
	private String pwd; 
	private String  ip; 
	private String regdate; 
}
