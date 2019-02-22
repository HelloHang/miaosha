package com.miaosha.controller;

import com.miaosha.error.BusinessException;
import com.miaosha.error.EmBussinessError;
import com.miaosha.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by dangao on 2/19/2019.
 */
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class BaseController
{
	public static final String CONTENT_TYPE_FORMED = "application/x-www-form-urlencoded";
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Object handleException(HttpServletRequest request, Exception ex)
	{
		Map<String, Object> responseData = new HashMap<>();
		if(ex instanceof BusinessException)
		{
			BusinessException businessException = (BusinessException) ex;
			responseData.put("errorCode", businessException.getErrorCode());
			responseData.put("errorMsg", businessException.getErrorMsg());
		}
		else
		{
			responseData.put("errorCode", EmBussinessError.UNKNOWN_ERROR.getErrorCode());
			responseData.put("errorMsg", EmBussinessError.UNKNOWN_ERROR.getErrorMsg());
		}
		return CommonReturnType.create(responseData, "fail");
	}
}
