package com.basis.cloud.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 错误码枚举
 * <p>
 * 错误码规则为 ABBBBCCCC ，共9位
 * <p>
 * A:错误级别。如1代表系统级错误，2代表服务级错误；
 * B:项目或模块名称代码；999 个项目或模块；
 * C:具体错误编号。单个项目/模块有999种错误应该够用；
 * <p>
 * A错误级别码：
 * 1 系统级别
 * 2 服务级错误
 * <p>
 * BBBB项目代码：
 * 000 全局
 * 001 用户、权限类
 * 002 短信类
 * 003 邮件服务类
 * <p>
 * CCCC错误编号：100 ~ 999
 *
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {

    SUCCESS(0, "success", "操作成功"),
    REQUEST_FAIL(1000100, "Request fail", "请求失败"),
    SERVICE_UNAVAILABLE(1000101, "Service unavailable", "服务暂停"),
    IP_LIMIT(1000102, "IP limit", "IP限制不能请求该资源"),
    PARAMETER_ERROR(1000103, "Param error, see doc for more info", "参数错误，请参考API文档"),
    SYSTEM_IS_BUSY(1000104, "Too many pending tasks, system is busy", "任务过多，系统繁忙"),
    ILLEGAL_REQUEST(1000105, "Illegal request", "非法请求"),
    MISS_REQUIRED_PARAMETER(1000106, "Miss required parameter (%s) , see doc for more info", "缺失必选参数 (%s)，请参考API文档"),

    ;

    private int code;
    private String reason;
    private String message;

}
