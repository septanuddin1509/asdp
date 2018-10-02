package com.infoasdp.login.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFilter;


@Entity(name = "UserRole")
@Table(name = "sys_user_role")
@JsonFilter("jsonFilterUserRoleEntity")
public class UserRoleEntity extends AuditEntity implements Serializable{

	private static final long serialVersionUID = 886950508881845834L;
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String userCode;
	private String roleName;
	
	@OneToMany(mappedBy = UserEntity.Constant.USER_ROLE_FIELD)
	private Set<UserEntity> user;
	
	@OneToMany(mappedBy = "userRole")
	private Set<MenuRoleEntity> menuRole;
	
	public Set<MenuRoleEntity> getMenuRole() {
		return menuRole;
	}
	public void setMenuRole(Set<MenuRoleEntity> menuRole) {
		this.menuRole = menuRole;
	}
	public Set<UserEntity> getUser() {
		return user;
	}
	public void setUser(Set<UserEntity> user) {
		this.user = user;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}	
	public static class Constant {
		private Constant() {}
		public static final String USER_CODE_FIELD = "userCode";
	}
}
