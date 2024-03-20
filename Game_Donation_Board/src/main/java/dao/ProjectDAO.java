package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.AdminInfoDTO;
import dto.CategoryNumDTO;
import dto.ProjectDTO;
import dto.ProjectMainListDTO;
import dto.ReviewDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProjectDAO {
	
final SqlSession sqlSession;
	//----------------------------정진수

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
	
	//프로젝트 삭제
	public int delete_project(int idx) {
		return sqlSession.delete("project.delete_project",idx);
	}
	
	//좋아요 삭제
	public int delete_like(int idx) {
		return sqlSession.delete("project.delete_like",idx);
	}
	
	//도네이션 삭제
	public int delete_donation(int idx) {
		return sqlSession.delete("project.delete_donation",idx);
	}
	
	//------------------------------------------------------------------ 성현 project_list
	
	// 프로젝트 총 갯수 
	public int select_count(ProjectDTO dto) {
		return sqlSession.selectOne("project.project_count", dto);
	}
	
	// 프로젝트 총 리스트
	public List<ProjectDTO> selectList(ProjectDTO dto){
		return sqlSession.selectList("project.project_list", dto);
	}
	
	// user 테이블에 user_name 가져오기
	public String selectName(int user_idx) {
		return sqlSession.selectOne("project.user_name", user_idx);
	}
	
	// ----------------------------------------------------------------- 성현 detail.jsp
	
	// donation 테이블의 정보 가져오기 - sum (project_idx)
	public int select_donation(int project_idx) {
		return sqlSession.selectOne("project.donation", project_idx) == null ? 0 : sqlSession.selectOne("project.donation", project_idx);
	}
	
	// user 테이블의 정보 가져오기 (project_idx)
	public String select_name(int project_idx) {
		return sqlSession.selectOne("project.user_author", project_idx);
	}
	
	// category_name 테이블의 정보 가져오기 (project_idx)
	public List<String> selectList(int project_idx){
		return sqlSession.selectList("project.category_name", project_idx);
	}
	
	// like 테이블의 정보 가져오기 (project_idx)
	public int select_like(int project_idx) {
		return sqlSession.selectOne("project.like_cnt", project_idx);
	}
	
	// like 테이블에서 두개의 idx와 같은 행 개수 가져오기
	public int selectOne(HashMap<String, Object> map_idx) {
		return sqlSession.selectOne("project.map_idx", map_idx);
	}
	
	// insert heart
	public int insert_heart(HashMap<String, Object> map_idx) {
		return sqlSession.insert("project.insert_heart", map_idx);
	}
	
	// delete heart
	public int delete_heart(HashMap<String, Object> map_idx) {
		return sqlSession.insert("project.delete_heart", map_idx);
	}
	
	//--------------------------------------------------------------------------- 성현 detail_ajax.jsp
	// EF_USER 테이블의 user_point 차감 - update
	public int update_point(HashMap<String, Object> map) {
		return sqlSession.update("d.update_point", map);
	}
	// 사용자가 이 프로젝트에 후원한적이 있는지 체크
	public int select_used(HashMap<String, Object> map) {
		return sqlSession.selectOne("d.select_used", map);
	}
	// 후원한 적 YES
	public int update_donation(HashMap<String, Object> map) {
		return sqlSession.update("d.update_donation", map);
	}
	// 후원한 적 NO
	public int insert_donation(HashMap<String, Object> map) {
		return sqlSession.insert("d.insert_donation", map);
	}
	
	// review select
	public List<ReviewDTO> selectList_review(HashMap<String, Object> map){
		return sqlSession.selectList("d.select_review", map);
	}
	
	// review register
	public int insert_review(ReviewDTO dto) {
		return sqlSession.insert("d.insert_review", dto);
	}
	
	
	//---------------------------------------------------------------------------------------이준성
		
	public int project_wait_count(int status){
		return sqlSession.selectOne("project.project_wait_count",status);
	}

	public List<AdminInfoDTO> getRowTotal(HashMap<String, Integer> map){
		return sqlSession.selectList("project.getRowTotal", map);
	}
	public int updateStatus(HashMap<String, Integer> map){
		return sqlSession.update("project.updateStatus",map);
	}

	public String getUserName(int user_idx) {
		return sqlSession.selectOne("project.getUserName",user_idx);
	}

	public List<ProjectMainListDTO> Main_New_registration_list(){
		return sqlSession.selectList("project.Main_New_registration_list");
	}
	public List<ProjectMainListDTO> Main_donation_list(){
		return sqlSession.selectList("project.Main_donation_list");
	}
	public List<ProjectMainListDTO> Main_To_be_released_list(){
		return sqlSession.selectList("project.Main_To_be_released_list");
	}
	public List<ProjectMainListDTO> Main_Like_Project_list(){
		return sqlSession.selectList("project.Main_Like_Project_list");
	}


	


	
	

}
