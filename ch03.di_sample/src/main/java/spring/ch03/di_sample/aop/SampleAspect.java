package spring.ch03.di_sample.aop;

import java.text.SimpleDateFormat;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SampleAspect {

	@Before("execution(* spring.ch03.di_sample.used.*Greet.*(..))")
	public void beforeAdvice(JoinPoint joinPoint) {
		//시작
		System.out.println("===== Before Advice =====");
		//날짜출력
		System.out.println(new SimpleDateFormat("yyyy/MM/dd").format(new java.util.Date()));
		//메서드명 출력
		System.out.println(String.format("메서드:%s", joinPoint.getSignature().getName()));
	}
	
	@After("execution(* spring.ch03.di_sample.used.*Greet.*(..))")
	public void afterAdvice(JoinPoint joinPoint) {
		//시작
		System.out.println("===== After Advice =====");
		//날짜출력
		System.out.println(new SimpleDateFormat("yyyy/MM/dd").format(new java.util.Date()));
		//메서드명 출력
		System.out.println(String.format("메서드:%s", joinPoint.getSignature().getName()));
	}
	
	@Around("execution (* spring.ch03.di_sample.used.*Greet.*(..))")
	public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		//시작 부분
		System.out.println("===== Around Advice =====");
		System.out.println("*** 처리전 ***");
		//지정된 클래스의 메서드 실행
		Object result = joinPoint.proceed();
		System.out.println("*** 처리후 ***");
		return result;
	}
}
