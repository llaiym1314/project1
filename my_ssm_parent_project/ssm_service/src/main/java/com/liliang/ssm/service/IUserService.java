package com.liliang.ssm.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.liliang.domain.Role;
import com.liliang.domain.UserInfo;

public interface IUserService extends UserDetailsService{

	List<UserInfo> findAll();

	void add(UserInfo user);

	UserInfo findById(String id);

	List<Role> findUserByIdAndAllRole(String userId);

	void addRoleToUser(String userId, String[] roleIds);

}
