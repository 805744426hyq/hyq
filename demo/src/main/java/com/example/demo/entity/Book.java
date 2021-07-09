package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.StringJoiner;

/**
 * @author 黄永琦
 * @description
 * @date 2021/6/22
 * @version: 1.0
 * @company: 数研院(福建)信息产业发展有限公司
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book extends BaseEntity {

	private static final long serialVersionUID = -1049967254469467215L;
	private String name;
	private String author;

	@Override
	public String toString() {
		return new StringJoiner(", ", Book.class.getSimpleName() + "[", "]")
				.add("name='" + name + "'")
				.add("author='" + author + "'")
				.toString();
	}

}
