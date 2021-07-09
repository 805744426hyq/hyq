package com.example.demo.dao;

import com.example.demo.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 */
@Repository
public interface UserDao extends BaseLongIdDao<User> {
	/**
	 * 通过用户名查找
	 *
	 * @param username
	 * @return
	 */
	User findByName(String username);

	int countByName(String name);

	User findByNameAndPassword(String name, String password);
}

