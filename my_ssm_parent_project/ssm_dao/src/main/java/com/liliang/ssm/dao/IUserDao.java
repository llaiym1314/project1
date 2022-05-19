package com.liliang.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.liliang.domain.Role;
import com.liliang.domain.UserInfo;


@Repository
public interface IUserDao {
	
	
	@Select("select * from users where username  = #{username} ")
	@Results({
		@Result(id = true,column = "id",property = "id"),
		@Result(column = "email",property = "email"),
		@Result(column = "username",property = "username"),
		@Result(column = "password",property = "password"),
		@Result(column = "phoneNum",property = "phoneNum"),
		@Result(column = "status",property = "status"),
		@Result(column = "id",property = "roles",javaType = List.class,many = @Many(select = "com.liliang.ssm.dao.IRoleDao.findByUserId",fetchType = FetchType.LAZY))
			})
	public UserInfo findByUsername(String username);

	
	@Select("select * from users")
	public List<UserInfo> findAll();

	@Insert("insert into users (id,username,email,password,phoneNum,status) values(#{id},#{username},#{email},#{password},#{phoneNum},#{status})")
	public void add(UserInfo user);

	@Select("select * from users where id  = #{id} ")
	@Results({
		@Result(id = true,column = "id",property = "id"),
		@Result(column = "email",property = "email"),
		@Result(column = "username",property = "username"),
		@Result(column = "password",property = "password"),
		@Result(column = "phoneNum",property = "phoneNum"),
		@Result(column = "status",property = "status"),
		@Result(column = "id",property = "roles",javaType = List.class,many = @Many(select = "com.liliang.ssm.dao.IRoleDao.findByUserId",fetchType = FetchType.LAZY))
			})
	public UserInfo findById(String id);

	@Select("SELECT * FROM role WHERE id not IN (SELECT roleId FROM users_role WHERE userId = #{userId})")
	public List<Role> findUserByIdAndAllRole(String userId);

	
	@Insert("insert into users_role (userId,roleId) VALUES(#{userId},#{roleId})")
	public void addRoleToUser(@Param("userId")String userId, @Param("roleId")String roleId);

}
