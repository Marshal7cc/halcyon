package com.marshal.halcyon.core.component;

import org.springframework.stereotype.Component;

import javax.validation.*;
import java.util.Set;

/**
 * @auth: Marshal
 * @date: 2018/10/28
 * @desc: 通用校验器，用于校验数据，通过属性的限制性注解进行校验判断
 */
@Component
public class CommonValidator {

    private static Validator validator;

    static {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
    }

    /**
     * 返回不符合规范de错误信息
     *
     * @param t
     * @param <T>
     * @return
     */
    public <T> String getErrors(T t) {
        Set<ConstraintViolation<T>> set = validator.validate(t);
        StringBuilder validateError = new StringBuilder();
        for (ConstraintViolation<T> val : set) {
            validateError.append(val.getPropertyPath() + ":" + val.getMessage() + ";\r\n");
        }
        return validateError.toString();
    }

    /**
     * 验证是否通过
     *
     * @param t
     * @param <T>
     * @return
     */
    public <T> boolean hasError(T t) {
        Set<ConstraintViolation<T>> set = validator.validate(t);
        if (set.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}

