package com.liliang.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import com.liliang.domain.Permission;
import com.liliang.domain.Role;

@Repository
public interface IRoleDao {
	
	@Select("select * from role where id in (select roleId from users_role where userId = #{userId})")
	@Results({
		@Result(id = true,column = "id", property = "id"),
		@Result(column = "roleName", property = "roleName"),
		@Result(column = "roleDesc", property = "roleDesc"),
		@Result(column = "id" ,property = "permissions",javaType = List.class,many = @Many(select = "com.liliang.ssm.dao.IPermissionDao.findByRoleId" ,fetchType = FetchType.LAZY) )	
	})
	public List<Role> findByUserId(String userId);

	@Select("select * from role")
	public List<Role> findAll();

	@Insert("insert into role (id,roleName,roleDesc) values(#{id},#{roleName},#{roleDesc})")
	public void addRole(Role role);

	@Select("select * from role where id = #{roleId}")
	public Role findById(String roleId);

	@Select("select * from permission where id not in(select permissionId from role_permission where roleId = #{roleId})")
	public List<Permission> findRoleByIdAndAllPermission(String roleId);

	@Insert("insert into role_permission (permissionId,roleId) values(#{permissionId},#{roleId})")
	public void addRoleToPermission(@Param("roleId")String roleId, @Param("permissionId")String permissionId);
	
}
