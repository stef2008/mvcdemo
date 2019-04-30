package com.nowcoder.mvcdemo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class MyAspect {

    @Pointcut("execution(* com.nowcoder.mvcdemo.service.*.*(..))")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void before() {
        System.out.println("before ...");
    }

    @After("pointCut()")
    public void after() {
        System.out.println("after ...");
    }

    @AfterReturning("pointCut()")
    public void afterReturning() {
        System.out.println("after returning ...");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing() {
        System.out.println("after throwing ...");
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("arount before ...");
        Object obj = proceedingJoinPoint.proceed();
        System.out.println("arount after ...");
        return obj;
    }

}
