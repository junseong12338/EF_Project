package context;


import org.apache.ibatis.session.SqlSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import advice.Advice;
import dao.TestDAO;



// 스프링에게 현재 클래스가 설정파일임을 알려주는 어노테이션
@Configuration
@EnableAspectJAutoProxy
// 어노테이션으로 AspectJ 스타일을 지원하도록 활성화 한다.
// AspectJ 자바용으로 개발된 강력한 AOP 프레임 워크로 널리 사용된다
// 이 어노테이션을 사용하여 AspectJ 스타일의 AOP를 활성화 하면, 스프링은 @Aspect 어노테이션을
// 가진 클래스를 찾아서 프록시를 생성하고, 해당 Aspect를 적용한다.
public class Context_2_dao {
	
	
	@Bean
	public TestDAO testDAO(SqlSession sqlSession) {
		return new TestDAO(sqlSession);
	}
	
	@Bean
	public Advice advice() {
		return new Advice();
	}
	

}
