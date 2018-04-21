package com.sndj.recipe.validator;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ValidationUtils {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> ValidationResult validateEntity(T obj) {
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
        if (CollectionUtils.isNotEmpty(set)) {
            result.setHasErrors(true);
            Map<String, String> errorMsg = new HashMap<String, String>();
            for (ConstraintViolation<T> cv : set) {
                errorMsg.put(cv.getPropertyPath().toString(), cv.getMessage());
            }
            result.setErrorMsg(errorMsg);
        }
        return result;
    }

    public static <T> ValidationResult validateProperty(T obj, String propertyName) {
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<T>> set = validator.validateProperty(obj, propertyName, Default.class);
        if (CollectionUtils.isNotEmpty(set)) {
            result.setHasErrors(true);
            Map<String, String> errorMsg = new HashMap<String, String>();
            for (ConstraintViolation<T> cv : set) {
                errorMsg.put(propertyName, cv.getMessage());
            }
            result.setErrorMsg(errorMsg);
        }
        return result;
    }


//    /**
//     * 使用hibernate的注解来进行验证
//     *
//     */
//    private static Validator validator = Validation
//            .byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();
//
//    /**
//     * 功能描述: <br>
//     * 〈注解验证参数〉
//     *
//     * @param obj
//     * @see [相关类/方法](可选)
//     * @since [产品/模块版本](可选)
//     */
//    public static <T> void validate(T obj) {
//        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);
//        // 抛出检验异常
//        if (constraintViolations.size() > 0) {
//            throw new AppException("0001", String.format("参数校验失败:%s", constraintViolations.iterator().next().getMessage()));
//        }
//    }
//}
}