package com.hao.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
@Aspect //AOP类
public class TimeAspect {

    @Pointcut("execution(* com.hao.service.*.*(..))") //抽取切入点表达式
    //@Pointcut("@annotation(com.hao.anno.Log)")
    /*public */ private void pt() {
    }

    @Around("pt()")
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        //1.1记录开始时间
        long begin = System.currentTimeMillis();

        //2.1 获取目标对象的类名
        String className = joinPoint.getTarget().getClass().getName();
        log.info("目标对象的类名：{}", className);

        //2.2 获取目标方法的方法名
        String methodName = joinPoint.getSignature().getName();
        log.info("目标方法的方法名：{}", methodName);

        //2.3 获取目标方法运行时传入的参数
        Object[] args = joinPoint.getArgs();
        log.info("目标方法运行时传入的参数：{}", Arrays.toString(args));

        //1.2调用原始方法运行 2.4 目标方法执行
        Object result = joinPoint.proceed();

        //2.4 获取目标方法运行的返回值
        log.info("目标方法运行的返回值：{}", result);

        //1.3记录结束时间，计算方法执行耗时
        long end = System.currentTimeMillis();
        log.info(joinPoint.getSignature() + "方法执行耗时：{}ms", end - begin);

        return result;
    }
}
