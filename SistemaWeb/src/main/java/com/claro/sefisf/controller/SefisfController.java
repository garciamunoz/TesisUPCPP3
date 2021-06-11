package com.claro.sefisf.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.claro.sefisf.entity.Servicio;
import com.claro.sefisf.entity.TipDoc;
import com.claro.sefisf.service.SefisfService;

@Controller
public class SefisfController {
	
	@Autowired
	SefisfService sefisfservice;
	private final static Logger logger = LoggerFactory.getLogger(SefisfController.class);
	
	@GetMapping("/sefisf")
	public String getFormulario(String direccion,Model model) {
		logger.info("Dentro del Controller SefisfController");
		logger.info("Direccion "+direccion );
		List<TipDoc> tiposDocumento;
		List<Servicio> servicios;
		try {
			tiposDocumento = sefisfservice.listarTiposDocumento() ;
			servicios = sefisfservice.listarServicios();
			model.addAttribute("tiposDocumento", tiposDocumento);
			model.addAttribute("servicios", servicios);
		} catch (Exception e) {
			logger.error(e.getStackTrace().toString());
		}
	
		return "sefisf";
	}

	
}
