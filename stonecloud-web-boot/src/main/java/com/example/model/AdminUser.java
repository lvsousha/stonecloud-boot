package com.example.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("admin_user")
public class AdminUser implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.AUTO)
	private Long id;							//唯一标识
	private String userName;					//服务名称
	private String password;					//协议
	@TableField(el = "applicationServer.id, jdbcType=BIGINT")
	private ApplicationServer applicationServer;//服务器
	private Date createDate;					//创建日期
	private Date updateDate;					//更新日期
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ApplicationServer getApplicationServer() {
		return applicationServer;
	}
	public void setApplicationServer(ApplicationServer applicationServer) {
		this.applicationServer = applicationServer;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
