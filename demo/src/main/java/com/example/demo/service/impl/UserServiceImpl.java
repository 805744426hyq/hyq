package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.util.JsonUtil;
import com.example.demo.util.Md5Util;
import com.example.demo.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 黄永琦
 * @description 注册时会将用户权限设置为ROLE_USER，同时将密码使用md5加密
 * @date 2021/6/7
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class UserServiceImpl implements UserService {

	private final UserDao userDao;
	private final RedisUtil redisUtil;
	private final JsonUtil jsonUtil;

	@Autowired
	public UserServiceImpl(UserDao userDao, RedisUtil redisUtil, JsonUtil jsonUtil) {
		this.userDao = userDao;
		this.redisUtil = redisUtil;
		this.jsonUtil = jsonUtil;
	}

	@Override
	public String login(String username, String password) {
		// 登录时把传过来的密码进行MD5验证
		String originalPassword = Md5Util.stringToMd5(password);
		User user = userDao.findByNameAndPassword(username, originalPassword);
		return Objects.isNull(user) ? "找不到用户" : jsonUtil.objectToJson(user);
	}

	@Override
	public String register(User user) {
		if (userDao.countByName(user.getName()) > 0) {
			return "用户已存在!";
		}
		String string = Md5Util.stringToMd5(user.getPassword());
		user.setPassword(string);
		List<String> roles = new ArrayList<>(1);
		roles.add("ROLE_USER");
		user.setRoles(roles);
		userDao.saveAndFlush(user);
		return jsonUtil.objectToJson(user);
	}

	@Override
	public String refreshToken(String oldToken) {
		return null;
	}
}
