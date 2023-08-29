package com.hzj.common;

/**
 * PackageName :com.hzj.common
 * ClassName: CustomException
 * Description:
 *
 * @Author 郝紫俊
 * @Create 2023/8/27  16:38
 * @edition 1.0
 */
public class CustomException extends RuntimeException{
    public CustomException(String message){
        super(message);
    }
}
