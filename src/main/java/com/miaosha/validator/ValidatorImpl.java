package com.miaosha.validator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.*;
import java.util.Set;


/**
 * Created by dangao on 2/25/2019.
 */
@Component
public class ValidatorImpl implements InitializingBean
{
	private Validator validator;

	@Override
	public void afterPropertiesSet() throws Exception
	{
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	public  ValidationResult validate(Object bean)
	{
		ValidationResult result = new ValidationResult();
		Set<ConstraintViolation<Object>> constraintViolationSet = validator.validate(bean);
		if(constraintViolationSet.size() > 0)
		{
			result.setHasErrors(true);
			constraintViolationSet.forEach(constraintViolation -> {
				String errorMsg = constraintViolation.getMessage();
				String propertyName = constraintViolation.getPropertyPath().toString();
				result.getErrorMsgMap().put(propertyName, errorMsg);
			});
		}

		return result;
	}
}
