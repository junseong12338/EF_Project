package service;

import java.util.HashMap;
import java.util.List;

import dao.ProjectDAO;
import dto.ProjectDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProjectService {
	
	final ProjectDAO projectDAO;
	
	// 전체 list의 갯수
	public int selectOne(ProjectDTO dto){
		return projectDAO.get_list_count(dto);
	}
	
	// 전체 프로젝트 리스트 ( 인기순으로 초기 값 잡자 )
	public List<ProjectDTO> selectList(ProjectDTO dto){
		return projectDAO.selectList(dto);
	}
}
