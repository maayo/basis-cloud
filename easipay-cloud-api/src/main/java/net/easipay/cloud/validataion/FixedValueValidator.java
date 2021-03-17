
package net.easipay.cloud.validataion;

import net.easipay.support.util.StringUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author jia.yang
 * @version 1.0
 * @date 2020/11/19 下午5:40
 */
public class FixedValueValidator implements ConstraintValidator<FixedValue, Object> {

    private String[] strValues;
    private int[] intValues;

    @Override
    public void initialize(FixedValue fixedValue) {
        strValues = fixedValue.strValues();
        intValues = fixedValue.intValues();

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        boolean flag = false;
        if (StringUtil.isBlank(String.valueOf(value))) {
            flag = true;
        }
        if (value instanceof String) {
            for (String s : strValues) {
                if (s.equals(value)) {
                    flag = true;
                }
            }
        } else if (value instanceof Integer) {
            for (Integer s : intValues) {
                if (s == value) {
                    flag = true;
                }
            }
        }
        return flag;

    }
}
