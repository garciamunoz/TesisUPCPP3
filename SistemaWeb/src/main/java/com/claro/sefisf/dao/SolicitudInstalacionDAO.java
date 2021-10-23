package com.claro.sefisf.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.claro.sefisf.entity.SolicitudInstalacion;

@Component
public interface SolicitudInstalacionDAO extends CrudRepository<SolicitudInstalacion, Integer> {

}
