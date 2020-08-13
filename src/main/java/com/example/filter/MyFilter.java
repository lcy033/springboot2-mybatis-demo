package com.example.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "myFilter", urlPatterns = {"/api/*", "/gsp/*"})
@Slf4j
public class MyFilter implements Filter {

    /**
     * 初始化
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info(filterConfig.getFilterName() + " init 初始化");
    }

    /**
     * 过滤逻辑
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("myFilter1 begin");
        try {
            log.info("业务方法执行");
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            log.error("error!", e);
        }
        log.info("myFilter1 end");
    }

    /**
     * 销毁
     */
    @Override
    public void destroy() {
        log.error("销毁");
    }
}
