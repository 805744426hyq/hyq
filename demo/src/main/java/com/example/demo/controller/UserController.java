package com.example.demo.controller;

import com.example.demo.aop.WebLog;
import com.example.demo.bean.UserBean;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author 黄永琦
 * @description 用户管理
 * @date 2021/6/7
 * CrossOrigin 跨域支持
 */

@CrossOrigin
@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/user")
	@ApiOperation(("校验"))
	public List<String> addUser(@Validated @RequestBody UserBean userBean, BindingResult result) {
		List<String> errors = new ArrayList<>();
		if (result.hasErrors()) {
			List<ObjectError> allErrors = result.getAllErrors();
			errors = allErrors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
		}
		return errors;
	}

	@PostMapping("/upload")
	@ApiOperation(("上传文件"))
	public String upload(MultipartFile uploadFile, HttpServletRequest request) {
		String s = "/uploadFile/";
		// String realPath = System.getProperty("user.dir")+s;
		String realPath = request.getSession().getServletContext().getRealPath(s);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String format = sdf.format(new Date());
		File folder = new File(realPath + format);
		if (!folder.isDirectory()) {
			folder.mkdirs();
		}
		String oldName = uploadFile.getOriginalFilename();
		String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."), oldName.length());
		try {
			uploadFile.transferTo(new File(folder, newName));
			return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + s + format + "/" + newName;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "上传失败!";
	}

	@GetMapping("/hello")
	@WebLog
	@ApiOperation("hello")
	public List<User> users() {
		List<User> users = new ArrayList<>(2);
		users.add(new User("张三", "12345"));
		users.add(new User("李四", "54321"));
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("users", users);
//        modelAndView.setViewName("users");
		return users;
		//   return new Book("李四","54321");
	}

	/**
	 * 用户登录
	 *
	 * @param name     用户名
	 * @param password 密码
	 * @return 操作结果
	 * @throws AuthenticationException 错误信息
	 */
	@ApiOperation("登录")
	@WebLog
	@PostMapping(value = "/login", params = {"name", "password"})
	public String login(String name, String password) throws AuthenticationException {
		return userService.login(name, password);
	}

	/**
	 * 用户注册
	 *
	 * @param user 用户信息
	 * @return 操作结果
	 * @throws AuthenticationException 错误信息
	 */
	@PostMapping(value = "/register")
	@ApiOperation("注册")
	public String register(@RequestBody User user) throws AuthenticationException {
		return userService.register(user);
	}

	/**
	 * 刷新密钥
	 *
	 * @param authorization 原密钥
	 * @return 新密钥
	 * @throws AuthenticationException 错误信息
	 */
	@GetMapping(value = "/refreshToken")
	@ApiOperation("刷新token")
	public String refreshToken(@RequestHeader String authorization) throws AuthenticationException {
		return userService.refreshToken(authorization);
	}
}
