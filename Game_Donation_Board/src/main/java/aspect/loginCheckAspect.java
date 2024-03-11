package aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import dto.UserDTO;

@Aspect
@Component
public class loginCheckAspect {
	
	@Before("execution(* com.korea.board.BoardController.*(..))")
	public void AOPTest(){
		 System.out.println("AOPTest이 호출되었습니다...");
	}
	
	@Pointcut("execution(* com.korea.board.SummerNoteController.project_editor(..))")
    public void loginMethodExceution() {
		 System.out.println("projectEditorMethodExecution이 호출되었습니다...");
	}
	
	@Before("loginMethodExceution()")
    public void beforeLoginMethodExceution(JoinPoint joinPoint) {
		System.out.println("beforeLoginMethodExceution 이 호출되었습니다...");
		
		Object[] args = joinPoint.getArgs();
		for(Object arg : args) {
			
			if(arg instanceof HttpServletRequest) {
				
				HttpServletRequest request = (HttpServletRequest)arg;
				
				try {
					String userEmail =  ((UserDTO)(request.getSession().getAttribute("userEmail"))).getUser_email();
					
				} catch (NullPointerException e) {
					String redirectUrl = "redirect:/";
					throw new ModelAndViewRedirectException(new ModelAndView(redirectUrl)); 
				}
			}
		}
        
    }
	
}
