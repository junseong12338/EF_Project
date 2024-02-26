package context;


import org.apache.ibatis.session.SqlSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dao.BoardDAO;
import dao.MemberDAO;
import dao.ProjectDAO;
import service.BoardService;
import service.ProjectService;

@Configuration
public class Context_2_dao {
	
	@Bean ProjectDAO projectDAO(SqlSession sqlSession) {
		return new ProjectDAO(sqlSession);
	}
	
	@Bean
	public ProjectService projectService(ProjectDAO projectDAO) {
		return new ProjectService(projectDAO);
	}
}
