package com.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.spring_aop_pointcut_decl.Account;

@Aspect
@Component
public class MyDemoLoggingAspect {

	@Pointcut("execution(* com.dao.*.*(..))")
	private void forDaoPackage() {
	}

	@Pointcut("execution(* com.dao.*.get*(..))")
	private void getter() {
	}

	@Pointcut("execution(* com.dao.*.set*(..))")
	private void setter() {
	}

	@Pointcut("forDaoPackage() && !(getter() || setter())")
	private void forDaoPackageNogetterNosetter() {
	}

	@Before("forDaoPackageNogetterNosetter()")
	public void beforeAddAccountAdvice() {
		System.out.println("\n=====>>> Executing @Before advice on method");
	}
	
	@Before("forDaoPackageNogetterNosetter()")
	public void performApiAnalytic(JoinPoint jp) {
		MethodSignature ms = (MethodSignature) jp.getSignature();
		System.out.println("method: " + ms + "---------------");
	}

	@Before("forDaoPackageNogetterNosetter()")
	public void performApiAnalytic() {
		System.out.println("\n=====>>> Performing API analytics");
	}

	

//	@Before("forDaoPackageNogetterNosetter()")
//	public void performApiAnalytics(JoinPoint jp) {
//		Object[] args = jp.getArgs();
//		for (Object argss : args) {
//			System.out.println(argss);
//			
//			if(argss instanceof Account) {
//			Account account = (Account)argss;
//			
//			System.out.println("account name: "+account.getName());
//			System.out.println("account level: "+account.getLevel());
//		}}
//	}

}