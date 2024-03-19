package dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProjectStatusDTO {
	
    private int project_status; // 프로젝트 상태 (0: 대기중, 1: 승인완료)
    private int project_idx;

	public ProjectStatusDTO(int project_status, int project_idx) {
		
		this.project_status = project_status;
		this.project_idx = project_idx;

	}
	
    

}