package com.torodeporte.toroDeporteBackEnd.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.torodeporte.toroDeporteBackEnd.models.entity.Productos;

public interface IProductoService {
	
	public List<Productos> findAll();
	
	public Page<Productos> findAll(Pageable pageable);
	
	public Productos findById(Long id);
	
	public Productos save(Productos producto);
	
	public void delete(Long id);
}
