package config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.korea.board.BoardController;
import com.korea.board.ProjectController;
import com.korea.board.SummerNoteController;

import service.BoardService;
import service.ProjectService;
import service.SummerNoteService;


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
	
	@Bean SummerNoteController summerNoteController(SummerNoteService summerNoteService) {
		return new SummerNoteController(summerNoteService);
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
