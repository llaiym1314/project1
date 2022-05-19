package com.liliang.ssm.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.liliang.domain.Permission;
import com.liliang.domain.Role;
import com.liliang.ssm.dao.IRoleDao;
import com.liliang.ssm.service.IRoleService;

@Service
@Transactional(propagation = Propagation.REQUIRED,readOnly = false)
public class RoleServiceImpl implements IRoleService{
	
	@Autowired
	private IRoleDao roleDao;
	
	@Override
	public List<Role> findAll() {		
		return roleDao.findAll();
	}

	@Override
	public void addRole(Role role) {
		String roleId = UUID.randomUUID().toString();
		role.setId(roleId);
		roleDao.addRole(role);
	}

	@Override
	public Role findById(String roleId) {
		return roleDao.findById(roleId);
	}

	@Override
	public List<Permission> findRoleByIdAndAllPermission(String roleId) {
		
		return roleDao.findRoleByIdAndAllPermission(roleId);
	}

	@Override
	public void addRoleToPermission(String roleId, String[] permissionIds) {
		for (String permissionId : permissionIds) {
			roleDao.addRoleToPermission(roleId,permissionId);
		}
		
	}

	





}
