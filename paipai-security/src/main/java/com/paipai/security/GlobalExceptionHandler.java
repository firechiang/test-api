package com.paipai.security;

import com.paipai.security.res.RestRes;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 处理所有未捕获的异常
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestRes<String>> handleAllExceptions(Exception ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(RestRes.error("系统繁忙，请稍后重试！"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Http消息不可读异常
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<RestRes<String>> handleNotReadableException(HttpMessageNotReadableException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(RestRes.error("参数格式错误！"), HttpStatus.BAD_REQUEST);
    }

    // 处理 @Valid 验证失败的异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestRes<String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            return new ResponseEntity<>(RestRes.error(error.getDefaultMessage()), HttpStatus.METHOD_NOT_ALLOWED);
        }
        return null;
    }

    // 处理其他约束违反异常（如 @Validated）
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<RestRes<String>> handleConstraintViolation(ConstraintViolationException ex) {
        for (ConstraintViolation<?> cv : ex.getConstraintViolations()) {
            return new ResponseEntity<>(RestRes.error(cv.getMessage()), HttpStatus.METHOD_NOT_ALLOWED);
        }
        return null;
    }
}
