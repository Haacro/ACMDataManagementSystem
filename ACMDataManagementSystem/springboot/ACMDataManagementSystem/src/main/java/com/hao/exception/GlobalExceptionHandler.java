package com.hao.exception;

import com.hao.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class) //捕获所有类型的异常
    public Result ex(Exception ex) {
        /*ex.printStackTrace();*/
        log.info("操作异常");
        return Result.error(StringUtils.hasLength(ex.getMessage()) ? ex.getMessage() : "操作失败！");
    }

}
