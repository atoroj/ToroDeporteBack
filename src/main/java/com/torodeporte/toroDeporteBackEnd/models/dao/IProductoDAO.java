package com.torodeporte.toroDeporteBackEnd.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.torodeporte.toroDeporteBackEnd.models.entity.Productos;

public interface IProductoDAO extends JpaRepository<Productos, Long>{

}
