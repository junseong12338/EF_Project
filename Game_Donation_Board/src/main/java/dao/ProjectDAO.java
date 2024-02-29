package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.ProjectDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProjectDAO {
	
	final SqlSession sqlsession;
	
	//�� �ۼ� �� �� ��ȣ �̸� ��������
	public int select_idx() {
		return sqlsession.selectOne("pj.select_idx");
	}
	
	public int insert_project(ProjectDTO dto) {
		return sqlsession.insert("pj.insert_project",dto);
	}
	
	public int select_idx(String project_content) {
		return sqlsession.selectOne("pj.select_idx",project_content);
	}
	
	//�� �Ѱ� ��������
	public ProjectDTO selectOne_project(int idx) {
		return sqlsession.selectOne("pj.selectOne_project",idx);
	}
	
	
	// list �� ���� ��ȯ
	public int get_list_count(ProjectDTO dto) {
		return sqlSession.selectOne("project.project_list_count",dto);
	}
	
	//-----------------------------------------------------------
	
	
	// ���࿹�� ������Ʈ�� �� ��ȯ
	public int getBeforeListTotal() {
		return sqlSession.selectOne("project.project_before_count");
		}
	
	// �������� ������Ʈ�� �� ��ȯ
	public int getNowListTotal() {
		return sqlSession.selectOne("project.project_now_count");
	}
	
	// ������ ������Ʈ�� �� ��ȯ
	public int getAfterListTotal() {
		int listTotal = sqlSession.selectOne("project.project_after_count");
		return listTotal;
		}
	
	//---------------------------------------------------------------------------------------
	// ��ü ������Ʈ ����Ʈ
	public List<ProjectDTO> selectList(ProjectDTO dto){
		return sqlSession.selectList("project.project_list",dto);
	}
	
	//----------------------------------------------------------------------
	
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
