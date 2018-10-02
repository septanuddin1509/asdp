package com.infoasdp.login.entity;

public class LoginConstant {

	public static final String USER_DB_NOT_FOUND_ERROR_CODE = "L05";
	public static final String USER_DB_NOT_FOUND_ERROR_DESC = "User Not Found";
	public static final String INVALID_PASSWORD_DB_ERROR_CODE = "L05";
	public static final String INVALID_PASSWORD_DB_ERROR_DESC = "Invalid Password";
	public static final String INVALID_LOGIN_LDAP_ERROR_CODE = "L06";
	public static final String INVALID_LOGIN_LDAP_ERROR_DESC = "Invalid LDAP Login";
	public static final String EMPTY_ROLE_NAME_ERROR_CODE = "L07";
	public static final String EMPTY_ROLE_NAME_ERROR_DESC = "Role Name must not empty";
	public static final String LOGIN_METHOD_UNSET_ERROR_CODE = "L08";
	public static final String LOGIN_METHOD_UNSET_ERROR_DESC = "Login method not set";
	public static final String LOGIN_METHOD_UNSUPPORTED_ERROR_CODE = "L09";
	public static final String LOGIN_METHOD_UNSUPPORTED_ERROR_DESC = "Login method unsupported";
	public static final String NOT_DB_USER_ERROR_CODE = "L10";
	public static final String NOT_DB_USER_ERROR_DESC = "Change password only allowed for DB user";
	public static final String INVALID_CURRENT_PASSWORD_ERROR_CODE = "L11";
	public static final String INVALID_CURRENT_PASSWORD_ERROR_DESC = "Invalid current password";
	public static final String AUTHENTICATION_ENGINE_KEY = "auth.engine";
	public static final String AUTHENTICATION_ENGINE_DB = "DB";
	public static final String PASSWORD_SALT_KEY = "saltpass";
}
