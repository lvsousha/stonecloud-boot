package com.example.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class AdminUser implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String uuid;							//唯一标识
	private String userName;					//服务名称
	private String password;					//协议
	private ApplicationServer applicationServer;//服务器
	private Date createDate;					//创建日期
	private Date updateDate;					//更新日期
	
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
	@JoinColumn(name = "application_server_id")
	@ManyToOne
	public ApplicationServer getApplicationServer() {
		return applicationServer;
	}
	public void setApplicationServer(ApplicationServer applicationServer) {
		this.applicationServer = applicationServer;
	}
	@Id  
    @GenericGenerator(name="idGenerator", strategy="uuid") //这个是hibernate的注解/生成32位UUID  
    @GeneratedValue(generator="idGenerator")  
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
