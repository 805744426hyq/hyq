package com.example.demo.bean;

import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * @author 黄永琦
 * @description
 * @date 2021/7/6
 */
@Data
public class UserBean implements Serializable {
	private Integer id;
	// size 表示一个字符串的长度或者一个集合的大小，必须在某一个范围中； min 参数表示范围的下限； max参数表示范围的上限； message 表示技验失败时的提示信息。
	@Size(min = 5, max = 10, message = "user.name.size")
	private String name;

	@NotNull(message = "{user.address.notnull}")
	private String address;

	// DecimalMin 表示对应属性值的下限，＠DecimalMax阻注解表示对应属性值的上限
	@DecimalMax(value = "150", message = "{user.age.size}")
	@DecimalMin(value = "1", message = "{user.age.size}")
	private Integer age;

	@Email(message = "{user.email.pattern}")
	@NotNull(message = "{user.email.notnull}")
	private String email;
}
