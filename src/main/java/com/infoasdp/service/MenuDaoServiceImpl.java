package com.infoasdp.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.infoasdp.login.entity.MenuEntity;
import com.infoasdp.login.entity.MenuRoleEntity;
import com.infoasdp.login.entity.UserRoleEntity;
import com.infoasdp.repository.MenuRepository;
import com.infoasdp.util.CommonPageUtil;
import com.infoasdp.util.CommonPaging;

public class MenuDaoServiceImpl implements MenuDaoService{

	@PersistenceContext
	EntityManager em;
	
	@Autowired
	MenuRepository repo;
	
	@Autowired
	CommonPageUtil pageUtil;
	
	@Override
	public List<MenuEntity> getMenuByUser(String userRole) {
		CriteriaBuilder critBuilder = em.getCriteriaBuilder();

		CriteriaQuery<MenuEntity> query = critBuilder.createQuery(MenuEntity.class);
		Root<MenuEntity> root = query.from(MenuEntity.class);
		Join<MenuEntity, MenuRoleEntity> joinRM = root.join(MenuEntity.Constant.MENU_ROLES_FIELD);
		Join<UserRoleEntity, MenuRoleEntity> joinUM = joinRM.join(MenuRoleEntity.Constant.USER_ROLE_FIELD);

		List<Predicate> lstWhere = new ArrayList<Predicate>();
		lstWhere.add(critBuilder.equal(joinUM.get(UserRoleEntity.Constant.USER_CODE_FIELD), userRole));

		query.select(root)
				.where(lstWhere.toArray(new Predicate[] {}));

		return em.createQuery(query).getResultList();
	}

	@Override
	public List<MenuEntity> findAllMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> findAllMenuCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonPaging<MenuEntity> searchMenu(String menuCode, String description, int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MenuEntity saveMenu(MenuEntity menu) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MenuEntity findOne(String menuCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteMenu(MenuEntity menu) {
		// TODO Auto-generated method stub
		
	}

}
