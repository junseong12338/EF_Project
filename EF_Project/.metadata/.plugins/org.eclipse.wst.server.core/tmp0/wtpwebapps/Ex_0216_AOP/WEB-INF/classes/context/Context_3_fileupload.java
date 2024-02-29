package context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;




//  스프링에게 현재 클래스가 설정파일임을 알려주는 어노테이션
@Configuration
public class Context_3_fileupload {

	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver mr = new CommonsMultipartResolver();
		mr.setDefaultEncoding("utf-8");
		// 최대 용량 파일
		mr.setMaxUploadSize(10485760);
		
		return mr;
	}
}
