package com.example.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("admin_user")
@ApiModel(value = "用户表")
public class AdminUser implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "唯一标识", required = false)
	@TableId(type = IdType.AUTO)
	private Long id;							//唯一标识
	@ApiModelProperty(value = "用户名", required = false)
	private String userName;					//服务名称
	@ApiModelProperty(value = "密码", required = false)
	private String password;					//协议
	@ApiModelProperty(value = "服务器", required = false)
	@TableField(el = "applicationServer.id, jdbcType=BIGINT")
	private ApplicationServer applicationServer;//服务器
	@ApiModelProperty(value = "创建日期", required = false)
	private Date createDate;					//创建日期
	@ApiModelProperty(value = "更新日期", required = false)
	private Date updateDate;					//更新日期
	
}
