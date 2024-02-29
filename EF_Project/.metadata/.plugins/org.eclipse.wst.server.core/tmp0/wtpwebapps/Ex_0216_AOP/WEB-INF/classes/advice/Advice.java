package advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Advice {
	//Pointcut : JoinPoint의 상세한 스펙을 정의한 것. 더욱 구체적으로 Advice가 실행될 지점을 정할 수 있다.

	//* : 반환 타입
	//excution : 메서드 실행을 나타내는 키워드
	//dao.*DAO : dao패키지의 DAO로 끝나는 모든 클래스
	//.* : 모든 메서드
	//(..) : 메서드의 전달되는인자 ..은 어떤 개수의 인자든지 가능하다. 
	
	@Pointcut("execution(* dao.*DAO.*(..))")
	public void myPoint() {}
	
	@Before("myPoint()")
	public void before(JoinPoint jp) {
		//getSignature() : 현재 JoinPoint에서 실행되는 메서드의 서명(Signature)정보를 반환한다.
				//서명 정보에는 메서드명, 리턴 타입, 파라미터 타입등이 포함된다.
				//.getName() : 메서드명
				//.getReturnType() : 리턴타입
				//.getParameterTypes() : 파라미터 타입들
		System.out.println("----before:"+jp.getSignature());
		
	}
	// 이후 타겟 메서드 실행
	@After("myPoint()") //- After Advice: Exception 발생 여부에 상관없이 대상 객체의 Method 실행 후 공통 기능을 실행
	public void after(JoinPoint jp) {
		//toLongString 호출하는 메서드의 모든 정보(리턴,이름,매게변수), 경로를 포함하여 반환
		
		System.out.println("---after:"+jp.toLongString());
	}

}
