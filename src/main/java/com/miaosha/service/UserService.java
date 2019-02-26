package com.miaosha.service;

import com.miaosha.error.BusinessException;
import com.miaosha.model.UserModel;


/**
 * Created by dangao on 2/19/2019.
 */
public interface UserService
{
	UserModel getUserById(Integer id);

	void register(UserModel userModel) throws BusinessException;

	UserModel validateLogin(String telphone, String encrptPassword) throws BusinessException;
}
