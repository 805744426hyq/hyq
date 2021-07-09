package com.example.demo.service;

import com.example.demo.entity.User;

/**
 * @author 黄永琦
 * @description
 * @date 2021/6/7
 */
public interface UserService {
	/**
	 * 用户登录
	 *
	 * @param username 用户名
	 * @param password 密码
	 * @return 操作结果
	 */
	String login(String username, String password);

	/**
	 * 用户注册
	 *
	 * @param user 用户信息
	 * @return 操作结果
	 */
	String register(User user);

	/**
	 * 刷新密钥
	 *
	 * @param oldToken 原密钥
	 * @return 新密钥
	 */
	String refreshToken(String oldToken);
}
