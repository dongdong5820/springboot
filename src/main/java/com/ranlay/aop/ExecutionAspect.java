package com.ranlay.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import sun.rmi.runtime.Log;

/**
 * @author: Ranlay.su
 * @date: 2021-07-10 15:04
 * @description: 切面
 * @version: 1.0
 */
@Component
@Aspect
public class ExecutionAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExecutionAspect.class);

    // 定义切点
    @Pointcut("execution(public * com.ranlay.core.service.ExecutionService.*(..))")
    public void executePoint() {}

    // 环绕通知
    @Around("executePoint()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("doAround: before");
        long startTime = System.currentTimeMillis();
        // 执行核心方法本身
        Object result = null;
        result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;
        LOGGER.info("Time taken by {} is {} ms", joinPoint, timeTaken);
        System.out.println("doAround: after");

        return result;
    }

    // 前置通知
    @Before("execution(public * com.ranlay.core.service.ExecutionService.*(..))")
    public void executeBefore() {
        System.out.println("核心方法调用之前，executeBefore方法被执行！！！");
    }

    // 后置通知
    @After("execution(public * com.ranlay.core.service.ExecutionService.test(..))")
    public void executeAfter() {
        System.out.println("核心方法调用之后，executeAfter方法被执行！！！");
    }
}
