package com.miaosha.validator;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by dangao on 2/25/2019.
 */
public class ValidationResult
{
	private boolean hasErrors;

	private Map<String, String> errorMsgMap;

	public ValidationResult()
	{
		hasErrors = false;
		errorMsgMap = new HashMap<>();
	}

	public boolean isHasErrors()
	{
		return hasErrors;
	}

	public void setHasErrors(boolean hasErrors)
	{
		this.hasErrors = hasErrors;
	}

	public Map<String, String> getErrorMsgMap()
	{
		return errorMsgMap;
	}

	public void setErrorMsgMap(Map<String, String> errorMsgMap)
	{
		this.errorMsgMap = errorMsgMap;
	}

	public String getErrorMessage()
	{
		return StringUtils.join(errorMsgMap.values().toArray(), ",");
	}
}
