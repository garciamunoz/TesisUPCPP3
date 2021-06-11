package com.claro.sefisf.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.claro.sefisf.entity.Ubicacion;
import com.claro.sefisf.model.InstalacionesResponse;
import com.claro.sefisf.service.InstalacionesService;
import com.claro.sefisf.util.Constantes;

@RestController
public class InstalacionesAPIController {
	
	@Autowired
	private InstalacionesService  instalacionesService;
	private final static Logger logger = LoggerFactory.getLogger(InstalacionesAPIController.class);
	
	@RequestMapping(path = "/instalaciones", method = RequestMethod.GET )
	public InstalacionesResponse getInstalaciones(@RequestParam(value = "x", defaultValue = "0") double x,
												  @RequestParam(value = "y", defaultValue = "0")double y,
												  @RequestParam(value = "distrito", defaultValue = "0") String distrito,
												  @RequestParam(value = "ciudad", defaultValue = "0")String ciudad) {
		InstalacionesResponse response = new InstalacionesResponse();
		logger.info("Entrada al metodo getInstalaciones()");
		logger.info("Valor del parámetro de entrada X " + x);
		logger.info("Valor del parámetro de entrada y " + y);
		try {
			List<Ubicacion> lista = instalacionesService.listarUbicaciones(x, y, distrito, ciudad);
			response.setCodigoResp(Constantes.CODIGO_SIN_DATOS);
			response.setMsgResp(Constantes.MENSAJE_SIN_DATOS);
			response.setListaUbicacion(lista);
			if(lista.size() > Constantes.LISTA_VACIA) {
				response.setCodigoResp(Constantes.CODIGO_EXITO);
				response.setMsgResp(Constantes.MENSAJE_EXITO);
				response.setListaUbicacion(lista);	
			}
		} catch (Exception e) {
			logger.error(" "+ e.getStackTrace());
			response.setCodigoResp(Constantes.CODIGO_ERROR_TECNICO);
			response.setMsgResp(e.getMessage());
		}

		
		return response;
	}

}
