package com.claro.sefisf.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.sefisf.dao.UbicacionDAO;
import com.claro.sefisf.entity.Punto;
import com.claro.sefisf.entity.Ubicacion;
import com.claro.sefisf.util.Constantes;

@Service
public class InstalacionesServiceImpl implements InstalacionesService{

	@Autowired
	private UbicacionDAO ubicacionDAO;
	private final static Logger logger = LoggerFactory.getLogger(InstalacionesServiceImpl.class);

	public List<Ubicacion> listarUbicaciones(double x, double y, String distrito, String ciudad) throws Exception {	
		logger.info("Entrada al método listarUbicaciones()");		
		Iterable<Ubicacion> iter=ubicacionDAO.listarPorCiudadDistritro(distrito, ciudad);
		logger.info(iter.toString());
	    List<Ubicacion> result = new ArrayList<Ubicacion>();
	    iter.forEach(result::add);
	    logger.info("Cantidad de ubicaciones encontradas "+ result.size());
	    logger.info("Calculando proximidad de puntos");
	    double direccionX = x;
	    double direccionY = y;
	    Punto puntoDireccion = new Punto(direccionX, direccionY);
	    for(Ubicacion u:result) {
	    	logger.info("Cantidad de ubicaciones encontradas "+ result.size());
	    	double listaX = Double.parseDouble(u.getX());
	    	double listaY = Double.parseDouble(u.getY());
	    	Punto puntoLista = new Punto(listaX, listaY) ;
	    	double distancia = 0;
	    	distancia = puntoDireccion.calcularDistanciaDesde(puntoLista);
	    	if(distancia > Constantes.DISTANCIA_STANDAR ) {
	    		logger.info("ubicacion fuera del rango  "+ u);
	    		result.remove(u);
	    	}
	    }
		return result;
		
	}

}
