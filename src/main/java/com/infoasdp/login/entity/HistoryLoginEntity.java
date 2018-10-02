package com.infoasdp.login.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFilter;

@Entity(name = "HistoryLogin")
@Table(name = "sys_history_login")
@JsonFilter("jsonFilterHistoryLoginEntity")
public class HistoryLoginEntity extends AuditEntity implements Serializable{

	private static final long serialVersionUID = 886950508881845835L;
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	@ManyToOne(fetch = FetchType.LAZY)
	private UserEntity user;
	private Date dateLogin;
	private String ipAddress;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	public Date getDateLogin() {
		return dateLogin;
	}
	public void setDateLogin(Date dateLogin) {
		this.dateLogin = dateLogin;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
}
