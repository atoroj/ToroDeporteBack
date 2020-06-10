package com.torodeporte.toroDeporteBackEnd.models.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.torodeporte.toroDeporteBackEnd.models.dao.IEmpleadoDAO;
import com.torodeporte.toroDeporteBackEnd.models.entity.Empleado;

@Service
public class EmpleadoService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(EmpleadoService.class);

	@Autowired
	private IEmpleadoDAO empleadoDAO;

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Empleado empleado = empleadoDAO.findByUsernameEmpleados(username);

		if (empleado == null) {
			logger.error("No existe el empleado");
			throw new UsernameNotFoundException("Error: No existe el empleado");
		}
		
		List<GrantedAuthority> authorities = empleado.getRoles().stream()
				.map(rol -> new SimpleGrantedAuthority(rol.getNombreRol()))
				.peek(authority -> logger.info("Rol: "+ authority.getAuthority()))
				.collect(Collectors.toList());

		return new User(empleado.getUsernameEmpleados(), empleado.getContrasenaEmpleados(),
				empleado.getEnabledEmpleados(), true, true, true, authorities);
	}

}
