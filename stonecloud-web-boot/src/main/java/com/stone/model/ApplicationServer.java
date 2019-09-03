package com.stone.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
@TableName("application_server")
public class ApplicationServer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6116014887216258423L;
	@TableId(type = IdType.AUTO)
	private Long id;							//唯一标识
	private String serverName;					//服务名称
	private String protocol;					//协议
	private String ip;							//IP
	private String port;						//端口号
	private String context;						//应用名
	private String tokenKey;						//生成token的密钥
	private Date createDate;					//创建日期
	private Date updateDate;					//更新日期
	
	@TableField(exist=false)
	private List<AdminUser> adminUsers;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
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
	public String getTokeKey() {
		return tokenKey;
	}
	public void setTokeKey(String tokenKey) {
		this.tokenKey = tokenKey;
	}
	public List<AdminUser> getAdminUsers() {
		return adminUsers;
	}
	public void setAdminUsers(List<AdminUser> adminUsers) {
		this.adminUsers = adminUsers;
	}
	
}
