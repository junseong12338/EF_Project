package service;

import java.util.HashMap;
import java.util.List;


import dao.ProjectDAO;
import dto.PageDTO;
import dto.ProjectDTO;
import lombok.RequiredArgsConstructor;
import util.Common;

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
	
	public List<ProjectDTO>	ProjectList(){
		
		return projectDAO.ProjectList();
	}

	
	//-------------------------------------------------------------- 이준성
	//전체 게시물 수 조회
	public List<ProjectDTO> getRowTotal(HashMap<String, Integer> map){
		return projectDAO.getRowTotal(map);
	}

	public PageDTO getContentCnt(int currentPage) {
		int content_cnt = projectDAO.project_all_count();
		PageDTO pageDTO = new PageDTO(content_cnt,currentPage,Common.Admin.BLOCKLIST,Common.Admin.BLOCKPAGE);
		return pageDTO;
		
	}
	
	public int updateStatus (int project_idx) {
		return projectDAO.updateStatus(project_idx);
	}

}
