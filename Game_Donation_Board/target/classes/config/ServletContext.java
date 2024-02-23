package config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.korea.board.BoardController;
import com.korea.board.UserController;

import service.BoardService;
import service.UserService;


@Configuration
@EnableWebMvc
//@ComponentScan("com.korea.board")
public class ServletContext implements WebMvcConfigurer {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	
	@Bean
	public BoardController boardController(BoardService boardService) {
		return new BoardController(boardService);
	}
	
	@Bean
	public UserController userController(UserService userService) {
		return new UserController(userService);
	}

}
