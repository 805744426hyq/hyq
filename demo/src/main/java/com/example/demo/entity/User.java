package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Collection;
import java.util.List;

/**
 * @author 黄永琦
 * @description
 * @date 2021/6/7
 */
@Entity
public class User extends BaseEntity {
	private static final long serialVersionUID = 6609156873328244902L;
	private String name;
	private String password;
	@Transient
	@JsonIgnore
	private List<String> roles;
	@Transient
	@JsonIgnore
	private Collection<? extends GrantedAuthority> authorities;

	public User(String name, String password, Collection<? extends GrantedAuthority> authorities) {
		this.name = name;
		this.password = password;
		this.authorities = authorities;
	}

	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public User() {

	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
}
