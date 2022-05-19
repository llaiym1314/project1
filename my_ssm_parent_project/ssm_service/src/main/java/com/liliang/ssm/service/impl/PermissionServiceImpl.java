package com.liliang.ssm.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.liliang.domain.Permission;
import com.liliang.ssm.dao.IPermissionDao;
import com.liliang.ssm.service.IPermissionService;

@Service
@Transactional(propagation = Propagation.REQUIRED,readOnly = false)
public class PermissionServiceImpl implements IPermissionService{
	
	@Autowired
	private IPermissionDao permissionDao;
	
	@Override
	public List<Permission> findAll() {	
		return permissionDao.findAll();
	}

	@Override
	public void addPermission(Permission permission) {
		String permissionId = UUID.randomUUID().toString();
		permission.setId(permissionId);
		permissionDao.addPermission(permission);
		
	}

}
