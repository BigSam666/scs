package com.zhcs.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.zhcs.utils.ZHCSException;

//*****************************************************************************
/**
 * <p>Title:ValidatorUtils</p>
 * <p>Description:hibernate-validator校验工具类</p>
 * 参考文档：http://docs.jboss.org/hibernate/validator/5.4/reference/en-US/html_single/
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年5月26日
 */
//*****************************************************************************
public class ValidatorUtils {
    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    //*************************************************************************
    /** 
    * 【校验】校验对象
    * @param object 待校验对象
    * @param groups 待校验的组
    * @throws ZHCSException   校验不通过，则报ZHCSException异常
    */
    //*************************************************************************
    public static void validateEntity(Object object, Class<?>... groups)
            throws ZHCSException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
        	ConstraintViolation<Object> constraint = (ConstraintViolation<Object>)constraintViolations.iterator().next();
            throw new ZHCSException(constraint.getMessage());
        }
    }
}
