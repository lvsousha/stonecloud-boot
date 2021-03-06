package com.example.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class ApplicationServer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6116014887216258423L;

	private String uuid;							//唯一标识
	private String serverName;					//服务名称
	private String protocol;					//协议
	private String ip;							//IP
	private String port;						//端口号
	private String context;						//应用名
	private String tokeKey;						//生成token的密钥
	private Date createDate;					//创建日期
	private Date updateDate;					//更新日期
	
	private List<AdminUser> adminUsers;
	
	@Id  
    @GenericGenerator(name="idGenerator", strategy="uuid") //这个是hibernate的注解/生成32位UUID  
    @GeneratedValue(generator="idGenerator")  
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
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
	@CreationTimestamp
	@Column(updatable = false)
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@UpdateTimestamp
	@Column(insertable = true)
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getTokeKey() {
		return tokeKey;
	}
	public void setTokeKey(String tokeKey) {
		this.tokeKey = tokeKey;
	}
	@OneToMany(mappedBy = "applicationServer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<AdminUser> getAdminUsers() {
		return adminUsers;
	}
	public void setAdminUsers(List<AdminUser> adminUsers) {
		this.adminUsers = adminUsers;
	}
	
}
