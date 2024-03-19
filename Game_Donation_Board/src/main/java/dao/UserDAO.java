package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.AdminNoticeDTO;
import dto.DonationDTO;
import dto.ProjectDTO;

import dto.UserDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserDAO {
	
	final SqlSession sqlSession;

	public UserDTO checkEmail(String user_email) {

		return sqlSession.selectOne("u.checkEmail",user_email);
	}
	
	public UserDTO checkSocial(String user_social) {

		return sqlSession.selectOne("u.checkSocial",user_social);
	}

	public UserDTO selectone(int user_idx) {

		return sqlSession.selectOne("u.selectone",user_idx);
	}
	
	public int userInsert(UserDTO dto) {
		return sqlSession.insert("u.insert",dto);
	}
	

	public int userDelete(int idx) {
		
		return sqlSession.delete("u.user_delete",idx);
	}
	
	public int userUpdate(UserDTO dto) {

	    return sqlSession.update("u.update", dto);
	}
	
	public UserDTO selectOne(int idx) {
		return sqlSession.selectOne("u.user_one",idx);
	}

	public List<UserDTO> selectList(HashMap<String, Integer> map){
		return sqlSession.selectList("u.user_list",map);
	}

	public int getRowTotal() {
		return sqlSession.selectOne("u.user_count");
	}

	
	// 이영찬
	
	//프로젝트 한건 가져오기
		public List<ProjectDTO> selectProjectList(int userIdx) {
			List<ProjectDTO> list = sqlSession.selectList("project.userIdx_list",userIdx);
//			System.out.println(list);
			return list;
		}
	public List<DonationDTO> selectdonationList(){
		List<DonationDTO> list = sqlSession.selectList("u.donation_list");

		return sqlSession.selectList("u.donation_list");
	}
	
	//유저 보유포인트 업데이트
	public int userPointUpdate(UserDTO dto) {
		return sqlSession.update("u.user_update_point",dto);
	}
	
	// 유저 프로젝트 갯수 업데이트
	public int userProjectCount(UserDTO dto) {
		return sqlSession.update("u.user_project_count",dto);
	}
	

		
	
	//------정진수
	//유저이미지 업데이트
	public int userImgUpdate(UserDTO dto) {
		return sqlSession.update("u.user_img_update",dto);
	}
	
	//공지사항 다음번호 가져오기
	public int select_admin_notice_next_idx() {
		return sqlSession.selectOne("u.select_next_idx");
	}
	
	//공지사항 인서트
	public int insert_admin_notice(AdminNoticeDTO dto) {
		return sqlSession.insert("u.insert_admin_notice",dto);
	}
}
