package com.infoasdp.login.request;

public class LoginRequest {
	String userId;
	String password;

	public String getPassword() {
		return password;
	}

	public String getUserId() {
		return userId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
