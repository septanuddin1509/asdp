package com.infoasdp.login.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFilter;

@Entity(name = "User")
@Table(name = "sys_user")
@JsonFilter("jsonFilterUserEntity")
public class UserEntity extends AuditEntity implements Serializable{

	private static final long serialVersionUID = 2371860388537337695L;
	@Id
	@GeneratedValue(generator = "code-uuid")
	@GenericGenerator(name = "code-uuid", strategy = "uuid")
	private String id;
	private String userId;
	private String name;
	private String noHp;
	private String alamat;
	private String jabatan;
	private String divisi;
	private Date expiredDate;
	private Integer valid = 1;
	private String password;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_role")
	private UserRoleEntity userRole;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UserRoleEntity getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRoleEntity userRole) {
		this.userRole = userRole;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public static class Constant {
		private Constant() {}
		public static final String ID_FIELD = "id";
		public static final String USER_ID_FIELD = "userId";
		public static final String NAME_FIELD = "name";
		public static final String USER_FIELD = "user";
		public static final String USER_ROLE_FIELD = "userRole";
		public static final String VALID_FIELD = "valid";
		public static final String JSON_FILTER = "jsonFilterUser";
	}
}
