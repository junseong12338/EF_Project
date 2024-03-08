package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.CategoryNumDTO;
import dto.ProjectDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProjectDAO {
	
	final SqlSession sqlSession;
	
	//프로젝트 시퀀스 번호 가져오기
	public int select_next_idx() {
		return sqlSession.selectOne("project.select_next_idx");
	}
	
	//프로젝트 등록
	public int insert_project(ProjectDTO dto) {
		return sqlSession.insert("project.insert_project",dto);
	}
	//프로젝트 업데이트
	public int update_project(ProjectDTO dto) {
		return sqlSession.update("project.project_update",dto);
	}
	
	//카테고리 삭제
	public int delete_categery(int idx) {
		return sqlSession.delete("project.category_delete",idx);
	}
	
	//프로젝트 한건 가져오기
	public ProjectDTO selectOne_project(int idx) {
		return sqlSession.selectOne("project.selectOne_project",idx);
	}
	
	//프로젝트 카테고리 등록 메서드
	public int insert_categoryNum(CategoryNumDTO dto) {
		return sqlSession.insert("project.insert_category",dto);
	}
	
	//프로젝트가 가지고있는 카테고리넘버 가져오기
	public List<CategoryNumDTO> select_categoryNum(int idx) {
		return sqlSession.selectList("project.select_category",idx);
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
	
	
	public List<ProjectDTO>	ProjectList(){
		return sqlSession.selectList("project.project_list");
	}
}
