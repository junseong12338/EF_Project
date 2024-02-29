package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dto.PersonDTO;

//  스프링에게 현재 클래스가 설정파일임을 알려주는 어노테이션
@Configuration
public class RootContext {
	// Bean
	// Spring 에서 Bean은 어플리케이션의 핵심 구성요소로 사용이 되는 객체를 의미한다.
	// 스프링은 제어 역전 (Inversion of Control, IoC)를 통해 객체의 생명주기와
	// 의존성 관리를 스프링이 담당하게 된다.
	
	@Bean
	public PersonDTO p1() {
		PersonDTO p1 = new PersonDTO();
		p1.setName("홍길동");
		p1.setAge(30);
		return p1;
	}
	
	@Bean
	public PersonDTO p2() {
		PersonDTO p2 = new PersonDTO("이길동",40);
		return p2;
	}
}
