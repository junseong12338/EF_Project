package service;

import java.util.List;

import dao.ReviewDAO;
import dto.ReviewDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReviewService {
	
	final ReviewDAO reviewDAO;
	
	public List<ReviewDTO> selectList(){
		
		List<ReviewDTO> list = reviewDAO.selectList();
		
		// 자세한건 나중에 - 수정해야함
		for(int i = 0; i < list.size(); i++) {
			String name = reviewDAO.selectName(list.get(i).getUser_idx());
			list.get(i).setUser_name(name);
		}
		return list;
	}
	
	
	
	public int reviewInsert(ReviewDTO reviewDTO) {
		return reviewDAO.reviewInsert(reviewDTO);
	}
	
	public int reviewDelete(int review_idx) {
		return reviewDAO.reviewDelete(review_idx);
	}
}
