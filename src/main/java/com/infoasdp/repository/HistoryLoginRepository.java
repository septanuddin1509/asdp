package com.infoasdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infoasdp.login.entity.HistoryLoginEntity;

public interface HistoryLoginRepository extends JpaRepository<HistoryLoginEntity, String>{

}
