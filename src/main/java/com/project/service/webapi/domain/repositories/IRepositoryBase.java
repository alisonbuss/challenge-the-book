package com.project.service.webapi.domain.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IRepositoryBase<Entity, ID extends Serializable> extends JpaRepository<Entity, ID> {


}
