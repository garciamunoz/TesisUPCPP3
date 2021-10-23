package com.claro.sefisf.service;

import java.util.List;

import com.claro.sefisf.entity.Servicio;
import com.claro.sefisf.entity.SolicitudInstalacion;
import com.claro.sefisf.entity.TipDoc;

public interface SefisfService {
	
	public List<TipDoc>  listarTiposDocumento() throws Exception;
	public List<Servicio> listarServicios() throws Exception;
	public void registrarSolicitud(SolicitudInstalacion bean)throws Exception;
}
