package com.claro.sefisf.controller;



import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.claro.sefisf.model.InstalacionesResponse;
import com.claro.sefisf.service.FactibilidadService;


@Controller
public class FactibilidadController {
	
	@Autowired
	FactibilidadService factibilidadSerice;
	private final static Logger logger = LoggerFactory.getLogger(FactibilidadController.class);
	
	@GetMapping("/factibilidad")
	public String getIndex(Model model) {
		return "factibilidad";
	}
	
	@RequestMapping(value = "/evaluarFactibilidad", method = RequestMethod.GET)
	@ResponseBody
	public InstalacionesResponse evaluarFactibilidad(String direccion,HttpSession sesion) {
		logger.info("Entrada al método evaluarFactibilidad()");		
		logger.info("direccion " + direccion);
		InstalacionesResponse instalacionesResponse= null;
		try {
			instalacionesResponse = factibilidadSerice.getInstalaciones(direccion);
			logger.debug(instalacionesResponse.toString());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return instalacionesResponse;
	}	
	
}
