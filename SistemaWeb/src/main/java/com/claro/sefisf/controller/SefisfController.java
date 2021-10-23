package com.claro.sefisf.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.claro.sefisf.entity.SolicitudInstalacion;

import com.claro.sefisf.model.Searcher;
import com.claro.sefisf.service.SefisfService;

@Controller
public class SefisfController {
	
	@Autowired
	SefisfService sefisfservice;
	private final static Logger logger = LoggerFactory.getLogger(SefisfController.class);
	

	
	@GetMapping("/registrar")
	@ResponseBody
	public int registrarSolicitudInstalacion(int idServicio,String nombre, String apellido,int idTipDoc, String numDoc,String direccion,Model model) {
		logger.info("Dentro del metodo registrarSolicitudInstalacion");
		SolicitudInstalacion bean = new SolicitudInstalacion();
		int codigoRespuesta = 0;
		bean.setIdServicio(idServicio);
		bean.setNombre(nombre);
		bean.setApellido(apellido);
		bean.setIdTipDoc(idTipDoc);
		bean.setNumdoc(numDoc);
		bean.setDireccion(direccion);
		
		try {
			sefisfservice.registrarSolicitud(bean);
		} catch (Exception e) {
			codigoRespuesta=1;
			logger.error("Caida ", e);
		}
		return codigoRespuesta;
	}
	
	@PostMapping("/retorno")
	public String getBack(Model model) {
		model.addAttribute("searcher",new Searcher());
		return "factibilidad";
	}

	
}
