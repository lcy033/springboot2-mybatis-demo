package com.example.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.aop.annotation.DecodeBase64;
import com.example.aop.annotation.EncodeBase64;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import sun.misc.BASE64Encoder;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class DecodeBase64Aspect {

    /**
     * 定义一个 Pointcut, 使用 切点表达式函数 来描述对哪些 Join point 使用 advise.
     */
    @Pointcut("@annotation(com.example.aop.annotation.DecodeBase64)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object checkAuth(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("解密,开始请求参数 : {}", JSON.toJSONString(joinPoint.getArgs()));
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] getParameter = joinPoint.getArgs();
        DecodeBase64 decrypt = method.getAnnotation(DecodeBase64.class);
        if (decrypt != null) {
            String[] values = decrypt.value().split(",");
        }
        log.info("解密,结束请求参数 : {}", JSON.toJSONString(getParameter));
        return joinPoint.proceed(getParameter);
    }

}
