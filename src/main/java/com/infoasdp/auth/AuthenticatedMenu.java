package com.infoasdp.auth;

public class AuthenticatedMenu {
	private String menuCode;
	private String menuName;
	private String url;
	private String parent;
	
	public AuthenticatedMenu() {
		
	}

	public AuthenticatedMenu(String menuCode, String menuName, String url, String parent) {
		this.menuCode = menuCode;
		this.menuName = menuName;
		this.url = url;
		this.parent = parent;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}
	
}
