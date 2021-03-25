package com.basis.cloud.validataion;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 字符串时间格式校验
 *
 * @author jia.yang
 * @version 1.0
 * @date 2020/11/19 下午5:40
 */
@Constraint(validatedBy = {StrTimeFormatValidator.class})
@Documented
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface StrTimeFormat {

    String message() default "必须为指定时间格式!";

    String formatStr() default "yyyy-mm-dd";;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        StrTimeFormat[] value();
    }

}
