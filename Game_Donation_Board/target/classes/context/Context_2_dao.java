package context;


import org.apache.ibatis.session.SqlSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dao.BoardDAO;
import dao.MemberDAO;
import dao.ProjectDAO;
import service.BoardService;
import service.ProjectService;
import service.SummerNoteService;

@Configuration
public class Context_2_dao {
	@Bean
	public BoardDAO boardDAO(SqlSession sqlSession) {
		return new BoardDAO(sqlSession);
	}
	
	@Bean
	public MemberDAO memberDAO(SqlSession sqlSession) {
		return new MemberDAO(sqlSession);
	}
	
	@Bean
	public ProjectDAO projectDAO(SqlSession sqlSession) {
		return new ProjectDAO(sqlSession);
	}
	
	@Bean
	public BoardService boardService(BoardDAO boardDAO,MemberDAO memberDAO) {
		return new BoardService(boardDAO,memberDAO);
	}
	
	@Bean public SummerNoteService summerNoteService(ProjectDAO projectDAO) {
		return new SummerNoteService(projectDAO);
	}
	
	@Bean
	public ProjectService projectService(ProjectDAO projectDAO) {
		return new ProjectService(projectDAO);
	}
	
}
