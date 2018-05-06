package com.hhp.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Hu on 2018/5/6.
 */

@Component
@Aspect
public class LoggingAspect {

    /**
     * 定义方法：用于声明切入点表达式，一般该方法不需要其他代码
     * 使用@Pointcut注解声明表达式
     * */
    @Pointcut("execution(public int com.hhp.spring.demo1.Calculator.*(int,int))")
    public void declareJoinPointExpression() {}

    /**
     * 前置通知
     * */
    @Before("declareJoinPointExpression()")
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("The method "+methodName+" begin with "+args);
    }

    /**
     * 后置通知
     * */
    @After("declareJoinPointExpression()")
    public void afterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("The method "+methodName+" ends.");
    }

    /**
     * 返回通知
     * */
    @AfterReturning(value="declareJoinPointExpression()",
    returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method "+methodName+" ends with "+result);
    }

    /**
     * 异常通知
     * */
    @AfterThrowing(value = "declareJoinPointExpression()",
    throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method "+methodName+" occurs "+ex);
    }

    /**
     * 环绕通知
     * */
    @Around("declareJoinPointExpression()")
    public Object aroundMethod(ProceedingJoinPoint pdj) {

        Object result = null;
        String methodName = pdj.getSignature().getName();
        List<Object> args = Arrays.asList(pdj.getArgs());

        try {
            //前置通知
            System.out.println("Before--The method " + methodName + " begins with " + args);
            //执行目标方法
            pdj.proceed();
            //后置返回通知
            System.out.println("AfterReturning--The method " + methodName + " ends with " + result);
        } catch (Throwable throwable) {
            System.out.println("AfterThrowing--The method occurs with " + throwable);
            throw new RuntimeException(throwable);
        }
        //后置通知
        System.out.println("After--The method " + methodName + " ends.");
        return result;
    }

}
