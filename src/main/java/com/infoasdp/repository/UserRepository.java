package com.infoasdp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.infoasdp.login.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String>{
	UserEntity findOneByUserId(String userId);
	
	Page<UserEntity> findByUserIdNotAndUserIdContaining(String user, String UserId, Pageable page);
}
