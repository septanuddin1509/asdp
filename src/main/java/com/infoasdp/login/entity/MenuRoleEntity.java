package com.infoasdp.login.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFilter;

@Entity(name = "MenuRole")
@Table(name = "sys_menu_role")
@JsonFilter("jsonFilterMenuRoleEntity")
public class MenuRoleEntity  extends AuditEntity implements Serializable{

	private static final long serialVersionUID = 266610560287912683L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="user")
	private Set<UserRoleEntity> userRole;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="menu")
	private Set<MenuEntity> menu;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Set<UserRoleEntity> getUserRole() {
		return userRole;
	}
	public void setUserRole(Set<UserRoleEntity> userRole) {
		this.userRole = userRole;
	}
	public Set<MenuEntity> getMenu() {
		return menu;
	}
	public void setMenu(Set<MenuEntity> menu) {
		this.menu = menu;
	}
	public static class Constant {
		private Constant() {}
		public static final String USER_ROLE_FIELD = "userRole";
	}
	

}
