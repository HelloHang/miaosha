package com.miaosha.error;

/**
 * Created by dangao on 2/19/2019.
 */
public enum EmBussinessError implements CommonError
{
	PARAMETER_VALIDATION_ERROR(10000, "参数不合法"),
	UNKNOWN_ERROR(20000, "未知错误"),
	USER_NOT_EXIST(10001, "用户不存在");

	private int errorCode;

	private String errorMsg;

	private EmBussinessError(int errorCode, String errorMsg)
	{
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	@Override
	public int getErrorCode()
	{
		return this.errorCode;
	}

	@Override
	public String getErrorMsg()
	{
		return this.errorMsg;
	}

	@Override
	public CommonError setErrorMsg(String errorMsg)
	{
		this.errorMsg = errorMsg;
		return this;
	}
}
