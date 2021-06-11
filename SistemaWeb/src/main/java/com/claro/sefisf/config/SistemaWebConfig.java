package com.claro.sefisf.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource(value= {"classpath:application.properties"})
public class SistemaWebConfig {

	@Value("${url.direcciones}")
	public String urlDirecciones;
	
	@Value("${url.instalaciones}")
	public String urlInstalaciones;
	
	@Bean
	public RestTemplate getresttemplate() {
		return new RestTemplate();
	}

}
