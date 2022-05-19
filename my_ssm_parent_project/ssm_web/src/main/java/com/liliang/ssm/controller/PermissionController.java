package com.liliang.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.liliang.domain.Permission;
import com.liliang.domain.Role;
import com.liliang.ssm.service.IPermissionService;

@Controller
@RequestMapping("/permission")
public class PermissionController {

	@Autowired
	private IPermissionService permissionService;
	
	@RequestMapping("/findAll")
	public ModelAndView findAll() {
		List<Permission> permissionList =  permissionService.findAll();
		ModelAndView mav = new ModelAndView();
		mav.addObject("permissionList",permissionList);
		mav.setViewName("permission-list");
		return mav;
		
	}
	
	@RequestMapping("/save")
	public String save(Permission permission) {
		permissionService.addPermission(permission);
		return "redirect:/permission/findAll";
		
	}
}
