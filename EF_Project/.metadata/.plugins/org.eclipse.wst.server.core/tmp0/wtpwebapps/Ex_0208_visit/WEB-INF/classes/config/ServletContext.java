package config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.korea.visit.VisitController;

import dao.VisitDAO;




@Configuration
@EnableWebMvc
// @ComponentScan("com.korea.mvc")
public class ServletContext implements WebMvcConfigurer {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
//	@Bean
//	public VisitJsonController visitJsonController(VisitDAO visit_dao) {
//		return new VisitJsonController(visit_dao);
//	}
	
	
	@Bean
	public VisitController visitController(VisitDAO visit_dao) {
		return new VisitController(visit_dao);
	}

}
