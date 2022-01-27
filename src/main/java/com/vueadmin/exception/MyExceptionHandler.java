package com.vueadmin.exception;

import com.vueadmin.common.api.CommonResult;
import com.vueadmin.common.api.ResultCode;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MyExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = AuthorizationException.class)
    public CommonResult exceptionHandler(Exception e){
        return CommonResult.failed(ResultCode.FORBIDDEN,"权限不足!");
    }
}
