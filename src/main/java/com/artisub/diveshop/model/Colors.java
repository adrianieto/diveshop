package com.artisub.diveshop.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the colors database table.
 * 
 */
@Entity
@Table(name="colors")
@NamedQuery(name="Colors.findAll", query="SELECT c FROM Colors c")
public class Colors implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String nombre;

	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="color")
	private List<Producto> productos;

	public Colors() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Producto addProducto(Producto producto) {
		getProductos().add(producto);
		producto.setColor(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setColor(null);

		return producto;
	}

	@Override
	public String toString() {
		return nombre;
	}

	
}