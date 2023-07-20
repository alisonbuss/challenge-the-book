package com.project.service.webapi.domain.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IServiceBase<Entity> {

    List<Entity> findAll();

    Page<Entity> findAllPageable(Pageable pageable);

}
