package com.example.mapper;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.model.AdminUser;

@Repository
public interface AdminUserMapper extends BaseMapper<AdminUser>{

	public Integer countAdminUser();
	
}
