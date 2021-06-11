package com.claro.sefisf.model;

import java.util.List;

import com.claro.sefisf.entity.Ubicacion;

public class InstalacionesResponse {

	private String codigoResp;
	private String msgResp;
	private List<Ubicacion> listaUbicacion;
	
	public String getCodigoResp() {
		return codigoResp;
	}
	public void setCodigoResp(String codigoResp) {
		this.codigoResp = codigoResp;
	}
	public String getMsgResp() {
		return msgResp;
	}
	public void setMsgResp(String msgResp) {
		this.msgResp = msgResp;
	}
	public List<Ubicacion> getListaUbicacion() {
		return listaUbicacion;
	}
	public void setListaUbicacion(List<Ubicacion> listaUbicacion) {
		this.listaUbicacion = listaUbicacion;
	}
	
	
}
