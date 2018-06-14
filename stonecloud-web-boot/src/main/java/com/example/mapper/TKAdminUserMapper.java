package com.example.mapper;

import org.springframework.stereotype.Repository;

import com.example.model.AdminUser;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@Repository
public interface TKAdminUserMapper extends Mapper<AdminUser>, MySqlMapper<AdminUser>{

	public Integer countAdminUser();
//	public AdminUser selectById(Long id);
	
}
