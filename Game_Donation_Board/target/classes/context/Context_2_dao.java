package context;


import org.apache.ibatis.session.SqlSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dao.ProjectDAO;
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
