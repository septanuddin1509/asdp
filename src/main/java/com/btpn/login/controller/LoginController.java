package com.btpn.login.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.infoasdp.auth.AuthenticatedUser;
import com.infoasdp.auth.AuthenticationEngine;
import com.infoasdp.auth.SessionPublisher;
import com.infoasdp.login.entity.LoginConstant;
import com.infoasdp.login.entity.UserEntity;
import com.infoasdp.login.request.LoginRequest;
import com.infoasdp.service.UserDaoService;
import com.infoasdp.util.CommonResponse;
import com.infoasdp.util.CommonResponseGenerator;
import com.infoasdp.util.JsonUtil;
import com.infoasdp.util.UserException;

@RestController
public class LoginController {

	public static final String LOGIN_ADDR = "login";
	public static final String LOGOUT_ADDR = "logout";
	public static final String WHO_AM_I_ADDR = "whoAmI";
	
	@Autowired
	SessionPublisher sessionPublisher;

	@Autowired
	UserDaoService userService;

	@Autowired
	ApplicationContext context;

	@Autowired
	CommonResponseGenerator comGen;

	Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value = LOGIN_ADDR, method = RequestMethod.POST)
	public String loginStandard(HttpServletRequest request, HttpServletResponse response,
			@RequestBody LoginRequest loginRequest) throws Exception {
		String userId = loginRequest.getUserId();
		String password = loginRequest.getPassword();

		UserEntity usrDB = userService.getUsersByName(userId);
		if (usrDB == null || usrDB.getUserId() == null || usrDB.getUserId().equals(""))
			throw new UserException(LoginConstant.USER_DB_NOT_FOUND_ERROR_CODE, LoginConstant.USER_DB_NOT_FOUND_ERROR_DESC);

		AuthenticationEngine stdAuthEngine = (AuthenticationEngine) context.getBean("AuthenticationEngineDBImpl");
		if (stdAuthEngine == null)
			throw new UserException(LoginConstant.LOGIN_METHOD_UNSUPPORTED_ERROR_CODE, LoginConstant.LOGIN_METHOD_UNSUPPORTED_ERROR_DESC);

		AuthenticatedUser usr = stdAuthEngine.authenticate(userId, password);
		sessionPublisher.setLoginUser(usr);
		CommonResponse<AuthenticatedUser> restResponse = comGen.generateCommonResponse(usr);
		return JsonUtil.generateJson(restResponse);
	}
	
	@RequestMapping(LOGOUT_ADDR)
	public String logout(HttpServletResponse response, @RequestParam(name = "redirectURL", defaultValue = "") String redirectURL) 
			throws JsonProcessingException {
		sessionPublisher.clearSession();
		CommonResponse<Void> restResponse = comGen.generateCommonResponse(Void.class);

		if (redirectURL != null && !redirectURL.equals("")) {
			try {
				response.sendRedirect(redirectURL);
			} catch (IOException e) {
				logger.error("Failed to redirect response", e);
			}
		}

		return JsonUtil.generateJson(restResponse);
	}

	@RequestMapping(WHO_AM_I_ADDR)
	public String whoAmI(HttpServletRequest request) throws Exception {
		AuthenticatedUser usr = sessionPublisher.getLoginUser();
		CommonResponse<AuthenticatedUser> restResponse = comGen.generateCommonResponse(usr);
		return JsonUtil.generateJson(restResponse);
	}
}
