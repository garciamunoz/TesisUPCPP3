package com.claro.sefisf.service;


import com.claro.sefisf.model.InstalacionesResponse;

public interface FactibilidadService {

	public InstalacionesResponse getInstalaciones(String direccion) throws Exception;
}
