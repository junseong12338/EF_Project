package service;

import java.util.List;

import dao.ProjectDAO;
import dto.ProjectDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProjectService {

	final ProjectDAO projectDAO;
	
	public ProjectDTO select_project(int idx) {
		return projectDAO.selectOne_project(idx);
	}
	
	//-------------------------------------------------------------- 성현
	
	// list 갯수
	public int selectOne(ProjectDTO dto){
		return projectDAO.select_count(dto);
	}
	
	// list
	public List<ProjectDTO> selectList(ProjectDTO dto){
		return projectDAO.selectList(dto);
	}
	
	// 상세정보
	public ProjectDTO select_detail(int project_idx) {
		return projectDAO.select_detail(project_idx);
	}
	
	//-------------------------------------------------------------- 이준성

	public List<ProjectDTO>	ProjectList(){
		return null;
//		return projectDAO.ProjectList();
	}
}
