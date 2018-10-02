package com.infoasdp.auth;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.infoasdp.util.JsonUtil;
import com.infoasdp.util.UserException;

public class SessionPublisherImpl implements SessionPublisher{
	@Autowired
	HttpServletRequest request;
	public static final String USER_SESSION_KEY_ATTR = "userSession";
	public static final String TRANS_TOKEN_KEY_ATTR = "transactionToken";
	Logger log = LoggerFactory.getLogger(SessionPublisherImpl.class);
	
	@Override
	public AuthenticatedUser getLoginUser() throws Exception {
		HttpSession session = this.request.getSession();
		String userSession = (String) session.getAttribute("userSession");
		if (userSession != null && !userSession.equals("")) {
			try {
				return (AuthenticatedUser) JsonUtil.parseJson(userSession, AuthenticatedUser.class);
			} catch (Exception arg3) {
				this.log.error("Error when parsing session json", arg3);
				throw arg3;
			}
		} else {
			return null;
		}
	}

	@Override
	public String getSessionId() {
		HttpSession session = this.request.getSession();
		return session.getId();
	}

	@Override
	public boolean validateToken(String token) throws UserException {
		HttpSession session = this.request.getSession();
		String sessionToken = (String) session.getAttribute("transactionToken");
		if (sessionToken != null && !sessionToken.equals("")) {
			if (token != null && !token.equals("")) {
				if (!sessionToken.equals(token)) {
					return false;
				} else {
					session.removeAttribute("transactionToken");
					return true;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean validateCapthcha(String arg0) throws UserException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setLoginUser(AuthenticatedUser authUser) throws Exception {
		HttpSession session = this.request.getSession();
		ObjectWriter writer = JsonUtil.generateDefaultJsonWriter();

		try {
			String e = writer.writeValueAsString(authUser);
			session.setAttribute("userSession", e);
		} catch (Exception arg4) {
			this.log.error("Error when creating json session", arg4);
			throw arg4;
		}
	}

	@Override
	public void putSession(String key, String value) {
		HttpSession session = this.request.getSession();
		session.setAttribute(key, value);
	}

	@Override
	public void removeSession(String key) {
		HttpSession session = this.request.getSession();
		session.removeAttribute(key);
	}

	@Override
	public void clearSession() {
		HttpSession session = this.request.getSession();
		session.invalidate();
	}

	@Override
	public String generateToken() {
		String token = UUID.randomUUID().toString().replace("-", "");
		HttpSession session = this.request.getSession();
		session.setAttribute("transactionToken", token);
		return token;
	}

	@Override
	public byte[] generateCaptcha() throws IOException {
		throw new UnsupportedOperationException();
	}

}
