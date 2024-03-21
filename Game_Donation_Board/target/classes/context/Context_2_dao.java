package context;

import org.apache.ibatis.session.SqlSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dao.NoticeDAO;
import dao.ProfileDAO;
import dao.ProjectDAO;
import dao.UserDAO;
import service.KakaoLoginService;
import service.NaverLoginService;
import service.ProfileService;
import service.ProjectService;
import service.SummerNoteService;
import service.UserService;

@Configuration
public class Context_2_dao {

	@Bean
	public UserService userService(UserDAO userDAO, ProjectDAO projectDAO) {
		return new UserService(userDAO,projectDAO);
	}
	
	@Bean
	public UserDAO userDAO(SqlSession sqlSession) {
		return new UserDAO(sqlSession);
	}
	
	
	@Bean
	public ProjectDAO projectDAO(SqlSession sqlSession) {
		return new ProjectDAO(sqlSession);
	}
	
	@Bean
	public ProfileDAO profileDAO(SqlSession sqlSession) {
		return new ProfileDAO(sqlSession);
	}
	
	
	@Bean public SummerNoteService summerNoteService(ProjectDAO projectDAO, UserDAO userDAO) {
		return new SummerNoteService(projectDAO,userDAO);
	}
	
	@Bean
	public ProjectService projectService(ProjectDAO projectDAO) {
		return new ProjectService(projectDAO);
	}
	
	@Bean
    public NaverLoginService naverLoginService() {
        return new NaverLoginService();
    }
	
	@Bean
    public KakaoLoginService kakaoLoginService() {
        return new KakaoLoginService();
    }
	
	@Bean
	public NoticeDAO noticeDAO(SqlSession sqlSession) {
		return new NoticeDAO(sqlSession);
  }
	@Bean
	public ProfileService profileService(ProfileDAO profileDAO) {
		return new ProfileService(profileDAO);
	}
}
