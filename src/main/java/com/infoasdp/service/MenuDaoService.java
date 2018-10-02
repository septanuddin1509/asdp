package com.infoasdp.service;

import java.util.List;

import com.infoasdp.login.entity.MenuEntity;
import com.infoasdp.util.CommonPaging;

public interface MenuDaoService {
	List<MenuEntity> getMenuByUser(String userName);
	
	List<MenuEntity> findAllMenu();
	
	List<String> findAllMenuCode();
	
	CommonPaging<MenuEntity> searchMenu(String menuCode, String description, int page);
	
	MenuEntity saveMenu(MenuEntity menu);
	
	MenuEntity findOne(String menuCode);
	
	void deleteMenu(MenuEntity menu);
}
