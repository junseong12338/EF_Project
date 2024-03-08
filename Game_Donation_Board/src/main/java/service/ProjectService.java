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
	
	//--------------------------------------------------------------

	public int selectOne(ProjectDTO dto){
		return projectDAO.select_count(dto);
	}
	

	public List<ProjectDTO> selectList(ProjectDTO dto){
		return projectDAO.selectList(dto);
	}
	//-------------------------------------------------------------- 이준성

	public List<ProjectDTO>	ProjectList(){
		return null;
//		return projectDAO.ProjectList();
	}
}
