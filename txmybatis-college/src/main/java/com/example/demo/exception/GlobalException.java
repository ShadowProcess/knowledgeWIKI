package com.example.demo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;
import java.util.List;

@Slf4j
@ControllerAdvice
public class GlobalException {


    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String exceptionHandler(Exception e, HttpServletRequest request){
        log.error("请求URL：{}",request.getRequestURL());
        log.error("全局异常信息：{}",e.getMessage());
        return e.getMessage();
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public String accessDeniedHandler(AccessDeniedException e){
        log.error("拒绝授权：{}",e.getMessage());
        return e.getMessage();
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBodyValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        log.error("参数绑定异常：{}", fieldErrors.get(0).getDefaultMessage());
        return (fieldErrors.get(0).getDefaultMessage());
    }
}
