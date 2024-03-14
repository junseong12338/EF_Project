package context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import aspect.loginCheckAspect;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class Context_4_AOP {
	
	@Bean
	public loginCheckAspect loginCheckAspect() {
		
		return new loginCheckAspect();
	}
	
}
