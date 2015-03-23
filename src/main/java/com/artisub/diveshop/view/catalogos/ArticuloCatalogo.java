package com.artisub.diveshop.view;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import com.artisub.diveshop.controller.dao.ArticuloDao;
import com.artisub.diveshop.controller.dao.IDAO;
import com.artisub.diveshop.model.Articulo;

public class ArticuloCatalogo extends Catalogo {

	private static final long serialVersionUID = -5996653965306107708L;
	
	private IDAO<Articulo> articuloService = new ArticuloDao();
	private List<Articulo> lista_articulos = articuloService.findAll();

	public ArticuloCatalogo() {
		super("Catalogo de Articulos", "Catalogo de Articulos");
		setTableData();
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
		table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
	}

	@Override
	protected void addItem() {
		String name = JOptionPane.showInputDialog(this, "Nombre del Articulo nuevo");
		name = name.toUpperCase();
		Articulo articulo = new Articulo();
		articulo.setNombre(name);
		articulo = articuloService.create(articulo);
		lista_articulos.add(articulo);
		model.insertRow(model.getRowCount(), new Object[] {articulo.getNombre()});

	}

	@Override
	protected void editItem() {
		Articulo articulo = articuloService.findById(lista_articulos.get(table.getSelectedRow()).getId());
		
		new Dialogo("Articulos","Editar Articulos", articulo.getNombre()) {
			private static final long serialVersionUID = 8075908448456753456L;
			@Override
			protected void okAction() {
				String val = textfield.getText();
				val = val.toUpperCase();
				articulo.setNombre(val);
				articuloService.update(articulo);
				model.setValueAt(articulo.getNombre(), table.getSelectedRow(), table.getSelectedColumn());
				table.repaint();
				dispose();
			}
		};

	}

	@Override
	protected void deleteItem() {
		Articulo articulo = articuloService.findById(lista_articulos.get(table.getSelectedRow()).getId());
		int res = JOptionPane.showConfirmDialog(this, "Esta seguro de borrar \""+articulo.getNombre()+"\" ?");
		if(JOptionPane.OK_OPTION == res){
			model.removeRow(table.getSelectedRow());
			articuloService.delete(articulo);
		}

	}

	@Override
	protected void setTableData() {
		for(Articulo a : lista_articulos){
			model.insertRow(model.getRowCount(), new Object[] { a.getNombre()});
		}


	}

}
