package com.basis.cloud.advice;

import lombok.extern.slf4j.Slf4j;
import net.easipay.support.common.ResponseCode;
import net.easipay.support.dto.Response;
import net.easipay.support.exception.BizException;
import net.easipay.support.exception.CommonErrorCode;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 全局异常拦截，主要拦截验证信息
 *
 * @author jia.yang
 * @version 1.0
 * @date 2021/3/16 11:15
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionTranslator {

    /**
     * 参数校验失败异常
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Response<String> handleBindException(BindException e) {
        return buildErrorResponse(e, e.getBindingResult());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Response<String> handleBindException(MethodArgumentNotValidException e) {
        return buildErrorResponse(e, e.getBindingResult());
    }

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public Response<String> handleBizException(BizException e, HttpServletRequest request) {
        log.error("业务异常", e);
        return Response.error(e.getCode(), e.getMessage());
    }

    /**
     * 全局异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response<String> handleException(Exception e, HttpServletRequest request) {
        log.error(e.getMessage(), e);
        return Response.error(CommonErrorCode.E999999.code(), CommonErrorCode.E999999.msg());
    }

    private Response<String> buildErrorResponse(Exception e, BindingResult bindingResult) {
        if (null != bindingResult && bindingResult.hasErrors()) {
            StringBuffer msg = new StringBuffer();
            List<ObjectError> objectErrors = bindingResult.getAllErrors();
            for (ObjectError objectError : objectErrors) {
                if (objectError instanceof FieldError) {
                    FieldError fieldError = (FieldError) objectError;
                    msg.append(fieldError.getField())
                            .append(":")
                            .append(fieldError.getDefaultMessage())
                            .append(";");
                } else {
                    msg.append(objectError.getDefaultMessage())
                            .append(";");
                }
            }
            return Response.error(ResponseCode.INVALID_ARGUMENT.getCode(), msg.toString());
        }
        log.warn("参数校验异常: ", e);
        return Response.error(ResponseCode.INVALID_ARGUMENT.getCode(), "参数校验失败");
    }
}
