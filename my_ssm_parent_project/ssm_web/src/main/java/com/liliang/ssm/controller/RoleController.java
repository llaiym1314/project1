package com.liliang.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.liliang.domain.Permission;
import com.liliang.domain.Role;
import com.liliang.ssm.service.IRoleService;



@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private IRoleService roleService;

	@RequestMapping("/findAll")
	public ModelAndView findAll() {
		List<Role> roleList =  roleService.findAll();
		ModelAndView mav = new ModelAndView();
		mav.addObject("roleList",roleList);
		mav.setViewName("role-list");
		return mav;
		
	}
	
	@RequestMapping("/save")
	public String save(Role role) {
		roleService.addRole(role);
		return "redirect:/role/findAll";
		
	}
	
	//添加角色权限资源
	@RequestMapping("/findRoleByIdAndAllPermission")
	public ModelAndView findRoleByIdAndAllPermission(String id) {
		Role role = roleService.findById(id);
		List<Permission> permissionList = roleService.findRoleByIdAndAllPermission(id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("permissionList",permissionList);
		mav.addObject("role",role);
		mav.setViewName("role-permission-add");
		return mav;
		
	}			
	//添加角色的资源权限的方法
	@RequestMapping("/addRoleToPermission")
	public String addRoleToPermission(@RequestParam("roleId")String roleId,@RequestParam("ids")String[] permissionIds) {
		roleService.addRoleToPermission(roleId,permissionIds);
		return "redirect:/role/findAll";
	}
}
