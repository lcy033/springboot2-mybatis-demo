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
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

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
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] getParameter = joinPoint.getArgs();
        Object rvt = joinPoint.proceed(getParameter);
        log.info("解密,开始请求参数 : {}", JSON.toJSONString(rvt));
        DecodeBase64 decrypt = method.getAnnotation(DecodeBase64.class);
        if (decrypt != null) {
            String[] values = decrypt.value().split(",");
            if (rvt instanceof List) {
                List<Object> list = (List<Object>) rvt;
                for (Object o : list){
                    if (o instanceof String) {
                        rvt = new String(new BASE64Decoder().decodeBuffer(String.valueOf(rvt)));
                    } else{
                        // 获取obj类的字节文件对象
                        Class c = o.getClass();
                        this.changeVariable(c, values, o);
                    }
                }

            } else if (rvt instanceof String) {
                rvt = new String(new BASE64Decoder().decodeBuffer(String.valueOf(rvt)));
            } else if (rvt instanceof Map) {
                for (String value : values) {
                    Object decryptParameters = ((Map) rvt).get(value);
                    if (((Map) rvt).get(value) != null) {
                        ((Map) rvt).put(value, new String(new BASE64Decoder().decodeBuffer(String.valueOf(decryptParameters))));
                    }
                }
            } else {
                // 获取obj类的字节文件对象
                Class c = rvt.getClass();
                this.changeVariable(c, values, rvt);
            }
        }
        log.info("解密,结束请求参数 : {}", JSON.toJSONString(rvt));
        return rvt;
    }

    /**
     * 更改值
     */
    private void changeVariable(Class c, String[] values, Object object) throws Throwable {
        //排除 除了String的类型
        if (!c.getName().contains("java.")) {
            for (String value : values) {
                // 获取该类的成员变量
                Field f = c.getDeclaredField(value);
                // 取消语言访问检查
                f.setAccessible(true);
                // 给变量赋值
                f.set(object, new String(new BASE64Decoder().decodeBuffer(String.valueOf(f.get(object)))));
            }
        }
    }

}
