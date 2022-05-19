package com.liliang.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.liliang.domain.Permission;

@Repository
public interface IPermissionDao {
	
	@Select("select * from permission")
	public List<Permission> findAll() ;

	@Insert("insert into permission (id,permissionName,url) values(#{id},#{permissionName},#{url})")
	public void addPermission(Permission permission);
	
	
	@Select("select * from permission where id in(select permissionId from role_permission where roleId = #{roleId})")
	public Permission findByRoleId(String roleId);
}
