package service;

import java.util.HashMap;
import java.util.List;

import dao.ProjectDAO;
import dto.ProjectDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProjectService {
	
	final ProjectDAO projectDAO;
	
	public HashMap<String, Object> selectList(HashMap<String, Object> map){
		
		HashMap<String, Object> selectMap = new HashMap<String, Object>();
		
		// �������� list�� �� ����
		int listTotal = projectDAO.getNowListTotal();
		// �������� list
		List<ProjectDTO> list = projectDAO.selectListNow(map);
		
		selectMap.put("listTotal", listTotal);
		selectMap.put("list", list);
		
		return selectMap;
	}
}
