package com.liliang.ssm.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.liliang.domain.Role;
import com.liliang.domain.UserInfo;
import com.liliang.ssm.dao.IUserDao;
import com.liliang.ssm.service.IUserService;

/*
 * 认证服务类
 */

@Service("userService")
@Transactional(propagation = Propagation.REQUIRED,readOnly = false)
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserInfo userInfo = userDao.findByUsername(username);
		User user = null;
		if(userInfo != null) {
			//user = new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),userInfo.getStatus() == 1 ? true : false,true,true,true,getGrantedAuthority(userInfo.getRoles()));
			user = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus() == 1 ? true : false,true,true,true,getGrantedAuthority(userInfo.getRoles()));
		}else {
			throw new RuntimeException("用户不存在!!!");
		}
		
		
		return user;
		
	}

	private Collection<? extends GrantedAuthority> getGrantedAuthority(List<Role> roles) {
		//
		
		ArrayList<GrantedAuthority> list = new ArrayList<>();
		for (Role role : roles) {
			list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
		}
//		list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));写死 
//		list.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		return list;
	}

	@Override
	public List<UserInfo> findAll() {
		return userDao.findAll();
	}

	@Override
	public void add(UserInfo user) {
		String password = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(password);
		String id = UUID.randomUUID().toString();
		user.setId(id);
		userDao.add(user);	
	}

	@Override
	public UserInfo findById(String id) {
		return userDao.findById(id);		 
	}

	@Override
	public List<Role> findUserByIdAndAllRole(String userId) {
		
		return userDao.findUserByIdAndAllRole(userId);
	}

	@Override
	public void addRoleToUser(String userId, String[] roleIds) {
		for (String roleId : roleIds) {
			userDao.addRoleToUser(userId,roleId);
		}
		
	}



}
