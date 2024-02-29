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
	
	// ��ü list�� ����
	public int selectOne(ProjectDTO dto){
		return projectDAO.get_list_count(dto);
	}
	
	// ��ü ������Ʈ ����Ʈ ( �α������ �ʱ� �� ���� )
	public List<ProjectDTO> selectList(ProjectDTO dto){
		return projectDAO.selectList(dto);
	}
}
