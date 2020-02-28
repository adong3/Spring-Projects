package com.adong3.fullstack.dao;

import java.util.List;

import com.adong3.fullstack.model.SysUser;
import org.apache.ibatis.annotations.*;

import com.adong3.fullstack.base.result.Results;

@Mapper
public interface UserDao {

	@Select("select * from sys_user t where t.username = #{username}")
	// @Select("select * from book where author = #{author}")
	SysUser getUser(String username);

	@Select("select count(*) from sys_user t ")
	Long countAllUsers();

	@Select("select * from sys_user")
	List<SysUser> getAllUsers();

	@Select("select * from sys_user t order by t.id limit #{startPosition},#{limit}")
	List<SysUser> getAllUsersByPage(@Param("startPosition") Integer startPosition, @Param("limit") Integer limit);

	@Select("select * from sys_user t where t.telephone = #{telephone}")
	SysUser getUserByPhone(String telephone);

	@Select("select * from sys_user t where t.email = #{email}")
	SysUser getUserByEmail(String email);

	@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("insert into sys_user(username, password, nickname, headImgUrl, phone, telephone, email, birthday, sex, status, createTime, updateTime) values(#{username}, #{password}, #{nickname}, #{headImgUrl}, #{phone}, #{telephone}, #{email}, #{birthday}, #{sex}, #{status}, now(), now())")
	int save(SysUser user);

	@Select("select * from sys_user t where t.id = #{id}")
	SysUser getById(Long id);

	int updateUser(SysUser user);

	@Delete("delete from sys_user where id = #{id}")
	int deleteUser(Long id);

	@Select("select count(*) from sys_user t where t.username like '%${username}%'")
	Long getUserByFuzzyUserName(@Param("username") String username);

	@Select("select * from sys_user t where t.username like '%${username}%' limit #{startPosition},#{limit}")
	List<SysUser> getUserByFuzzyUserNamePage(@Param("username") String username,
			@Param("startPosition") Integer startPosition, @Param("limit") Integer limit);
}
