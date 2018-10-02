package com.infoasdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infoasdp.login.entity.MenuEntity;

public interface MenuRepository extends JpaRepository<MenuEntity, String> {

}
