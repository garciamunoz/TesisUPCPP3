package com.claro.sefisf.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.claro.sefisf.config.SistemaWebConfig;
import com.claro.sefisf.model.GeocodeResult;
import com.claro.sefisf.model.InstalacionesResponse;

@Service
public class FactibilidadServiceImpl implements FactibilidadService {

	@Autowired
	RestTemplate restTemplate;
	@Autowired 
	SistemaWebConfig config;
	private final static Logger logger = LoggerFactory.getLogger(FactibilidadServiceImpl.class);
	
	private GeocodeResult getUbicacion(String address) throws Exception {
		String urlDireccion = config.urlDirecciones + "?address={address}";
		logger.info("entrada al método getUbicacion");
		logger.info("url de direcciones " +urlDireccion);
		logger.info("direccion en el service " +address);
		Map<String, String> params = new HashMap<String, String>();
	    params.put("address", address);
	    
	    
	    GeocodeResult result = restTemplate.getForObject(urlDireccion, GeocodeResult.class, params);
	    logger.debug(result.toString());
		return result;
	}

	
	public InstalacionesResponse getInstalaciones(String direccion) throws Exception {
		logger.info("entrada al método getInstalaciones");
		GeocodeResult result = getUbicacion(direccion);
		logger.debug(result.toString());
		String urlInstalaciones = config.urlInstalaciones+ "?x={x}&y={y}&distrito={distrito}&ciudad={ciudad}";
		String x = result.getResults().get(0).getGeometry().getGeocodeLocation().getLatitude();
		String y = result.getResults().get(0).getGeometry().getGeocodeLocation().getLongitude();
		String distrito =  getDistrito(result);
		String ciudad = getCiudad(result);
		
		
		Map<String, String> params = new HashMap<String, String>();
	    params.put("x", x);
	    params.put("y", y);
	    params.put("distrito", distrito);
	    params.put("ciudad", ciudad);
	    
	    InstalacionesResponse response = restTemplate.getForObject(urlInstalaciones, InstalacionesResponse.class, params);
		return response;
	}
	
	private String getDistrito(GeocodeResult result) {
		for (int i = 0; i <= result.getResults().get(0).getAddressComponents().size(); i ++) {
			for(String s:result.getResults().get(0).getAddressComponents().get(i).getTypes()) {
					if(s.equals("locality"))
						return  result.getResults().get(0).getAddressComponents().get(i).getLongName();
			}
		}
		return "NOT_FOUND";    
	}
	
	private String getCiudad(GeocodeResult result) {
		for (int i = 0; i <= result.getResults().get(0).getAddressComponents().size(); i ++) {
			for(String s:result.getResults().get(0).getAddressComponents().get(i).getTypes()) {
					if(s.equals("administrative_area_level_2"))
						return  result.getResults().get(0).getAddressComponents().get(i).getLongName();
			}
		}
		return "NOT_FOUND";  
	}

}
