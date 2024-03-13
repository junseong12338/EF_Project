package config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.korea.board.AdminController;
import com.korea.board.BoardController;
import com.korea.board.KakaoLoginController;
import com.korea.board.NaverLoginController;
import com.korea.board.ProfileController;
import com.korea.board.ProjectController;
import com.korea.board.SummerNoteController;
import com.korea.board.UserLoginController;

import dto.ProjectStatus;
import service.KakaoLoginService;
import service.NaverLoginService;
import service.ProjectService;
import service.SummerNoteService;
import service.UserService;
import com.korea.board.ProjectController;

import service.ProjectService;


@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ServletContext implements WebMvcConfigurer {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	@Bean
	public BoardController boardController(ProjectService projectService) {
		return new BoardController(projectService);
	}
	
	
	@Bean
	public UserLoginController userController(UserService userService) {
		return new UserLoginController(userService);
	}
	
	@Bean
	public NaverLoginController naverController(UserService userService, NaverLoginService naverLoginService) {
		return new NaverLoginController(userService,naverLoginService);
	}
	
	@Bean
	public KakaoLoginController kakaoController(UserService userService, KakaoLoginService kakaoLoginService) {
		return new KakaoLoginController(userService,kakaoLoginService);
	}
	
	@Bean SummerNoteController summerNoteController(SummerNoteService summerNoteService) {
		return new SummerNoteController(summerNoteService);
	}
	
	@Bean
	public ProfileController profileController(UserService userService) {
		return new ProfileController(userService);
	}
	
	@Bean
	public AdminController adminController(ProjectService projectService) {
		return new AdminController(projectService);
	}
	
	
	@Bean ProjectController projectController(ProjectService projectService) {
		return new ProjectController(projectService);
	}
	
	 @Bean(name = "multipartResolver")
	    public CommonsMultipartResolver commonsMultipartResolver() {
	        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
	        resolver.setMaxUploadSize(100000000);
	        return resolver;
	    }

	

}
