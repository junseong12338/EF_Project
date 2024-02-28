package service;

import java.util.HashMap;
import java.util.List;

import dao.ProjectDAO;
import dto.ProjectDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProjectService {
	
	final ProjectDAO projectDAO;
	
	// ��ü list�� ����
	public int selectOne(){
		return projectDAO.get_list_count();
	}
	
	// ��ü ������Ʈ ����Ʈ ( �α������ �ʱ� �� ���� )
	public List<ProjectDTO> selectList(HashMap<String, Object> map){
		return projectDAO.selectList(map);
	}
}
