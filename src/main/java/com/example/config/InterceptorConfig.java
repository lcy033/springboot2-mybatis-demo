package com.example.config;

import com.example.interceptor.ControllerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Created by finup on 2018/12/12.
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport{

    /**
     * 拦截规则
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //可配置多个实现
        registry.addInterceptor(getControllerInterceptor())
                //需要拦截（默认没有全部拦截）
                .addPathPatterns("/api/**")
                //排除拦截
                .excludePathPatterns("/home/**")
                .excludePathPatterns("/actuator/**")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
    }

    /**
     * 静态拦截规则
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        //排除 swagger
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 拦截器实现
     * @return
     */
    @Bean
    public ControllerInterceptor getControllerInterceptor(){
        return new ControllerInterceptor();
    }
}
