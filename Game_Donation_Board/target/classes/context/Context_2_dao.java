package context;

import org.apache.ibatis.session.SqlSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dao.UserDAO;
import service.NaverLoginService;
import service.UserService;

@Configuration
public class Context_2_dao {
	

	@Bean
	public UserDAO memberDAO(SqlSession sqlSession) {
		return new UserDAO(sqlSession);
	}


	@Bean
	public UserService userService(UserDAO userDAO) {
		return new UserService(userDAO);
	}
	
	@Bean
    public NaverLoginService naverLoginService() {
        return new NaverLoginService();
    }
}
