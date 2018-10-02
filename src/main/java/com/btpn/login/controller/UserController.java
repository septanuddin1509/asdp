package com.btpn.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infoasdp.login.entity.UserEntity;
import com.infoasdp.service.UserDaoService;
import com.infoasdp.util.SystemRestConstant;

@RestController
@RequestMapping(SystemRestConstant.UserConstant.USER_CONTROLLER)
public class UserController {
	@Autowired
	UserDaoService userService;
	
	@PostMapping(SystemRestConstant.UserConstant.SAVE_USER_ADDR)
	public String saveUser(@RequestBody UserEntity request) throws Exception {
		return userService.saveUser(request);
	}
}
