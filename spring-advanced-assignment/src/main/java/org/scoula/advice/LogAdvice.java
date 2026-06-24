package org.scoula.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAdvice {
    @Before("execution(* org.scoula.sample.service.SampleService.*(..))")
    public void logBefore() {
        System.out.println("========================================");
    }

    @Before("execution(* org.scoula.sample.service.SampleService.doAdd(..))")
    public void logParameters(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length >= 2) {
            System.out.println("str1: " + args[0]);
            System.out.println("str2: " + args[1]);
        }
    }
}
