package com.liliang.ssm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.liliang.domain.Permission;


public interface IPermissionService {

	List<Permission> findAll();

	void addPermission(Permission permission);

}
