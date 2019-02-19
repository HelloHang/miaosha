package com.miaosha.service.impl;

import com.miaosha.dao.UserDOMapper;
import com.miaosha.dao.UserPasswordDOMapper;
import com.miaosha.dataobject.UserDO;
import com.miaosha.dataobject.UserPasswordDO;
import com.miaosha.model.UserModel;
import com.miaosha.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by dangao on 2/19/2019.
 */

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserDOMapper userDOMapper;

	@Autowired
	private UserPasswordDOMapper userPasswordDOMapper;

	@Override
	public UserModel getUserById(Integer id)
	{
		UserDO userDO = userDOMapper.selectByPrimaryKey(id);
		UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(id);

		return convertFromDataObject(userDO, userPasswordDO);
	}

	protected UserModel convertFromDataObject(UserDO userDO, UserPasswordDO userPasswordDO)
	{
		if(userDO == null)
		{
			return  null;
		}
		UserModel userModel = new UserModel();
		BeanUtils.copyProperties(userDO, userModel);
		if(userPasswordDO != null)
		{
			userModel.setEncrptPassword(userPasswordDO.getEncrptPassword());
		}
		return userModel;
	}
}
