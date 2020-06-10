package com.torodeporte.toroDeporteBackEnd.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.torodeporte.toroDeporteBackEnd.models.entity.Empleado;

public interface IEmpleadoService {
	
	public List<Empleado> findAll();
	
	public Page<Empleado> findAll(Pageable pageable);
	
	public Page<Empleado> findEmpleado(Pageable pageable);
	
	public Empleado findById(Long id);
	
	public Empleado save(Empleado empleado);
	
	public Empleado findByUsernameEmpleados(String username);
	
	public void delete(Long id);
}
