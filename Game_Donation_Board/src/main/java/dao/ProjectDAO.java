package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.ProjectDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProjectDAO {
	
	final SqlSession sqlSession;
	
	// ���࿹�� ������Ʈ�� �� ��ȯ
	public int getBeforeListTotal() {
		int listTotal = sqlSession.selectOne("project.project_before_count");
		return listTotal;
		}
	
	// �������� ������Ʈ�� �� ��ȯ
	public int getNowListTotal() {
		int listTotal = sqlSession.selectOne("project.project_now_count");
		return listTotal;
	}
	
	// ������ ������Ʈ�� �� ��ȯ
	public int getAfterListTotal() {
		int listTotal = sqlSession.selectOne("project.project_after_count");
		return listTotal;
		}
	
	//---------------------------------------------------------------------------------------
	// ���࿹�� ������Ʈ ����Ʈ
	public List<ProjectDTO> selectListBefore(HashMap<String, Object> map){
		List<ProjectDTO> list = sqlSession.selectList("project.project_before_list",map);
		return list;
	}
	
	// �������� ������Ʈ ����Ʈ
	public List<ProjectDTO> selectListNow(HashMap<String, Object> map){
		List<ProjectDTO> list = sqlSession.selectList("project.project_now_list",map);
		return list;
	}
	
	// ������ ������Ʈ ����Ʈ
	public List<ProjectDTO> selectListAfter(HashMap<String, Object> map){
		List<ProjectDTO> list = sqlSession.selectList("project.project_after_list",map);
		return list;
	}
	
	//---------------------------------------------------------------------------------------
	
}
