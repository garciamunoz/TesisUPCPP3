package com.claro.sefisf.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.claro.sefisf.entity.TipDoc;

@Component
public interface TipDocDao extends CrudRepository<TipDoc, Integer>  {

}
