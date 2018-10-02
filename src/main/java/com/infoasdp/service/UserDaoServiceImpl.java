package com.infoasdp.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.infoasdp.login.entity.UserEntity;
import com.infoasdp.repository.UserRepository;
import com.infoasdp.util.CommonPaging;
import com.infoasdp.util.JsonUtil;
import com.infoasdp.util.StringFunction;
import com.infoasdp.util.SystemConstant;
import com.infoasdp.util.UserException;

public class UserDaoServiceImpl implements UserDaoService{
	
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public String searchUser(UserEntity request) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized String saveUser(UserEntity request) throws Exception {
		UserEntity toUpdate = request;
		if (isExistUserByUserId(request.getUserId(), request.getId())) {
			throw new UserException("ALREADY_EXISTS", "User with that User ID already exists !");
		}
		if (request == null || StringFunction.isEmpty(request.getUserId())) {
			throw new UserException("MANDATORY", "User ID is mandatory !");
		}
		if (StringFunction.isNotEmpty(request.getId())) {
			toUpdate = userRepo.findOne(request.getId());
			if (toUpdate == null) {
				throw new UserException("NOT_FOUND", "User not found !");
			}
			BeanUtils.copyProperties(request, toUpdate);
		}
		userRepo.save(toUpdate);
		
		//CommonResponse<String> response = comGen.generateCommonResponse(SystemConstant.SUCCESS);
		return JsonUtil.generateDefaultJsonWriter().writeValueAsString("SUCCESS");
	}
	
	private boolean isExistUserByUserId(String userId, String id) {
		CriteriaBuilder critBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Long> countQuery = critBuilder.createQuery(Long.class);
		countQuery.select(critBuilder.count(countQuery.from(UserEntity.class)));
		Root<UserEntity> root = countQuery.from(UserEntity.class);
		Predicate predicateValid = critBuilder.equal(root.get(UserEntity.Constant.VALID_FIELD),
				SystemConstant.ValidFlag.VALID);
		Predicate predicateUserId = critBuilder.equal(root.get(UserEntity.Constant.USER_ID_FIELD), userId);
		Predicate validAndUserId = critBuilder.and(predicateValid, predicateUserId);
		em.createQuery(countQuery);
		if (id != null) {
			Predicate predicateId = critBuilder.notEqual(root.get(UserEntity.Constant.ID_FIELD), id);
			countQuery.where(critBuilder.and(validAndUserId, predicateId));
		} else {
			countQuery.where(critBuilder.and(validAndUserId));
		}
		Long rowCount = em.createQuery(countQuery).getSingleResult();
		return (rowCount != null && rowCount > 0 ? true : false);
	}

	@Override
	public String findOneUser(UserEntity request) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteUser(UserEntity request) throws Exception {
		if (request == null || StringFunction.isEmpty(request.getId())) {
			throw new UserException("MANDATORY", "ID is mandatory !");
		}
		UserEntity user = userRepo.findOne(request.getId());
		if (user != null) {
			user.setValid(SystemConstant.ValidFlag.INVALID);
		}
		//CommonResponse<String> toSerialize = comGen.generateCommonResponse("DELETE_SUCCESS");
		ObjectWriter writer = JsonUtil.generateDefaultJsonWriter();
		return writer.writeValueAsString("Delete Success");
	}

	@Override
	public String updateUser(UserEntity request) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserEntity getUsersByName(String name) {
		return userRepo.findOneByUserId(name);
	}

	@Override
	public CommonPaging<UserEntity> getUserWithPaging(String search, String user, int page) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
