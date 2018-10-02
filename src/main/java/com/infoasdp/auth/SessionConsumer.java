package com.infoasdp.auth;

import com.infoasdp.util.UserException;

public interface SessionConsumer {
	AuthenticatedUser getLoginUser() throws Exception;

	String getSessionId();

	boolean validateToken(String arg0) throws UserException;

	boolean validateCapthcha(String arg0) throws UserException;
}
