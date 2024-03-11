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
	
	//-------------------------------------------------------------- 성현 list.jsp , list_ajax.jsp
	
	// list 갯수
	public int selectOne(ProjectDTO dto){
		return projectDAO.select_count(dto);
	}
	
	// list
	public List<ProjectDTO> selectList(ProjectDTO dto){
		
		
		List<ProjectDTO> list = projectDAO.selectList(dto);
		
		for(int i = 0; i < list.size(); i++) {
			// user table 에서 user_name 가져와서 setting
			String name = projectDAO.selectName(list.get(i).getUser_idx());
			list.get(i).setProject_author(name);
		}
		
		return list;
	}
	
	//-------------------------------------------------------------- 이준성

	public List<ProjectDTO>	ProjectList(){
		return null;
//		return projectDAO.ProjectList();
	}
}
