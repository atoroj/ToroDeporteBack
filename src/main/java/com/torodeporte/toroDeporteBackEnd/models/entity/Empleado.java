package com.torodeporte.toroDeporteBackEnd.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "empleados")
public class Empleado implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_empleados")
	public Long idEmpleados;

	@Column(name = "nombre_empleados")
	public String nombreEmpleados;

	@Column(name = "apellidos_empleados")
	public String apellidosEmpleados;

	@Column(length = 60, name = "contrasena_empleados")
	public String contrasenaEmpleados;

	@Column(name = "enabled_empleados")
	public Boolean enabledEmpleados;

	@Column(name = "dni_empleados")
	public String dniEmpleados;

	@Column(unique = true, length = 20, name = "username_empleados")
	public String usernameEmpleados;

	@Column(name = "cargo_empleados")
	public int cargoEmpleados;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	public List<Rol> roles;

	public String getUsernameEmpleados() {
		return usernameEmpleados;
	}

	public void setUsernameEmpleados(String usernameEmpleados) {
		this.usernameEmpleados = usernameEmpleados;
	}

	public String getContrasenaEmpleados() {
		return contrasenaEmpleados;
	}

	public void setContrasenaEmpleados(String contrasenaEmpleados) {
		this.contrasenaEmpleados = contrasenaEmpleados;
	}

	public Boolean getEnabledEmpleados() {
		return enabledEmpleados;
	}

	public void setEnabledEmpleados(Boolean enabledEmpleados) {
		this.enabledEmpleados = enabledEmpleados;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public Long getIdEmpleados() {
		return idEmpleados;
	}

	public void setIdEmpleados(Long idEmpleados) {
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

	public String getDniEmpleados() {
		return dniEmpleados;
	}

	public void setDniEmpleados(String dniEmpleados) {
		this.dniEmpleados = dniEmpleados;
	}

	public int getCargoEmpleados() {
		return cargoEmpleados;
	}

	public void setCargoEmpleados(int cargoEmpleados) {
		this.cargoEmpleados = cargoEmpleados;
	}

	private static final long serialVersionUID = 1L;

}
