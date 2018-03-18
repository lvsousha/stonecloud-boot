package com.example.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class AdminUser implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long uuid;							//唯一标识
	private String userName;					//服务名称
	private String password;					//协议
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
	@JoinColumn(name = "application_server_id")
	@ManyToOne
	public ApplicationServer getApplicationServer() {
		return applicationServer;
	}
	public void setApplicationServer(ApplicationServer applicationServer) {
		this.applicationServer = applicationServer;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
}
