package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
//@Configuration : 설정파일을 만들기 위한 어노테이션, Bean을 등록하기 위한 어노테이션

@EnableWebMvc
//@EnableWebMvc : Enable로 시작하는 어노테이션을 @Configuration이 붙은 설정 클래스에 붙임으로써 이와 관련된 기능들을 편리하게 제공하고 있다.
//어노테이션 기반의 SpringMvc를 구성할때 필요한 Bean 설정들을 자동으로 해주는 어노테이션이다.
@ComponentScan("com.korea.first")
public class ServletContext implements WebMvcConfigurer {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	@Bean
	public InternalResourceViewResolver  resolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
}
