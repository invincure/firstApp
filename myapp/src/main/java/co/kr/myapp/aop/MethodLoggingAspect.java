package co.kr.myapp.aop;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import co.kr.myapp.bean.UserInfo;

@Aspect
@Component
public class MethodLoggingAspect {
	private static final Logger logger = LoggerFactory.getLogger(MethodLoggingAspect.class);
	
	//조인 포이트 전에 실행
	@Before("execution(* *..*Service.*(..))")	//Service로 끝나는 class의 모든 public 메서드를 대상으로함.
	public void startLog(JoinPoint jp) {
		logger.info("메서드 시작 : " + jp.getSignature());
	}

	//조인포인트 정상 종료한 후에 실행
//	@@AfterReturning("execution(* *..*Service.*(..))")
	@AfterReturning(value="execution(* *..*Service.*(..))", returning="userInfo")
	public void endLog(JoinPoint jp, UserInfo userInfo) {
		logger.info("메서드 정상 종료 : " + jp.getSignature());
		logger.info("메서드 정상 종료 ReturnUserInfo : " + userInfo.getName() + ", "+userInfo);
		
	}
	
	//조인포인트에서 예외가 발생 했을 때 실행
	@AfterThrowing(value="execution(* *..*Service.*(..))", throwing="e")
	public void endLogAfterThrowing(JoinPoint jp, Exception e) {
		logger.info("메서드 비정상 종료 : " + jp.getSignature());
		logger.error("error Detail" + e.getMessage(), e);
	}
	
	//조인포으트 종료 시점에 실행( try-cat-finally에서 finally와 같은 역할)
	@After(value="execution(* *..*Service.*(..))")
	public void after(JoinPoint jp) {
		logger.info("메서드 finally 종료 : " + jp.getSignature());
	}
	
	//조인포인트 전후에 실행
	@Around(value="execution(* *..*Service.*(..))")
	public Object arount(ProceedingJoinPoint jp) throws Throwable{
		String logPrefix = "[Around] ";
		logger.info(logPrefix + "메서드 시작 : " + jp.getSignature());
		try {
			Object result = jp.proceed(); //대상 메서드 실행도 가능함!
			logger.info(logPrefix + "메서드 정상종료 :"+ jp.getSignature() + " 반환값 =" + result);
			return result;
		}catch(Exception e) {
			logger.info(logPrefix + "메서드 시작 : " + jp.getSignature());
			throw e;
		}
	}

}
