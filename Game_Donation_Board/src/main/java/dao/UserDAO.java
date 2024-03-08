package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

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
	
	public int userInsert(UserDTO dto) {
		return sqlSession.insert("u.insert",dto);
	}
	public int update_point(int userId, int point) {
		
		Map<String, Integer> params = new HashMap<>();
	    params.put("userId", userId);
	    params.put("point", point);
	    return sqlSession.update("u.update_point_by_user_id", params);
	}

	public int userDelete(int idx) {
		
		return sqlSession.delete("u.user_delete",idx);
	}
	
	public int userUpdate(UserDTO dto) {
		System.out.println("DAO"+dto);
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

	//프로젝트 한건 가져오기
	public List<ProjectDTO> selectProjectList() {
		List<ProjectDTO> list = sqlSession.selectList("project.userIdx_list");
//		System.out.println(list);
		return sqlSession.selectList("project.userIdx_list");
	}

	public List<DonationDTO> selectdonationList(){
		List<DonationDTO> list = sqlSession.selectList("u.donation_list");

		return sqlSession.selectList("u.donation_list");
	}
		
}
