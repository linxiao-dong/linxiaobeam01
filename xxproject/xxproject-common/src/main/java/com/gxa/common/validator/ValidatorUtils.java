package com.gxa.common.validator;


import com.gxa.common.exception.ResultException;
import com.gxa.common.utils.AssertUtils;
import com.gxa.common.validator.group.AddGroup;
import com.gxa.common.validator.group.UpdateGroup;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author shelei
 * @since 1.0.0
 */
public class ValidatorUtils {

    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     *
     * @param object 待校验对象
     * @param groups 待校验的组
     * @throws ResultException 校验不通过，则报ResultException异常
     */
    public static void validateEntity(Object object, Class<?>... groups)
            throws ResultException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            StringBuilder msg = new StringBuilder();
            for (ConstraintViolation<Object> constraint : constraintViolations) {
                msg.append(constraint.getMessage()).append("<br>");
            }
            throw new ResultException(msg.toString());
        }
    }


}