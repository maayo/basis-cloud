package com.basis.cloud.annotaion;



import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.basis.cloud.common.json.SensitiveInfoSerialize;
import com.basis.cloud.enums.SensitiveType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 脱敏注解
 *
 * @author biezhi
 * @date 2019-07-31
 */
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = SensitiveInfoSerialize.class)

public @interface SensitiveInfo {

	SensitiveType value();

}