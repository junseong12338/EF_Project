package dto;

import java.util.List;

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
	
	// DB�� �߰��ؾ��Ѵ�
	private String project_subtitle;
	// ���;���
	private String project_author;
	// �� �ݵ��ݾ� - sql���� alias �ؼ� �ܾ�� ����
	private int project_donation;
	
	// ī�װ��� 10��
	private List<Integer> category_list;
	// ���Ĺ��
	private int sort;
	// list Start,End,Count Num
	private int start;
	private int end;
	private int count;
	
	private String persent;
	private String diff_date;
}
