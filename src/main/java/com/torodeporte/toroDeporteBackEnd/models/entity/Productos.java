package com.torodeporte.toroDeporteBackEnd.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "productos")
public class Productos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_producto")
	public Long idProducto;
	
	@Column(name = "nombre_producto", nullable = false)
	public String nombreProducto;
	
	@Column(name = "categoria_producto", nullable = false)
	public String categoriaProducto;
	
	@Column(name = "precio_producto", nullable = false)
	public float precioProducto;
	
	@Column(name = "coste_producto", nullable = false)
	public float costeProducto;
	
	@Column(name = "stock_producto", nullable = false)
	public int stockProducto;
	
	public String fotoProducto;
	
	
	public int getStockProducto() {
		return stockProducto;
	}

	public void setStockProducto(int stockProducto) {
		this.stockProducto = stockProducto;
	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getCategoriaProducto() {
		return categoriaProducto;
	}

	public void setCategoriaProducto(String categoriaProducto) {
		this.categoriaProducto = categoriaProducto;
	}

	public float getPrecioProducto() {
		return precioProducto;
	}

	public void setPrecioProducto(float precioProducto) {
		this.precioProducto = precioProducto;
	}

	public float getCosteProducto() {
		return costeProducto;
	}

	public void setCosteProducto(float costeProducto) {
		this.costeProducto = costeProducto;
	}

	public String getFotoProducto() {
		return fotoProducto;
	}

	public void setFotoProducto(String fotoProducto) {
		this.fotoProducto = fotoProducto;
	}
	
}
