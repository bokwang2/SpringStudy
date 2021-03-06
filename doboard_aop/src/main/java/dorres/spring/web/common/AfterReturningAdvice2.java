package dorres.spring.web.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

import dorres.spring.web.user.UserVO;

@Service
@Aspect
public class AfterReturningAdvice2 {
	@Pointcut("execution(* dorres.spring.web..*Impl.get*(..))")
	public void getPointcut() {
	}

	@AfterReturning(pointcut = "getPointcut()", returning = "returnObj")
	public void afterLog(JoinPoint joinPoint, Object returnObj) {
		String method = joinPoint.getSignature().getName();
		if (returnObj instanceof UserVO) {
			UserVO user = (UserVO) returnObj;
			if (user.getRole().equals("admin")) {
				System.out.println(user.getName() + " 로그인(Admin)");
			}
		}
		System.out.println("[사후처리] : " + method + "() 메서드 리턴값 : " + returnObj.toString());
	}
}
//<aop:aspectj-autoproxy></aop:aspectj-autoproxy>

//@AfterReturning(pointcut = "PointcutCommon.getPointcut()", returning = "returnObj")