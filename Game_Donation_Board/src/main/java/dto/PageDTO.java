package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageDTO {
	// 최소 페이지 번호
		private int min;
		// 최대 페이지 번호
		private int max;
		// 이전 버튼 페이지 번호
		private int prevPage;
		// 다음 버튼 페이지 번호
		private int nextPage;
		// 전체 페이지 개수
		private int pageCnt;
		// 현재 페이지 번호
		private int currentPage;
				
		// contentCnt : 전체글 개수 , currentPage : 현재 글 번호, contentPageCnt : 페이지당 글의 개수, paginationCnt : 페이지 버튼의 개수
		public PageDTO(int contentCnt, int currentPage, int contentPageCnt, int paginationCnt) {
			// 현재 페이지 번호
			this.currentPage = currentPage;
			// 전체 페이지 개수
			pageCnt = contentCnt / contentPageCnt;
			if(contentCnt % contentPageCnt > 0) {
				pageCnt++;
			}
			// 계산공식
			min = ((currentPage -1) / contentPageCnt) * contentPageCnt + 1;
			max = min + paginationCnt - 1;
			
			if(max > pageCnt) {
				max = pageCnt;
			}
			prevPage = min -1;
			nextPage = max + 1;
			
		}
	
}
