package context;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;



// 스프링에게 현재 클래스가 설정파일임을 알려주는 어노테이션
@Configuration
@ComponentScan("dependency")
public class Context_2_dao {
	
}
