package com.torodeporte.toroDeporteBackEnd.controllers.response;

import org.springframework.data.domain.Page;

import com.torodeporte.toroDeporteBackEnd.models.entity.Empleado;

public class EmpleadoResponse {
	public int idEmpleados;
	public String nombreEmpleados;
	public String apellidosEmpleados;
	public int cargoEmpleados;
	
	/*public EmpleadoResponse(Page<Empleado> empleado){
		this.nombreEmpleados = empleado.nombreEmpleados;
		this.idEmpleados = empleado.getContent().
		this.apellidosEmpleados = empleado.apellidosEmpleados;
		this.cargoEmpleados = empleado.cargoEmpleados;
	}*/
	
	public int getIdEmpleados() {
		return idEmpleados;
	}
	public void setIdEmpleados(int idEmpleados) {
		this.idEmpleados = idEmpleados;
	}
	public String getNombreEmpleados() {
		return nombreEmpleados;
	}
	public void setNombreEmpleados(String nombreEmpleados) {
		this.nombreEmpleados = nombreEmpleados;
	}
	public String getApellidosEmpleados() {
		return apellidosEmpleados;
	}
	public void setApellidosEmpleados(String apellidosEmpleados) {
		this.apellidosEmpleados = apellidosEmpleados;
	}
	public int getCargoEmpleados() {
		return cargoEmpleados;
	}
	public void setCargoEmpleados(int cargoEmpleados) {
		this.cargoEmpleados = cargoEmpleados;
	}
}
