package service;

import java.util.List;

import dao.ReviewDAO;
import dto.ReviewDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReviewService {
	
	final ReviewDAO reviewDAO;
	

	private static class TIME_MAXIMUM {
		public static final int SEC = 60;
		public static final int MIN = 60;
		public static final int HOUR = 24;
		public static final int DAY = 30;
		public static final int MONTH = 12;
	}
	
	public static String calculateTime(int diffTime) {
		String msg = null;
		if (diffTime < TIME_MAXIMUM.SEC) {
			// 초
			msg = "방금 전";
		} else if ((diffTime /= TIME_MAXIMUM.SEC) < TIME_MAXIMUM.MIN) {
			// 분
			msg = diffTime + "분 전";
		} else if ((diffTime /= TIME_MAXIMUM.MIN) < TIME_MAXIMUM.HOUR) {
			// 시
			msg = (diffTime) + "시간 전";
		} else if ((diffTime /= TIME_MAXIMUM.HOUR) < TIME_MAXIMUM.DAY) {
			// 일
			msg = (diffTime) + "일 전";
		} else if ((diffTime /= TIME_MAXIMUM.DAY) < TIME_MAXIMUM.MONTH) {
			// 일
			msg = (diffTime) + "달 전";
		} else {
			msg = (diffTime) + "년 전";
		}
		return msg;
	}
	
	public List<ReviewDTO> selectList(){
		
		List<ReviewDTO> list = reviewDAO.selectList();
		
		// 자세한건 나중에 - 수정해야함
		for(int i = 0; i < list.size(); i++) {
			String name = reviewDAO.selectName(list.get(i).getUser_idx());
			int date = reviewDAO.selectregdate(list.get(i).getReview_idx());
			list.get(i).setRegdate(calculateTime(date));
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
