package com.artisub.diveshop.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the producto database table.
 * 
 */
@Entity
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String descripcion;

	private int existencias;

	private String numparte;

	private BigDecimal precio;

	//bi-directional many-to-one association to Articulo
	@ManyToOne
	private Articulo articulo;

	//bi-directional many-to-one association to Marca
	@ManyToOne
	private Marca marca;

	//bi-directional many-to-one association to Talla
	@ManyToOne
	private Talla talla;

	//bi-directional many-to-one association to Venta
	@OneToMany(mappedBy="producto")
	private List<Venta> ventas;

	//bi-directional many-to-one association to Color
	@ManyToOne
	private Colors color;

	public Producto() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getExistencias() {
		return this.existencias;
	}

	public void setExistencias(int existencias) {
		this.existencias = existencias;
	}

	public String getNumparte() {
		return this.numparte;
	}

	public void setNumparte(String numparte) {
		this.numparte = numparte;
	}

	public BigDecimal getPrecio() {
		return this.precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public Articulo getArticulo() {
		return this.articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public Marca getMarca() {
		return this.marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Talla getTalla() {
		return this.talla;
	}

	public void setTalla(Talla talla) {
		this.talla = talla;
	}

	public List<Venta> getVentas() {
		return this.ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}

	public Venta addVenta(Venta venta) {
		getVentas().add(venta);
		venta.setProducto(this);

		return venta;
	}

	public Venta removeVenta(Venta venta) {
		getVentas().remove(venta);
		venta.setProducto(null);

		return venta;
	}

	public Colors getColor() {
		return this.color;
	}

	public void setColor(Colors color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Producto [descripcion=" + descripcion + "]";
	}
	
	

}