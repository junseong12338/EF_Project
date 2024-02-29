package context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import emp.EmpLoggingAspect;
import emp.EmpManager;

//  스프링에게 현재 클래스가 설정파일임을 알려주는 어노테이션
@Configuration
@EnableAspectJAutoProxy
public class Context_4_AOP {
	
	@Bean
	public EmpManager empManager() {
		return new EmpManager();
	}
	
	@Bean
	public EmpLoggingAspect longAspect() {
		return new EmpLoggingAspect();
	}
	
	
}
