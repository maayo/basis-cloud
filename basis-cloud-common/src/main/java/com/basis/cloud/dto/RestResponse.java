package com.basis.cloud.dto;

import com.basis.cloud.enums.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * 统一响应
 *
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class RestResponse<T> implements Serializable {

	/**
	 * 状态码
	 */
	private int code;

	/**
	 * 是否成功
	 */
	private boolean success;

	/**
	 * 承载数据
	 */
	private T data;

	/**
	 * 返回消息
	 */
	private String message;



	public RestResponse<T> data(T data) {
		this.data = data;
		return this;
	}

	public RestResponse<T> message(String message) {
		this.message = message;
		return this;
	}

	public RestResponse<T> code(int code) {
		this.code = code;
		this.success = (this.code == 0);
		return this;
	}


	public static <T> RestResponse<T> success() {
		return of(ErrorCode.SUCCESS);
	}

	public static <T> RestResponse<T> success(T data) {
		RestResponse<T> restResponse = of(ErrorCode.SUCCESS);
		restResponse.data = data;
		return restResponse;
	}

	public static <T> RestResponse<T> fail() {
		return fail(ErrorCode.REQUEST_FAIL);
	}

	public static <T> RestResponse<T> fail(String message) {
		RestResponse<T> restResponse = new RestResponse<>();
		restResponse.code = ErrorCode.REQUEST_FAIL.getCode();
		restResponse.message = message;
		return restResponse;
	}

	public static <T> RestResponse<T> fail(int code, String message) {
		RestResponse<T> restResponse = new RestResponse<>();
		restResponse.code = code;
		restResponse.message = message;
		return restResponse;
	}

	public static <T> RestResponse<T> fail(ErrorCode errorCode) {
		return of(errorCode);
	}

	public static <T> RestResponse<T> of(ErrorCode errorCode) {
		RestResponse<T> restResponse = new RestResponse<>();
		restResponse.code = errorCode.getCode();
		restResponse.success = (restResponse.code == 0);
		restResponse.message = errorCode.getMessage();
		return restResponse;
	}

}
