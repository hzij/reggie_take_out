package com.hzj.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * PackageName :com.hzj.common
 * ClassName: GlobalExceptionHandler
 * Description:
 *      全局异常处理
 * @Author 郝紫俊
 * @Create 2023/8/16  14:12
 * @edition 1.0
 */
@ControllerAdvice(annotations ={RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {


    /**
     *
     * 进行异常处理SQLIntegrityConstraintViolationException
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
        public R<String> exceptionHandler(SQLIntegrityConstraintViolationException ex){
            log.error(ex.getMessage());
            if (ex.getMessage().contains("Duplicate entry")){
                String[] split=ex.getMessage().split(" ");
                String msg=split[2]+"已存在";
                return  R.error(msg);
            }
            return R.error("未知错误");
        }

    /**
     * 异常处理方法
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public R<String> exceptionHandler(CustomException ex) {
        log.error(ex.getMessage());

        return R.error(ex.getMessage());
    }
}
