package com.miaosha.controller;

import com.alibaba.druid.util.StringUtils;
import com.miaosha.error.BusinessException;
import com.miaosha.error.EmBussinessError;
import com.miaosha.model.UserModel;
import com.miaosha.response.CommonReturnType;
import com.miaosha.service.UserService;
import com.miaosha.viewobject.UserVO;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


/**
 * Created by dangao on 2/19/2019.
 */

@Controller
@RequestMapping("/user")
public class UserController extends BaseController
{
	@Autowired
	private UserService userService;

	@Autowired
	private HttpServletRequest httpServletRequest;

	//用户注册接口
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = {CONTENT_TYPE_FORMED})
	@ResponseBody
	public CommonReturnType register(@RequestParam(name="telphone") String telphone,
		  @RequestParam(name="optCode") String optCode, @RequestParam(name="name") String name,
		  @RequestParam(name = "gender") Integer gender, @RequestParam(name="age") Integer age,
		  @RequestParam(name="password") String password) throws Exception
	{
		//验证手机号和对应的opt相符合
		String inSessionOptCode = (String) this.httpServletRequest.getSession().getAttribute(telphone);

		if(!StringUtils.equals(inSessionOptCode, optCode))
		{
			throw new BusinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR, "验证码不正确");
		}

		UserModel userModel = new UserModel();
		userModel.setName(name);
		userModel.setAge(age);
		userModel.setGender(new Byte(String.valueOf(gender)));
		userModel.setTelphone(telphone);
		userModel.setRegisterMode("byPhone");
		userModel.setEncrptPassword(this.encodeByMd5(password));
		userService.register(userModel);
		return CommonReturnType.create(null);
	}


	protected String encodeByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException
	{
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		BASE64Encoder base64Encoder = new BASE64Encoder();
		String encodeString = base64Encoder.encode(md5.digest(str.getBytes("utf-8")));
		return encodeString;
	}

	@RequestMapping(value = "/getOtp", method = RequestMethod.POST, consumes = {CONTENT_TYPE_FORMED})
	@ResponseBody
	public CommonReturnType getOtp(@RequestParam(name="telphone")String telphone)
	{
		Random random = new Random();
		int randomInt = random.nextInt(99999);
		randomInt += 10000;
		String otpCode = String.valueOf(randomInt);

		httpServletRequest.getSession().setAttribute(telphone, otpCode);

		System.out.println("telphone=" + telphone + "&otpCode=" + otpCode);

		return CommonReturnType.create(null);

	}

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
