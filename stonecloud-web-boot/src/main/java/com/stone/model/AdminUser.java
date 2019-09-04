package com.stone.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

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
	@Column(name = "id")
	private Long id;	
	
	
	@ApiModelProperty(value = "用户名", required = false)
	private String userName;	
	
	
	@ApiModelProperty(value = "密码", required = false)
	private String password;	
	
	
	@ApiModelProperty(value = "服务器", required = false)
//	@TableField(el = "applicationServer.id, jdbcType=BIGINT")
	private Long applicationServer;
//	private ApplicationServer applicationServer;//服务器
	
	
	@ApiModelProperty(value = "创建日期", required = false)
	private Date createDate;
	
	
	@ApiModelProperty(value = "更新日期", required = false)
	private Date updateDate;	
	
	
	public static AdminUser build() {
	  AdminUser model = new AdminUser();
	  return model;
	}
	
	public AdminUser buildTest() {
	  this.setUserName("test");
	  return this;
	}
}
