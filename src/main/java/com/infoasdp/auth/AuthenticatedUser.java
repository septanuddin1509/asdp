package com.infoasdp.auth;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("jsonFilterAuthenticatedUser")
public class AuthenticatedUser {
	private String userId;
	private String name;
	private String noHp;
	private String alamat;
	private String jabatan;
	private String divisi;
	private Date expiredDate;
	private String sessionId;
	List<AuthenticatedMenu> menus;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNoHp() {
		return noHp;
	}
	public void setNoHp(String noHp) {
		this.noHp = noHp;
	}
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	public String getJabatan() {
		return jabatan;
	}
	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
	}
	public String getDivisi() {
		return divisi;
	}
	public void setDivisi(String divisi) {
		this.divisi = divisi;
	}
	public Date getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public List<AuthenticatedMenu> getMenus() {
		return menus;
	}
	public void setMenus(List<AuthenticatedMenu> menus) {
		this.menus = menus;
	}
}
