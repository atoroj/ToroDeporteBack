package com.torodeporte.toroDeporteBackEnd.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.torodeporte.toroDeporteBackEnd.models.dao.IEmpleadoDAO;
import com.torodeporte.toroDeporteBackEnd.models.entity.Empleado;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService{

	@Autowired
	private IEmpleadoDAO empleadoDAO;
	
	@Transactional( readOnly = true)
	public List<Empleado> findAll() {
		return (List<Empleado>) empleadoDAO.findAll();
	}

	@Transactional( readOnly = true)
	public Page<Empleado> findAll(Pageable pageable) {
		return empleadoDAO.findAll(pageable);
	}
	
	@Transactional( readOnly = true)
	public Page<Empleado> findEmpleado(Pageable pageable) {
		return empleadoDAO.findEmpleado(pageable);
	}
	
	@Transactional( readOnly = true)
	public Empleado findById(Long id) {
		return empleadoDAO.findById(id).orElse(null);
	}

	@Override
	public Empleado save(Empleado empleado) {
		return empleadoDAO.save(empleado);
	}

	@Override
	public void delete(Long id) {
		empleadoDAO.deleteById(id);	
	}

	@Override
	public Empleado findByUsernameEmpleados(String username) {
		return empleadoDAO.findByUsernameEmpleados(username);
	}

}
