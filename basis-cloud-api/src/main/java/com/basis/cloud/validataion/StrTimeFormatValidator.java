
package com.basis.cloud.validataion;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * @author jia.yang
 * @version 1.0
 * @date 2020/11/19 下午5:40
 */
@Slf4j
public class StrTimeFormatValidator implements ConstraintValidator<StrTimeFormat, String> {

    private String formatStr;

    @Override
    public void initialize(StrTimeFormat constraintAnnotation) {
        formatStr = constraintAnnotation.formatStr();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean flag = true;
        if (Objects.nonNull(value) && !"".equals(value)) {
            try {
                SimpleDateFormat format = new SimpleDateFormat(formatStr);
                format.setLenient(false);
                format.parse(value);
                flag = true;
            } catch (ParseException e) {
                log.error("时间格式错误", e);
                flag = false;
            }
        }
        return flag;
    }
}
