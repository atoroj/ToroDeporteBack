package com.torodeporte.toroDeporteBackEnd.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.torodeporte.toroDeporteBackEnd.models.entity.Productos;
import com.torodeporte.toroDeporteBackEnd.models.services.IProductoService;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class ProductoRestController {

	@Autowired
	private IProductoService productoService;

	@GetMapping("/productos")
	public List<Productos> getProductos() {
		return productoService.findAll();
	}

	@GetMapping("/productos/page/{page}")
	public Page<Productos> getProductos(@PathVariable Integer page) {
		return productoService.findAll(PageRequest.of(page, 10));
	}

	@GetMapping("/productos/{id}")
	public ResponseEntity<?> getProductoById(@PathVariable Long id) {
		Productos producto = null;
		Map<String, Object> response = new HashMap<>();
		try {
			producto = productoService.findById(id);
		} catch (DataAccessException e) {
			response.put("error", "Error en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (producto == null) {
			response.put("error", "El producto no se ha encontrado");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Productos>(producto, HttpStatus.OK);
	}

	@PostMapping("/productos/create")
	public ResponseEntity<?> saveProducto(@RequestBody Productos producto) {
		Productos productoNew = null;
		Map<String, Object> response = new HashMap<>();
		try {
			productoNew = productoService.save(producto);
		} catch (DataAccessException e) {
			response.put("error", "Error en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Productos>(productoNew, HttpStatus.CREATED);
	}

	@PutMapping("/productos/update/{id}")
	public ResponseEntity<?> updateProducto(@RequestBody Productos producto, @PathVariable Long id) {

		Productos productoSeleccionado = productoService.findById(id);
		Productos productoUpdated = null;
		Map<String, Object> response = new HashMap<>();
		try {
			productoSeleccionado.setNombreProducto(producto.getNombreProducto());
			productoSeleccionado.setCategoriaProducto(producto.getCategoriaProducto());
			productoSeleccionado.setCosteProducto(producto.getCosteProducto());
			productoSeleccionado.setPrecioProducto(producto.getPrecioProducto());
			productoSeleccionado.setStockProducto(producto.getStockProducto());
			productoUpdated = productoService.save(productoSeleccionado);
		} catch (DataAccessException e) {
			response.put("error", "Error en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (productoUpdated == null) {
			response.put("error", "El producto no se ha encontrado");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Productos>(productoUpdated, HttpStatus.CREATED);
	}

	@DeleteMapping("/productos/delete/{id}")
	public ResponseEntity<?> deleteProducto(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Productos producto = productoService.findById(id);
			String fotoAnterior = producto.getFotoProducto();
			if (fotoAnterior != null && fotoAnterior.length() > 0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(fotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
				}
			}
			productoService.delete(id);
		} catch (DataAccessException e) {
			response.put("error", "Error en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El producto ha sido eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PostMapping("/productos/upload")
	public ResponseEntity<?> subirImagen(@RequestParam("foto") MultipartFile file, @RequestParam("idProducto") Long id) {
		Map<String, Object> response = new HashMap<>();
		Productos producto = productoService.findById(id);
		if (!file.isEmpty()) {
			String nombreFile = UUID.randomUUID().toString() + "-" + file.getOriginalFilename().replace(" ", "");
			Path rutaFile = Paths.get("uploads").resolve(nombreFile).toAbsolutePath();

			try {
				Files.copy(file.getInputStream(), rutaFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			String fotoAnterior = producto.getFotoProducto();

			if (fotoAnterior != null && fotoAnterior.length() > 0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(fotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
				}
			}

			producto.setFotoProducto(nombreFile);
			productoService.save(producto);
		}
		response.put("producto", producto);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verImagen(@PathVariable String nombreFoto){
		Path rutaFile = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
		Resource recurso = null;
		try {
			recurso = new UrlResource(rutaFile.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		if(!recurso.exists() && !recurso.isReadable()) {
			throw new RuntimeException("Error: No se pudo cargar la imagen");
		}
		HttpHeaders cabecera = new HttpHeaders();
		
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"" );
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}
}
