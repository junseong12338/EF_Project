package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import dto.ProjectDTO;
import dto.ReviewDTO;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class ReviewDAO {
	SqlSession sqlSession;
	
	public ReviewDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
    
    //리뷰 전체 조회
	public List<ReviewDTO> selectList(){
		List<ReviewDTO> list = sqlSession.selectList("r.review_list");
		return list;
	}
	
	// 리스트 별 리뷰 작성한 사람 이름 select
	public String selectName(int user_idx) {
		return sqlSession.selectOne("r.user_name",user_idx);
	}
	

	public String selectuserimg(int user_idx) {
		return sqlSession.selectOne("r.user_img",user_idx);
	}

	public int selectregdate(int review_idx) {
		return sqlSession.selectOne("r.regdate",review_idx);
	}
	
	//리뷰 추가
	public int reviewInsert(ReviewDTO dto){
		int res = sqlSession.insert("r.review_insert",dto);
		return res;
	}
	
	//리뷰 삭제
	public int reviewDelete(int review_idx){
		int res = sqlSession.delete("r.review_delete",review_idx);
		return res;
	}
	
	
	//리뷰 한건 가져오기
	public ReviewDTO selectOne_Review(int idx) {
		return sqlSession.selectOne("r.selectOne_review",idx);
	}
		
}
