package com.infoasdp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.infoasdp.login.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String>{
	UserEntity findOneByUsername(String username);
	
	Page<UserEntity> findByUsernameNotAndUsernameContaining(String user, String username, Pageable page);
}
