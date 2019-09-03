package com.stone.mapper;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stone.model.AdminUser;

@Repository
public interface AdminUserMapper extends BaseMapper<AdminUser>{

	public Integer countAdminUser();
//	public AdminUser selectById(Long id);
	
}
