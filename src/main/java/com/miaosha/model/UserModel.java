package com.miaosha.model;

/**
 * Created by dangao on 2/19/2019.
 */
public class UserModel
{
	private Integer id;

	private String name;

	private Byte gender;

	private Integer age;

	private String telphone;

	private String registerMode;

	private String thridPartyId;

	private String encrptPassword;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Byte getGender()
	{
		return gender;
	}

	public void setGender(Byte gender)
	{
		this.gender = gender;
	}

	public String getTelphone()
	{
		return telphone;
	}

	public void setTelphone(String telphone)
	{
		this.telphone = telphone;
	}

	public String getRegisterMode()
	{
		return registerMode;
	}

	public void setRegisterMode(String registerMode)
	{
		this.registerMode = registerMode;
	}

	public String getThridPartyId()
	{
		return thridPartyId;
	}

	public void setThridPartyId(String thridPartyId)
	{
		this.thridPartyId = thridPartyId;
	}

	public String getEncrptPassword()
	{
		return encrptPassword;
	}

	public void setEncrptPassword(String encrptPassword)
	{
		this.encrptPassword = encrptPassword;
	}

	public Integer getAge()
	{
		return age;
	}

	public void setAge(Integer age)
	{
		this.age = age;
	}
}
