package com.example.aop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AuthCheckerAspect {

    /**
     * 方法名
     */
    private ThreadLocal<String> methodName = new ThreadLocal<>();

    /**
     * 定义一个 Pointcut, 使用 切点表达式函数 来描述对哪些 Join point 使用 advise.
     */
    @Pointcut("@annotation(com.example.aop.annotation.AuthChecker)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object checkAuth(ProceedingJoinPoint joinPoint) throws Throwable {
        methodName.set(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        try {
            String s = JSON.toJSONString(joinPoint.getArgs());
            log.info("权限认证,执行 {} 请求参数 : {}", methodName.get(), s);
            if (!s.contains("menuName")){
                log.info("权限认证,[错误, 权限不合法!]执行 {} 请求参数 : {}", methodName.get(), s);
                return false;
            }
        } catch (Exception e) {
            log.info("权限认证,执行 {} 请求参数 : {}", methodName.get(), joinPoint.getArgs());
        }

        return joinPoint.proceed();
    }

}
