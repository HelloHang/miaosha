package com.miaosha.response;

/**
 * Created by dangao on 2/19/2019.
 */
public class CommonReturnType
{
	private String status;

	private Object data;

	public static CommonReturnType create(Object result)
	{
		return create(result, "success");
	}

	public static CommonReturnType create(Object result, String status)
	{
		CommonReturnType commonReturnType = new CommonReturnType();
		commonReturnType.setStatus(status);
		commonReturnType.setData(result);
		return commonReturnType;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public Object getData()
	{
		return data;
	}

	public void setData(Object data)
	{
		this.data = data;
	}
}
