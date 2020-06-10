package com.torodeporte.toroDeporteBackEnd.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.torodeporte.toroDeporteBackEnd.models.entity.Empleado;
import com.torodeporte.toroDeporteBackEnd.models.services.IEmpleadoService;

@Component
public class InfoAdicional implements TokenEnhancer{
	
	@Autowired
	private IEmpleadoService empleadoService;
	
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		
		Map<String, Object> info = new HashMap<>();
		Empleado empleado = empleadoService.findByUsernameEmpleados(authentication.getName());
		info.put("idEmpleado", empleado.getIdEmpleados());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		
		return accessToken;
	}
	
	
}
