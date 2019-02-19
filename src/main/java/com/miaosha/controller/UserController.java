package com.miaosha.controller;

import com.miaosha.error.BusinessException;
import com.miaosha.error.EmBussinessError;
import com.miaosha.model.UserModel;
import com.miaosha.response.CommonReturnType;
import com.miaosha.service.UserService;
import com.miaosha.viewobject.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * Created by dangao on 2/19/2019.
 */

@Controller
@RequestMapping("/user")
public class UserController extends BaseController
{
	@Autowired
	private UserService userService;

	@GetMapping
	@ResponseBody
	public CommonReturnType getUser(@RequestParam(name = "id") Integer id) throws Exception
	{
		UserModel userModel =  userService.getUserById(id);

		if(userModel == null)
		{
			throw new BusinessException(EmBussinessError.USER_NOT_EXIST);
		}
		UserVO userVO = convertFromModel(userModel);
		return CommonReturnType.create(userVO);
	}

	private UserVO convertFromModel(UserModel userModel)
	{
		UserVO userVO = new UserVO();
		BeanUtils.copyProperties(userModel, userVO);
		return userVO;
	}

}