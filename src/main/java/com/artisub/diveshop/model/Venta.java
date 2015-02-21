package com.artisub.diveshop.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the ventas database table.
 * 
 */
@Entity
@Table(name="ventas")
@NamedQuery(name="Venta.findAll", query="SELECT v FROM Venta v")
public class Venta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private short cantidad;

	private String comentarios;

	private short descuento;

	@Temporal(TemporalType.TIMESTAMP)
	private Date feha;

	private int numventa;

	private BigDecimal subtotal;

	private String total;

	//bi-directional many-to-one association to Formapago
	@ManyToOne
	private Formapago formapago;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	private Producto producto;

	public Venta() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public short getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(short cantidad) {
		this.cantidad = cantidad;
	}

	public String getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public short getDescuento() {
		return this.descuento;
	}

	public void setDescuento(short descuento) {
		this.descuento = descuento;
	}

	public Date getFeha() {
		return this.feha;
	}

	public void setFeha(Date feha) {
		this.feha = feha;
	}

	public int getNumventa() {
		return this.numventa;
	}

	public void setNumventa(int numventa) {
		this.numventa = numventa;
	}

	public BigDecimal getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public String getTotal() {
		return this.total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public Formapago getFormapago() {
		return this.formapago;
	}

	public void setFormapago(Formapago formapago) {
		this.formapago = formapago;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	
}