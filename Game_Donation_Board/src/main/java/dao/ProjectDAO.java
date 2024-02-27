package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.ProjectDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProjectDAO {
	
	final SqlSession sqlSession;
	
	// 진행예정 프로젝트의 수 반환
	public int getBeforeListTotal() {
		int listTotal = sqlSession.selectOne("project.project_before_count");
		return listTotal;
		}
	
	// 진행중인 프로젝트의 수 반환
	public int getNowListTotal() {
		int listTotal = sqlSession.selectOne("project.project_now_count");
		return listTotal;
	}
	
	// 마감된 프로젝트의 수 반환
	public int getAfterListTotal() {
		int listTotal = sqlSession.selectOne("project.project_after_count");
		return listTotal;
		}
	
	//---------------------------------------------------------------------------------------
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
