package com.basis.cloud.exception;

import lombok.Getter;
import lombok.Setter;
import com.basis.cloud.enums.ErrorCode;

/**
 * 通用业务异常
 *
 */
@Getter
@Setter
public class BizException extends RuntimeException {

    private Integer code;

    private String msg;

    private ErrorCode errorCode;

    private Object obj;

    public BizException() {
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BizException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BizException(ErrorCode errorCode, Object obj) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.obj = obj;
    }


}
