package com.infoasdp.auth;

public interface AuthenticationEngine {

	AuthenticatedUser authenticate(String username, String password) throws Exception;
}
