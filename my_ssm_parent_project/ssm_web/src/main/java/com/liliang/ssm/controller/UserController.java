package com.liliang.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.liliang.domain.Role;
import com.liliang.domain.UserInfo;
import com.liliang.ssm.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	//查询所有的用过户的方法
	@RequestMapping("/findAll")
	public ModelAndView findAll() {	
		
		List<UserInfo> userList = userService.findAll();
		ModelAndView mav= new ModelAndView();
		mav.addObject("userList",userList);
		mav.setViewName("user-list");
		return mav;
	}
	
	
	//保存用户的方法
	@RequestMapping("/save")
	public String save(UserInfo user) {	
		userService.add(user);
		return "redirect:/user/findAll";
	
	}
	
	//查询用户角色详情的方法
	@RequestMapping("/findById")
	public ModelAndView findById(String id) {		
		UserInfo userInfo =  userService.findById(id);		
		ModelAndView mav = new ModelAndView();
		mav.addObject("user",userInfo);
		mav.setViewName("/user-show");
		return mav;
		
	}
	//查询用户的不包含的角色的方法
	@RequestMapping("/findUserByIdAndAllRole")
	public  ModelAndView findUserByIdAndAllRole(@RequestParam("id")String userId) {			
		List<Role> roles = userService.findUserByIdAndAllRole(userId);
		UserInfo userInfo =  userService.findById(userId);
		ModelAndView mav = new ModelAndView();		
		mav.addObject("roleList",roles);
		mav.addObject("user",userInfo);
		mav.setViewName("user-role-add");
		return mav;		
	}
	
	@RequestMapping("/addRoleToUser")
	public String addRoleToUser(@RequestParam("userId")String userId ,@RequestParam("ids")String[] roleIds) {
		userService.addRoleToUser(userId,roleIds);
		return "redirect:/user/findAll";
	}
}
