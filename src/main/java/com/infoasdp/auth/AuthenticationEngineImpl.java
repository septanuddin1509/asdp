package com.infoasdp.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.infoasdp.login.entity.LoginConstant;
import com.infoasdp.login.entity.MenuEntity;
import com.infoasdp.login.entity.UserEntity;
import com.infoasdp.service.MenuDaoService;
import com.infoasdp.service.UserDaoServiceImpl;
import com.infoasdp.util.UserException;

public class AuthenticationEngineImpl implements AuthenticationEngine{

	@Autowired
	UserDaoServiceImpl userService;

	@Autowired
	MenuDaoService menuService;

	@Override
	public AuthenticatedUser authenticate(String username, String password) throws Exception {
		AuthenticatedUser usr = new AuthenticatedUser();
		UserEntity usrDB = userService.getUsersByName(username);
		if(usrDB==null || usrDB.getUserId()==null || usrDB.getUserId().equals("")) {
			throw new UserException(LoginConstant.USER_DB_NOT_FOUND_ERROR_CODE, LoginConstant.USER_DB_NOT_FOUND_ERROR_DESC);
		}
		String salt = LoginConstant.PASSWORD_SALT_KEY;
		String hashPassword = PasswordUtil.getPasswordHash(password, salt);
		if(!usrDB.getPassword().equals(hashPassword)) {
			/*System.out.println("SALT : " + salt);
			System.out.println("PASSWORDNYE : " + password);
			System.out.println(usrDB.getPassword() + "====" + hashPassword);*/
			throw new UserException(LoginConstant.INVALID_PASSWORD_DB_ERROR_CODE, LoginConstant.INVALID_PASSWORD_DB_ERROR_DESC);
		}
		usr.setUserId(usrDB.getUserId());
		usr.setName(usrDB.getName());

		List<MenuEntity> lstMenu= menuService.getMenuByUser(usrDB.getUserRole().getUserCode());
		List<AuthenticatedMenu> menus = new ArrayList<AuthenticatedMenu>();
		lstMenu.forEach((menu) -> menus.add(new AuthenticatedMenu(menu.getMenuCode(), menu.getMenuName(), menu.getUrl(), menu.getParent())));
		usr.setMenus(menus);

		return usr;
	}

}
