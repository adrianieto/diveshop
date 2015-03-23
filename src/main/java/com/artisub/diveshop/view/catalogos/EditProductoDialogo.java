package com.artisub.diveshop.view.catalogos;

import java.math.BigDecimal;

import com.artisub.diveshop.model.Producto;

public class EditProductoDialogo extends ProductoDialogo {

	private static final long serialVersionUID = 1L;
	
	private static ProductoCatalogo productoCatalogo;
	
	
	
	public EditProductoDialogo(ProductoCatalogo producto_Catalogo) {
		productoCatalogo = producto_Catalogo;
//		setLocationRelativeTo(null);
		
        Producto producto = productoCatalogo.productoService.findById((int)(productoCatalogo.table.getModel().getValueAt(productoCatalogo.table.convertRowIndexToModel(productoCatalogo.table.getSelectedRow()), 0)));		
		int i = productoCatalogo.getMapIndex(producto.getId());
		
		marcaComboBox.setSelectedItem(productoCatalogo.modelo.getValueAt(i, 2));
		articuloComboBox.setSelectedItem(productoCatalogo.modelo.getValueAt(i, 3));
		colorComboBox.setSelectedItem(productoCatalogo.modelo.getValueAt(i, 5));
		tallaComboBox.setSelectedItem(productoCatalogo.modelo.getValueAt(i, 6));
		partetextField.setText((String)productoCatalogo.modelo.getValueAt(i, 1));
		modeloTextField.setText((String)productoCatalogo.modelo.getValueAt(i, 4));
		precioTextField.setText(productoCatalogo.modelo.getValueAt(i, 8).toString());
		existenciasTextField.setText(producto.getExistencias().toString());
		codigoTextField.setText(producto.getCodigo());
	}

	@Override
	protected void saveProducto() {
		Producto producto = productoCatalogo.productoService.findById((int)(productoCatalogo.table.getModel().getValueAt(productoCatalogo.table.convertRowIndexToModel(productoCatalogo.table.getSelectedRow()), 0)));
//		Producto producto = productoCatalogo.productoService.findById((productoCatalogo.lista_productos.get(productoCatalogo.table.getSelectionModel().getSelectionMode()).getId()));
		producto.setMarca(getSelectedMarca());
		producto.setArticulo(getSelectedArticulo());
		producto.setColor(getSelectedColor());
		producto.setTalla(getSelectedTalla());
		producto.setDescripcion(modeloTextField.getText().toUpperCase());
		producto.setNumparte(partetextField.getText().toUpperCase());
		producto.setPrecio(new BigDecimal(precioTextField.getText()));
		producto.setExistencias(Integer.parseInt(existenciasTextField.getText()));
		producto.setCodigo(codigoTextField.getText());
		
		productoCatalogo.productoService.update(producto);
//		int selectedRow =  (int)(productoCatalogo.table.getModel().getValueAt(productoCatalogo.table.convertRowIndexToModel(productoCatalogo.table.getSelectedRow()), 0));

		
		int i = productoCatalogo.getMapIndex(producto.getId());;
		productoCatalogo.modelo.setValueAt(producto.getId(),i, 0);
		productoCatalogo.modelo.setValueAt(producto.getNumparte().toString(),i, 1);
		productoCatalogo.modelo.setValueAt(producto.getMarca().toString(), i, 2);
		productoCatalogo.modelo.setValueAt(producto.getArticulo().toString(), i, 3);
		productoCatalogo.modelo.setValueAt(producto.getDescripcion().toString(), i, 4);
		productoCatalogo.modelo.setValueAt(producto.getColor().toString(), i, 5);
		productoCatalogo.modelo.setValueAt(producto.getTalla().toString(), i, 6);
		productoCatalogo.modelo.setValueAt(producto.getExistencias(), i, 7);
		productoCatalogo.modelo.setValueAt(producto.getPrecio(), i, 8);
		
		productoCatalogo.table.repaint();
		
	}
	

	


}
