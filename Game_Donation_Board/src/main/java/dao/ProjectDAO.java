package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.ProjectDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProjectDAO {
	
	final SqlSession sqlSession;
	
	// list 총 개수 반환
	public int get_list_count(ProjectDTO dto) {
		return sqlSession.selectOne("project.project_list_count",dto);
	}
	
	//-----------------------------------------------------------
	
	
	// 진행예정 프로젝트의 수 반환
	public int getBeforeListTotal() {
		return sqlSession.selectOne("project.project_before_count");
		}
	
	// 진행중인 프로젝트의 수 반환
	public int getNowListTotal() {
		return sqlSession.selectOne("project.project_now_count");
	}
	
	// 마감된 프로젝트의 수 반환
	public int getAfterListTotal() {
		int listTotal = sqlSession.selectOne("project.project_after_count");
		return listTotal;
		}
	
	//---------------------------------------------------------------------------------------
	// 전체 프로젝트 리스트
	public List<ProjectDTO> selectList(ProjectDTO dto){
		return sqlSession.selectList("project.project_list",dto);
	}
	
	//----------------------------------------------------------------------
	
	// 진행예정 프로젝트 리스트
	public List<ProjectDTO> selectListBefore(HashMap<String, Object> map){
		List<ProjectDTO> list = sqlSession.selectList("project.project_before_list",map);
		return list;
	}
	
	// 진행중인 프로젝트 리스트
	public List<ProjectDTO> selectListNow(HashMap<String, Object> map){
		List<ProjectDTO> list = sqlSession.selectList("project.project_now_list",map);
		return list;
	}
	
	// 마감된 프로젝트 리스트
	public List<ProjectDTO> selectListAfter(HashMap<String, Object> map){
		List<ProjectDTO> list = sqlSession.selectList("project.project_after_list",map);
		return list;
	}
	
	//---------------------------------------------------------------------------------------
	
}
