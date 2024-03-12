package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.ReviewDTO;

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
	
	//리뷰 추가
	public int insert(ReviewDTO dto){
		int res = sqlSession.insert("r.review_insert",dto);
		return res;
	}
}
