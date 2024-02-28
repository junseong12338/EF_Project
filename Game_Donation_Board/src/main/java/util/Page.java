package util;

public class Page {
	
	public static String getPaging(String pageURL, int nowPage, int rowTotal, int blockList, int blockPage) {
		int totalPage; 
		int startPage; 
		int endPage; 
		
		boolean isPrevPage, isNextPage;
		StringBuffer sb;
		
		
		isPrevPage=isNextPage=false;
		
		totalPage=(int)(rowTotal/blockList);
		
		if(rowTotal % blockList !=0) {
			totalPage++;
		}
		
		if(nowPage > totalPage) {
			nowPage = totalPage;
		}
		
		startPage = (int)(((nowPage-1)/blockPage)*blockPage+1);
		
		endPage = startPage + blockPage -1;
		
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		
		if(endPage < totalPage) {
			isNextPage = true;
		}
		
		if(startPage > 1) {
			isPrevPage = true;
		}
		
		sb = new StringBuffer();
		
		if(isPrevPage) {
			sb.append("<a href='"+pageURL+"?page=");
			sb.append(startPage - 1);
			sb.append("'><img src='resources/img/btn_prev.gif'></a>");
		} else {
			sb.append("<img src='resources/img/btn_prev.gif'>");
		}
		
		sb.append(" ");
		for(int i = startPage; i <=endPage; i++) {
			if(i > totalPage) {
				break;
			}
			if(i == nowPage) {
				sb.append("&nbsp; <b> <font color='#ff0000'>");
				sb.append(i);
				sb.append("</font></b>");
			} else {
				sb.append("&nbsp; <a href='"+pageURL+"?page=");
				sb.append(i);
				sb.append("'>");
				sb.append(i);
				sb.append("</a>");
			}
		}
		
		sb.append("&nbsp; ");
		if(isNextPage) {
			sb.append("<a href='"+pageURL+"?page=");
			
			sb.append(endPage + 1);
			sb.append("'><img src='resources/img/btn_next.gif'></a>");
		} else {
			sb.append("<img src='resources/img/btn_next.gif'>");
		}
		
		return sb.toString();	
		
	}

}



















