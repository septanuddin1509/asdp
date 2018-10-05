package com.infoasdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.infoasdp.login.entity.UserEntity;
import com.infoasdp.service.UserDaoService;
import com.infoasdp.util.JsonUtil;
import com.infoasdp.util.SystemRestConstant;
import com.infoasdp.util.UserException;

@RestController
@RequestMapping(SystemRestConstant.UserConstant.USER_CONTROLLER)
public class UserController {
	@Autowired
	UserDaoService userService;
	
	@PostMapping(SystemRestConstant.UserConstant.SAVE_USER_ADDR)
	public String saveUser(@RequestBody UserEntity request) throws Exception {
		return userService.saveUser(request);
	}
	
	@PostMapping(SystemRestConstant.UserConstant.FIND_USER_DETAIL_ADDR)
	public String findUserDatail(@RequestBody UserEntity request) 
			throws Exception {
	
		UserEntity user = userService.findOneUser(request);
		if (user == null) {
			throw new UserException("NOT_FOUND", "User with id : " + request.getId() + " not found !");
		}
		
		ObjectWriter writter = JsonUtil.generateDefaultJsonWriter();
		return writter.writeValueAsString("Success");
	}
}
