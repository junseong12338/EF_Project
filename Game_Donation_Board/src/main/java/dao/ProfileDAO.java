package dao;

import org.apache.ibatis.session.SqlSession;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProfileDAO {
	
	final SqlSession sqlSession;
	
	// 리뷰 작성한 갯수
	public int reviewCount(int user_idx) {
		return sqlSession.selectOne("profile.user_review_count",user_idx);
	}
	
	// 후원한 프로젝트 내역 수
	public int sponsored_Project_Details(int user_idx) {
		return sqlSession.selectOne("profile.user_Sponsored_Project_Details",user_idx);
	}
	
	// 등록한 프로젝트 수
	public int Registered_project(int user_idx) {
		return sqlSession.selectOne("profile.user_registered_project",user_idx);
	}

}
