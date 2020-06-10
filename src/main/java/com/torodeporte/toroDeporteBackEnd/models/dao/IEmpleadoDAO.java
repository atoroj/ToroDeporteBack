package com.torodeporte.toroDeporteBackEnd.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.torodeporte.toroDeporteBackEnd.models.entity.Empleado;

@Repository
public interface IEmpleadoDAO extends JpaRepository<Empleado, Long>{
	
	public Empleado findByUsernameEmpleados(String usernameEmpleado);
	
	@Query(value="SELECT id_empleados, nombre_empleados, apellidos_empleados, cargo_empleados FROM empleados", nativeQuery = true)
	public Page<Empleado> findEmpleado(Pageable pageable);

}
