package com.miaosha.service.impl;

import com.miaosha.dao.UserDOMapper;
import com.miaosha.dao.UserPasswordDOMapper;
import com.miaosha.dataobject.UserDO;
import com.miaosha.dataobject.UserPasswordDO;
import com.miaosha.error.BusinessException;
import com.miaosha.error.EmBussinessError;
import com.miaosha.model.UserModel;
import com.miaosha.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


/**
 * Created by dangao on 2/19/2019.
 */

@Service public class UserServiceImpl implements UserService
{
	@Autowired private UserDOMapper userDOMapper;

	@Autowired private UserPasswordDOMapper userPasswordDOMapper;

	@Override public UserModel getUserById(Integer id)
	{
		UserDO userDO = userDOMapper.selectByPrimaryKey(id);
		UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(id);

		return convertFromDataObject(userDO, userPasswordDO);
	}

	@Override
	@Transactional
	public void register(UserModel userModel) throws BusinessException
	{
		if (userModel == null)
		{
			throw new BusinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR);
		}

		if (StringUtils.isEmpty(userModel.getName()) || StringUtils.isEmpty(userModel.getTelphone()) || StringUtils
			  .isEmpty(userModel.getEncrptPassword()))
		{
			throw new BusinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR);
		}

		UserDO userDO = convertFromModel(userModel);
		try{
			userDOMapper.insertSelective(userDO);
		}
		catch (DuplicateKeyException ex)
		{
			throw new BusinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR, "手机号已注册");
		}
		userModel.setId(userDO.getId());


		UserPasswordDO userPasswordDO = convertPasswordFromModel(userModel);
		userPasswordDOMapper.insertSelective(userPasswordDO);

		return;
	}

	protected UserPasswordDO convertPasswordFromModel(UserModel userModel)
	{
		if (userModel == null)
		{
			return null;
		}
		UserPasswordDO userPasswordDO = new UserPasswordDO();
		userPasswordDO.setUserId(userModel.getId());
		userPasswordDO.setEncrptPassword(userModel.getEncrptPassword());
		return userPasswordDO;
	}

	protected UserDO convertFromModel(UserModel userModel)
	{
		if (userModel == null)
		{
			return null;
		}
		UserDO userDO = new UserDO();
		BeanUtils.copyProperties(userModel, userDO);
		return userDO;
	}

	protected UserModel convertFromDataObject(UserDO userDO, UserPasswordDO userPasswordDO)
	{
		if (userDO == null)
		{
			return null;
		}
		UserModel userModel = new UserModel();
		BeanUtils.copyProperties(userDO, userModel);
		if (userPasswordDO != null)
		{
			userModel.setEncrptPassword(userPasswordDO.getEncrptPassword());
		}
		return userModel;
	}
}
