package com.torodeporte.toroDeporteBackEnd.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.torodeporte.toroDeporteBackEnd.controllers.response.EmpleadoResponse;
import com.torodeporte.toroDeporteBackEnd.models.entity.Empleado;
import com.torodeporte.toroDeporteBackEnd.models.entity.Rol;
import com.torodeporte.toroDeporteBackEnd.models.services.IEmpleadoService;
import com.torodeporte.toroDeporteBackEnd.models.services.IRolService;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class EmpleadoRestController {

	@Autowired
	private IEmpleadoService empleadoService;
	
	@Autowired
	public BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/empleados")
	public List<Empleado> getEmpleadosAll(){
		return empleadoService.findAll();
	}
	
	@GetMapping("/empleados/page/{page}")
	public Page<Empleado> getEmpleadosPage(@PathVariable Integer page) {
		return empleadoService.findAll(PageRequest.of(page, 10));
	}
	
	@GetMapping("/empleados/front/{page}")
	public Page<Empleado> getEmpleados(@PathVariable Integer page) {
		//Page<Empleado> empleado = empleadoService.findEmpleado(PageRequest.of(page, 10));
		//EmpleadoResponse response = new EmpleadoResponse(empleado);
		return empleadoService.findEmpleado(PageRequest.of(page, 10));
	}
	
	
	@GetMapping("/empleados/{id}")
	public ResponseEntity<?> getEmpleadoById(@PathVariable Long id) {
		Empleado empleado = null;
		Map<String, Object> response = new HashMap<>();
		try {
			empleado = empleadoService.findById(id);
		} catch (DataAccessException e) {
			response.put("error", "Error en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (empleado == null) {
			response.put("error", "El empleado no se ha encontrado");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Empleado>(empleado, HttpStatus.OK);
	}
	
	@PostMapping("/empleados/create")
	public ResponseEntity<?> saveEmpleado(@RequestBody Empleado empleado) {
		Empleado empleadoNew = null;
		Map<String, Object> response = new HashMap<>();
		try {
			/*for (int i = 0; i < 4; i++) {
				String passwordEncriptada = passwordEncoder.encode(empleado.contrasenaEmpleados);
			}*/
			empleado.contrasenaEmpleados = passwordEncoder.encode(empleado.contrasenaEmpleados);
			empleadoNew = empleadoService.save(empleado);
		} catch (DataAccessException e) {
			System.out.println(e);
			response.put("error", "Error en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Empleado>(empleadoNew, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/empleados/update/{id}")
	public ResponseEntity<?> updateEmpleado(@RequestBody Empleado empleado, @PathVariable Long id) {

		Empleado empleadoSeleccionado = empleadoService.findById(id);
		Empleado empleadoUpdated = null;
		Map<String, Object> response = new HashMap<>();
		try {
			empleadoSeleccionado.setNombreEmpleados(empleado.getNombreEmpleados());
			empleadoSeleccionado.setApellidosEmpleados(empleado.getApellidosEmpleados());
			empleadoSeleccionado.setUsernameEmpleados(empleado.getUsernameEmpleados());
			empleadoSeleccionado.setContrasenaEmpleados(empleado.getContrasenaEmpleados());
			empleadoUpdated = empleadoService.save(empleadoSeleccionado);
		} catch (DataAccessException e) {
			response.put("error", "Error en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (empleadoUpdated == null) {
			response.put("error", "El producto no se ha encontrado");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Empleado>(empleadoUpdated, HttpStatus.CREATED);
	}
	
	@PutMapping("/empleados/change/password/{id}")
	public ResponseEntity<?> updateContrasenaEmpleado(@RequestBody String contrasena, @PathVariable Long id) {

		Empleado empleadoSeleccionado = empleadoService.findById(id);
		Empleado empleadoUpdated = null;
		Map<String, Object> response = new HashMap<>();
		try {
			String contrasenaCode = contrasena;
			empleadoSeleccionado.setContrasenaEmpleados(passwordEncoder.encode(contrasenaCode));
			empleadoUpdated = empleadoService.save(empleadoSeleccionado);
		} catch (DataAccessException e) {
			response.put("error", "Error en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (empleadoUpdated == null) {
			response.put("error", "El producto no se ha encontrado");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Empleado>(empleadoUpdated, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/empleados/delete/{id}")
	public ResponseEntity<?> deleteEmpleado(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			/*Empleado empleado = empleadoService.findById(id);
			String fotoAnterior = empleado.getFotoProducto();
			if (fotoAnterior != null && fotoAnterior.length() > 0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(fotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
				}*/
			empleadoService.delete(id);
		} catch (DataAccessException e) {
			response.put("error", "Error en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El empleado ha sido eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
