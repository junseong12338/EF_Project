package context;

import org.apache.ibatis.session.SqlSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dao.BoardDAO;
import dao.UserDAO;
import service.BoardService;
import service.UserService;

@Configuration
public class Context_2_dao {
	@Bean
	public BoardDAO boardDAO(SqlSession sqlSession) {
		return new BoardDAO(sqlSession);
	}

	@Bean
	public UserDAO memberDAO(SqlSession sqlSession) {
		return new UserDAO(sqlSession);
	}

	@Bean
	public BoardService boardService(BoardDAO boardDAO, UserDAO userDAO) {
		return new BoardService(boardDAO, userDAO);
	}

	@Bean
	public UserService userService(UserDAO userDAO) {
		return new UserService(userDAO);
	}
}
