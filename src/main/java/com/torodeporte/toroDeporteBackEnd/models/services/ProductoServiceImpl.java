package com.torodeporte.toroDeporteBackEnd.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.torodeporte.toroDeporteBackEnd.models.dao.IProductoDAO;
import com.torodeporte.toroDeporteBackEnd.models.entity.Productos;

@Service
public class ProductoServiceImpl implements IProductoService{
	
	@Autowired
	private IProductoDAO productoDAO;
	
	@Transactional( readOnly = true)
	public List<Productos> findAll() {
		return (List<Productos>) productoDAO.findAll();
	}
	
	@Transactional( readOnly = true)
	public Page<Productos> findAll(Pageable pageable) {
		return productoDAO.findAll(pageable);
	}
	
	@Transactional( readOnly = true)
	public Productos findById(Long id) {
		return productoDAO.findById(id).orElse(null);
	}

	public Productos save(Productos producto) {
		return productoDAO.save(producto);
	}

	public void delete(Long id) {
		productoDAO.deleteById(id);	
	}
	

	
}
