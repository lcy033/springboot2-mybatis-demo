package com.example.controller;

import com.example.enums.ResponseCode;
import com.example.exception.extend.BusinessException;
import com.example.model.base.ResponseVo;
import com.example.service.MailService;
import com.example.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * 统一错误处理入口
 */
@ControllerAdvice(basePackages = "com.example.controller")
@Slf4j
public class ErrorControllerAdvice {

    @Autowired
    private MailService mailService;

    @ExceptionHandler(Exception.class)
    @ResponseBody
    ResponseVo<String> handleControllerException(HttpServletRequest request, Exception ex) {
        String token = request.getHeader("token");
        String requestUri = request.getRequestURI();
        log.error("接口错误统一处理,方法:{},时间:{},token:{},message:{}", requestUri, DateUtil.getDateTimePatternConnect(), token, ex.getMessage(), ex);
        String errorCode = "";
        String errorMessage = "";
        if (ex instanceof BusinessException) {
            errorCode = ((BusinessException) ex).getErrorCode();
            errorMessage = ex.getMessage();
        } else if (ex instanceof IllegalArgumentException) {
            errorCode = ResponseCode.PARAMETER_ERROR.getValue();
            errorMessage = ex.getMessage();
        } else if (ex instanceof HttpMessageNotReadableException) {
            errorCode = ResponseCode.DATA_INFO_ERROR.getValue();
            errorMessage = ResponseCode.DATA_INFO_ERROR.getMessage();
        } else {
            errorCode = ResponseCode.INTERNAL_SERVER_ERROR.getValue();
            errorMessage = ResponseCode.INTERNAL_SERVER_ERROR.getMessage();
        }
        if (errorMessage.contains(ResponseCode.INTERNAL_SERVER_ERROR.getMessage())) {
            boolean product = true;
            //todo 判断测试环境还是生产可以根据不同的结果发送
            String environment = product ? "生产环境" : "测试环境";
            String title = environment + "[个贷APP]时间:" + DateUtil.getDateTimePatternConnect();
            try {
                String body = IOUtils.toString(request.getInputStream(),request.getCharacterEncoding());
                String content = title + ",方法:" + requestUri + ",参数:" + body + ",token:" + token;
                log.info("预警,请求参数 : {}", content);
                //发送邮件
                mailService.sendSimpleMail(product, title, content);
            } catch (IOException ignored) {
            }
        }
        return ResponseVo.ofError(errorCode, errorMessage);
    }
}
