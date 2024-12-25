package com.example.incidentmanage.common;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public Object exceptionHandler(HttpServletRequest request, Exception e){
        LOGGER.error("*****统一异常拦截*****。错误信息:",e);
        // 注解验证抛出的异常
        if(e instanceof MethodArgumentNotValidException){
            // 获取错误信息
            BindingResult bindingResult = ((MethodArgumentNotValidException)e).getBindingResult();
            StringBuilder sb = new StringBuilder();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                sb.append(fieldError.getDefaultMessage());
            }
            return ResultMessage.failure(sb.toString());
        }
        if(e instanceof InvalidDataAccessResourceUsageException){
            return ResultMessage.failure("数据库异常，请检查！");
        }
        return ResultMessage.failure(e.getMessage());
    }
}
