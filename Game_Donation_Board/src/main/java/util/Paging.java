package util;

public class Paging {

	public static String getPaging(String pageURL, int nowPage, int rowTotal, int blockList, int blockPage) {
		int totalPage; //전체 페이지 수
		int startPage; //시작 페이지 번호
		int endPage; //마지막 페이지 번호
		
		boolean isPrevPage, isNextPage;
		StringBuffer sb;
		
		
		isPrevPage=isNextPage=false;
		
		//전체 페이지 수
		// 전체 페이지 수 계산
		totalPage = (int)Math.ceil((double)rowTotal / blockList);

		// 게시물 수가 6의 배수로 떨어지지 않고 나머지가 생겼을 때
		if (rowTotal % blockList != 0) {
		    totalPage++;
		}

		// 만약 잘못된 연산과 움직임으로 인하여 현재 페이지 수가 전체 페이지 수를
		// 넘을 경우 강제로 현재 페이지 값을 전체 페이지 값으로 변경
		if (nowPage > totalPage) {
		    nowPage = totalPage;
		}

		// 시작 페이지 구하기
		startPage = (int)(((nowPage - 1) / blockPage) * blockPage + 1);

		// 종료 페이지 구하기
		endPage = startPage + blockPage - 1;

		// 마지막 페이지 수가 전체 페이지 수보다 크면 마지막 페이지 값을 변경
		if (endPage > totalPage) {
		    endPage = totalPage;
		}

		// 마지막 페이지가 전체 페이지보다 작을 경우 다음 페이징이 적용할 수 있도록
		// boolean 형 변수의 값을 설정
		if (endPage < totalPage) {
		    isNextPage = true;
		}

		// 시작 페이지의 값이 1보다 크면 이전 페이지로 이동할 수 있도록 값 설정
		if (startPage > 1) {
		    isPrevPage = true;
		}
		
		sb = new StringBuffer();
		
		//----그룹 페이지처리 이전-------------------------------------
		if(isPrevPage) {
			sb.append("<a href='"+pageURL+"?page=");
			sb.append(startPage - 1);
			//sb.append("'><img src='resources/img/btn_prev.gif'></a>");
		} else {
			//sb.append("<img src='resources/img/btn_prev.gif'>");
		}
		
		//---- 페이지 목록을 출력--------------------------------------
		sb.append(" ");
		for(int i = startPage; i <=endPage; i++) {
			if(i > totalPage) {
				break;
			}
			if(i == nowPage) {//현재 선택된 페이지
				sb.append("&nbsp;<font color='#e75e8d'>");
				sb.append(i);
				sb.append("</font>");
			} else {//현재 페이지가 아닐때
				sb.append("&nbsp;<a href='" + pageURL + "?page=");
				sb.append(i);
				sb.append("'>");
				sb.append(i);
				sb.append("</a>");
			}
		}//for문
		
		sb.append("&nbsp; ");
		//----그룹 페이지처리 다음-------------------------------------
		if(isNextPage) {
			sb.append("<a href='"+pageURL+"?page=");
			
			sb.append(endPage + 1);
			//sb.append("'><img src='resources/img/btn_next.gif'></a>");
		} else {
			//sb.append("'><img src='resources/img/btn_next.png' width='10px;' height='10px;'></a>");
		}
		
		return sb.toString();	
		
	}
}
