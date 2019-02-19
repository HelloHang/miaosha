package com.miaosha.error;

/**
 * Created by dangao on 2/19/2019.
 */
public class BusinessException extends Exception implements CommonError
{
	private CommonError commonError;

	public BusinessException(CommonError commonError)
	{
		super();
		this.commonError = commonError;
	}

	public BusinessException(CommonError commonError, String errorMsg)
	{
		super();
		this.commonError = commonError;
		this.commonError.setErrorMsg(errorMsg);
	}

	@Override
	public int getErrorCode()
	{
		return this.commonError.getErrorCode();
	}

	@Override
	public String getErrorMsg()
	{
		return  this.commonError.getErrorMsg();
	}

	@Override
	public CommonError setErrorMsg(String errorMsg)
	{
		this.commonError.setErrorMsg(errorMsg);
		return this;
	}

}
