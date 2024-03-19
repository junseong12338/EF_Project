package aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import dto.UserDTO;

@Aspect
public class loginCheckAspect {
	
//	@Pointcut()
//	public void AOPTest(){}
	
	
	//지정한 메서드 실행 시 실행
	@Pointcut("execution(* com.korea.board.SummerNoteController.project_editor(..)) || "
			+ "execution(* com.korea.board.SummerNoteController.project_modify(..)) ||"
			+ "execution(* com.korea.board.ProfileController.mypage_view(..)) ||"
			+ "execution(* com.korea.board.AdminController.adminNoticeEditor(..)) ||"
			+ "execution(* com.korea.board.SummerNoteController.adminNoticeModify(..))")
	
    public void loginMethodExceution() {}
	
	
	//loginMethodExceution() 실행 전 userEmail Session유무 체크 후 NullPointException발생하면
	//login_form으로 이동
	@Before("loginMethodExceution()")
    public void beforeLoginMethodExceution(JoinPoint joinPoint) {
		System.out.println("beforeLoginMethodExceution 이 호출되었습니다...");
		
		// 현재 스레드의 ServletRequestAttributes를 가져옴
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        
        // HttpServletRequest를 가져옴
        HttpServletRequest request = attributes.getRequest();
		try {
			UserDTO userEmail =  ((UserDTO)(request.getSession().getAttribute("user_email")));
			System.out.println(userEmail.getUser_status());
		} catch (NullPointerException e) {
			String redirectUrl = "redirect:/login_filter?status=0";
			throw new ModelAndViewRedirectException(new ModelAndView(redirectUrl)); 
		}
	}
}
