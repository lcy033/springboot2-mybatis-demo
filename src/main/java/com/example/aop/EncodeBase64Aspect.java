package com.example.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.aop.annotation.EncodeBase64;
import com.example.model.GspMenu;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.runtime.internal.AroundClosure;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.util.StopWatch;
import sun.misc.BASE64Encoder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 加密(目前只支持实体对象与String类型)
 * @author lcy
 */
@Slf4j
@Aspect
@Component
public class EncodeBase64Aspect {

    /**
     * 定义一个 Pointcut, 使用 切点表达式函数 来描述对哪些 Join point 使用 advise.
     */
    @Pointcut("@annotation(com.example.aop.annotation.EncodeBase64)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object encodeBase64(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("加密,开始请求参数 : {}", JSON.toJSONString(joinPoint.getArgs()));
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //获取参数
        Object[] getParameter = joinPoint.getArgs();
        //获取@Param
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        //获取需要加密的字段
        EncodeBase64 decrypt = method.getAnnotation(EncodeBase64.class);
        if (decrypt != null) {
            String[] values = decrypt.value().split(",");
            for (int i = 0; i < getParameter.length; i++) {
                //String
                if (getParameter[i] instanceof String) {
                    for (Annotation annotation : parameterAnnotations[i]) {
                        if (annotation instanceof Param) {
                            for (String value : values) {
                                if (((Param) annotation).value().equals(value)) {
                                    getParameter[i] = new BASE64Encoder().encode(String.valueOf(getParameter[i]).getBytes());
                                }
                            }
                        }
                    }
                    //实体对象
                } else {
                    Object object = getParameter[i];
                    // 获取obj类的字节文件对象
                    Class c = object.getClass();
                    //排除 除了String的类型
                    if (!c.getName().contains("java.")) {
                        for (String value : values) {
                            // 获取该类的成员变量
                            Field f = c.getDeclaredField(value);
                            // 取消语言访问检查
                            f.setAccessible(true);
                            // 获取变量值然后处理在给变量赋值
                            f.set(object, new BASE64Encoder().encode(String.valueOf(f.get(object)).getBytes()));
                        }
                    }
                }
            }
        }
        log.info("加密,结束请求参数 : {}", JSON.toJSONString(getParameter));
        return joinPoint.proceed(getParameter);
    }

}
