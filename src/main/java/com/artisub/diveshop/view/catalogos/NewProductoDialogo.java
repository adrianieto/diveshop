package com.artisub.diveshop.view;

import java.math.BigDecimal;

import com.artisub.diveshop.model.Producto;

public class NewProductoDialogo extends PDialogo {

	private static final long serialVersionUID = 3752640680554238955L;
	
	private ProductoCatalogo productoCatalogo;
	
	public NewProductoDialogo(ProductoCatalogo productoCatalogo) {
		this.productoCatalogo = productoCatalogo;
//		setLocationRelativeTo(null);
		
		if(productoCatalogo.table.getSelectedRow() != -1){
			Producto producto = productoCatalogo.productoService.findById((int)(productoCatalogo.table.getModel().getValueAt(productoCatalogo.table.convertRowIndexToModel(productoCatalogo.table.getSelectedRow()), 0)));		
			int i = productoCatalogo.getMapIndex(producto.getId());
			
			marcaComboBox.setSelectedItem(productoCatalogo.modelo.getValueAt(i, 2));
			articuloComboBox.setSelectedItem(productoCatalogo.modelo.getValueAt(i, 3));
			colorComboBox.setSelectedItem(productoCatalogo.modelo.getValueAt(i, 5));
			tallaComboBox.setSelectedItem(productoCatalogo.modelo.getValueAt(i, 6));
			partetextField.setText((String)productoCatalogo.modelo.getValueAt(i, 1));
			modeloTextField.setText((String)productoCatalogo.modelo.getValueAt(i, 4));
			precioTextField.setText(productoCatalogo.modelo.getValueAt(i, 8).toString());
			existenciasTextField.setText(productoCatalogo.modelo.getValueAt(i, 7).toString());
			codigoTextField.setText(producto.getCodigo());
		}
		
		
	}

	@Override
	protected void saveProducto() {
		Producto p = new Producto();
		p.setArticulo(getSelectedArticulo());
		p.setMarca(getSelectedMarca());
		p.setTalla(getSelectedTalla());
		p.setColor(getSelectedColor());
		p.setDescripcion(modeloTextField.getText().toUpperCase());
		p.setNumparte(partetextField.getText().toUpperCase());
		p.setPrecio(new BigDecimal(precioTextField.getText()));
		p.setExistencias(Integer.parseInt(existenciasTextField.getText()));
		p.setCodigo(codigoTextField.getText());
		
		productoCatalogo.productoService.create(p); // persist the new object
		productoCatalogo.lista_productos.add(p);
		productoCatalogo.modelo.insertRow(productoCatalogo.modelo.getRowCount(), new Object[]{p.getId(),p.getNumparte(), p.getMarca().toString(), p.getArticulo().toString(), p.getDescripcion(), p.getColor().toString(), p.getTalla().toString(), p.getExistencias(), p.getPrecio()});
	
		ProductoCatalogo.posindex.clear();
		int i =0;
		for(Producto prod : productoCatalogo.lista_productos){
			ProductoCatalogo.posindex.put(prod.getId(), i);
			i++;
		}
	}	

}
