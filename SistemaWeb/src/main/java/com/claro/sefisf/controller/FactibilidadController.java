package com.claro.sefisf.controller;



import java.util.List;

import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.claro.sefisf.entity.Servicio;
import com.claro.sefisf.entity.TipDoc;
import com.claro.sefisf.model.InstalacionesResponse;
import com.claro.sefisf.model.Searcher;
import com.claro.sefisf.service.FactibilidadService;
import com.claro.sefisf.service.SefisfService;


@Controller
public class FactibilidadController {
	
	@Autowired
	FactibilidadService factibilidadSerice;
	@Autowired
	SefisfService sefisfservice;
	private final static Logger logger = LoggerFactory.getLogger(FactibilidadController.class);
	
	@GetMapping("/factibilidad")
	public String getIndex(Model model) {
		model.addAttribute("searcher",new Searcher());
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
	
	@PostMapping("/sefisf")
	public String getFormulario(@ModelAttribute Searcher searcher, Model model) {
		logger.info("Dentro del Controller SefisfController");
		
		logger.info("Direccion "+ searcher.getDireccion() );
		List<TipDoc> tiposDocumento;
		List<Servicio> servicios;
		try {
			tiposDocumento = sefisfservice.listarTiposDocumento() ;
			servicios = sefisfservice.listarServicios();
			model.addAttribute("tiposDocumento", tiposDocumento);
			model.addAttribute("servicios", servicios);
			model.addAttribute("direccion", searcher.getDireccion());
		} catch (Exception e) {
			logger.error(e.getStackTrace().toString());
		}
	
		return "sefisf";
	}
	
}
