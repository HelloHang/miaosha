package com.miaosha.error;

/**
 * Created by dangao on 2/19/2019.
 */
public interface CommonError
{
	int getErrorCode();
	String getErrorMsg();
	CommonError setErrorMsg(String errorMsg);
}
