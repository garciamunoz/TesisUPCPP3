package com.claro.sefisf.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.sefisf.dao.ServicioDao;
import com.claro.sefisf.dao.TipDocDao;
import com.claro.sefisf.entity.Servicio;
import com.claro.sefisf.entity.TipDoc;

@Service
public class SefisfServiceImpl implements SefisfService{

	@Autowired
	private TipDocDao tipDocDao;
	@Autowired
	private ServicioDao servicioDao;
	
	public List<TipDoc> listarTiposDocumento() throws Exception {
		
		Iterable<TipDoc> iter=tipDocDao.findAll();
	    List<TipDoc> result = new ArrayList<TipDoc>();
	    iter.forEach(result::add);
		return result;
	}


	
	public List<Servicio> listarServicios() throws Exception {
		Iterable<Servicio> iter=servicioDao.findAll();
		List<Servicio> result = new ArrayList<Servicio>();
		iter.forEach(result::add);
		return result;
	}
	


}
