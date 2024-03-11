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
