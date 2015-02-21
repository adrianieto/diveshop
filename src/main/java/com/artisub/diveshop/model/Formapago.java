package com.artisub.diveshop.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the formapago database table.
 * 
 */
@Entity
@NamedQuery(name="Formapago.findAll", query="SELECT f FROM Formapago f")
public class Formapago implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String formapago;

	//bi-directional many-to-one association to Venta
	@OneToMany(mappedBy="formapago")
	private List<Venta> ventas;

	public Formapago() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFormapago() {
		return this.formapago;
	}

	public void setFormapago(String formapago) {
		this.formapago = formapago;
	}

	public List<Venta> getVentas() {
		return this.ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}

	public Venta addVenta(Venta venta) {
		getVentas().add(venta);
		venta.setFormapago(this);

		return venta;
	}

	public Venta removeVenta(Venta venta) {
		getVentas().remove(venta);
		venta.setFormapago(null);

		return venta;
	}

	@Override
	public String toString() {
		return formapago;
	}

	
}