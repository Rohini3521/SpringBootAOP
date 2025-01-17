package com.rohini.aop.demoaop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.rohini.aop.demoaop.service.*.*(..))")
    public void logBeforeMethod(JoinPoint joinPoint) {
        logger.info("Entering method: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "execution(* com.rohini.aop.demoaop.service.*.*(..))", returning = "result")
    public void logAfterMethod(JoinPoint joinPoint, Object result) {
        logger.info("Exiting method: " + joinPoint.getSignature().getName() + " with result: " + result);
    }
}
