package service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


import dao.ProjectDAO;
import dto.AdminInfoDTO;
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
	
	//--------------------------------------------------------------- 성현 detail.jsp , detail_ajax.jsp
	
	// project테이블 의 정보를 가져오기 (project_idx)
	public ProjectDTO selectOne(int project_idx) {
		return projectDAO.selectOne_project(project_idx);
	}
	
	// donation 테이블의 정보 가져오기 - sum (project_idx)
	// donation, persent, diff_date
	public HashMap<String, Object> selectMap(int project_idx){
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		// donation
		int donation = projectDAO.select_donation(project_idx);
		
		// persent, diff_date
		ProjectDTO dto = projectDAO.selectOne_project(project_idx);
		
		int target = dto.getProject_target();
		String start = dto.getProject_start();
		String end = dto.getProject_end();
		
		int persent = 0;
		
		// persent
		persent = ( donation / target ) * 100;
		String persent_str  = String.format("%,d", persent);
		
		String diff_date = "";
		
		// diff_date
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate now = LocalDate.now();
		String today = now.format(formatter);
		Date start_date = null;
		Date end_date = null;
		Date now_date = null;
		
		try {
			now_date = sdf.parse(today);
			start_date = sdf.parse(start);
			end_date = sdf.parse(end);
			
			if(now_date.getTime() < start_date.getTime()) {
				diff_date = "진행예정";
			}else if(now_date.getTime() > end_date.getTime()){
				diff_date = "마감";
			}else {
				diff_date = String.format("%d 일", ( end_date.getTime() - now_date.getTime() ) / (24*60*60*1000) );
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		map.put("donation", donation);
		map.put("persent", persent_str + " %");
		map.put("diff_date", diff_date);
		
		return map;
	}
	
	// user 테이블의 정보 가져오기 (project_idx)
	public String selectName(int project_idx) {
		return projectDAO.select_name(project_idx);
	}
	
	// category_name 테이블의 정보 가져오기 (project_idx)
	public List<String> selectList(int project_idx){
		return projectDAO.selectList(project_idx);
	}
	
	// like 테이블의 정보 가져오기 (project_idx)
	public int selectLike(int project_idx) {
		return projectDAO.select_like(project_idx);
	}
	
	// like 테이블에서 두개의 idx와 같은 행 개수 가져오기 (map_idx)
	public int selectOne(HashMap<String, Object> map_idx) {
		return projectDAO.selectOne(map_idx);
	}
	
	// insert heart
	public int insert_heart(HashMap<String, Object> map_idx) {
		return projectDAO.insert_heart(map_idx);
	}
	
	// delete heart
	public int delete_heart(HashMap<String, Object> map_idx) {
		return projectDAO.delete_heart(map_idx);
	}
	
	// EF_USER 테이블의 user_point 차감 - update
	public int update_point(HashMap<String, Object> map) {
		return projectDAO.update_point(map);
	}
	
	// EF_DONATION 테이블의 insert, update
	// 사용자가 이 프로젝트에 후원한적이 있는지 체크
	public int select_used(HashMap<String, Object> map) {
		return projectDAO.select_used(map);
	}
	// 후원한 적 YES
	public int update_donation(HashMap<String, Object> map) {
		return projectDAO.update_donation(map);
	}
	// 후원한 적 NO
	public int insert_donation(HashMap<String, Object> map) {
		return projectDAO.insert_donation(map);
	}
	
		
	//-------------------------------------------------------------- 이준성
	//전체 게시물 수 조회
	public List<AdminInfoDTO> getRowTotal(HashMap<String, Integer> map){
		return projectDAO.getRowTotal(map);
	}

	public String getUserName(int user_idx) {
		return projectDAO.getUserName(user_idx);
	}
	
	public PageDTO getContentCnt(int currentPage) {
		int content_cnt = projectDAO.project_wait_count();
	PageDTO pageDTO = new PageDTO(content_cnt,currentPage,Common.Admin.BLOCKLIST,Common.Admin.BLOCKPAGE);
		return pageDTO;
		
	}
	
	public int updateStatus (int project_idx) {
		return projectDAO.updateStatus(project_idx);
	}
	
	public List<ProjectDTO> Main_limit_list(){
		return projectDAO.Main_limit_list();
	}

}
