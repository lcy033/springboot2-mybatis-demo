package com.example.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerAspect {

    private final Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);

    /**
     * 方法名
     */
    private ThreadLocal<String> methodName = new ThreadLocal<>();

    /**
     * 开始时间
     */
    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution( * com.example..controller..*Controller.*(..))")
    public void controllerLog() {
    }


    @Before("controllerLog()")
    public void doBefore(JoinPoint joinPoint) {
        methodName.set(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        try {
            String s = JSON.toJSONString(joinPoint.getArgs());
            LOGGER.info("执行 {} 请求参数 : {}", methodName.get(), s);
        } catch (Exception e) {
            LOGGER.info("执行 {} 请求参数 : {}", methodName.get(), joinPoint.getArgs());
        }
        startTime.set(System.currentTimeMillis());
    }

    @After("controllerLog()")
    public void doAfter() {
        long time = System.currentTimeMillis() - startTime.get();
        LOGGER.info("执行 {} 耗时为 : {}ms", methodName.get(), time);
    }

    @AfterReturning(returning = "object", pointcut = "controllerLog()")
    public void doAfterReturning(Object object) {
        try {
            String s = JSON.toJSONString(object);
            LOGGER.info("执行 {} 响应结果 : {}", methodName.get(), s);
        } catch (Exception e) {
            LOGGER.info("执行 {} 响应结果 : {}", methodName.get(), object);
        } finally {
            methodName.remove();
            startTime.remove();
        }
    }

}
