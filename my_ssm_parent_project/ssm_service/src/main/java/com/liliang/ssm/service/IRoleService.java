package com.liliang.ssm.service;

import java.util.List;

import com.liliang.domain.Permission;
import com.liliang.domain.Role;

public interface IRoleService {

	List<Role> findAll();

	void addRole(Role role);

	Role findById(String roleId);

	List<Permission> findRoleByIdAndAllPermission(String roleId);

	void addRoleToPermission(String roleId, String[] permissionIds);





	

}
