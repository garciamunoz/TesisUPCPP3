package com.claro.sefisf.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.claro.sefisf.entity.Servicio;

@Component
public interface ServicioDao extends CrudRepository<Servicio, Integer> {

}
