package com.infoasdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infoasdp.login.entity.UserRoleEntity;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, String> {

}
