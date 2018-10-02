package com.infoasdp.service;

import com.infoasdp.login.entity.UserEntity;
import com.infoasdp.util.CommonPaging;

public interface UserDaoService {
	String saveUser(UserEntity request) throws Exception;
	UserEntity findOneUser(UserEntity request) throws Exception;
	String deleteUser(UserEntity request) throws Exception;
	UserEntity getUsersByName(String name);
	CommonPaging<UserEntity> getUserWithPaging(String search, String user, int page);
}
