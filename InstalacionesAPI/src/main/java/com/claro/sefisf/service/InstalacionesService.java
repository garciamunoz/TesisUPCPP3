package com.claro.sefisf.service;

import java.util.List;

import com.claro.sefisf.entity.Ubicacion;

public interface InstalacionesService {
	
	public List<Ubicacion> listarUbicaciones(double x, double y, String distrito, String ciudad) throws Exception;

}
