package com.infoasdp.auth;

import java.io.IOException;

public interface SessionPublisher extends SessionConsumer{
	void setLoginUser(AuthenticatedUser arg0) throws Exception;

	void putSession(String arg0, String arg1);

	void removeSession(String arg0);

	void clearSession();

	String generateToken();

	byte[] generateCaptcha() throws IOException;
}
