package com.artisub.diveshop.view;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import com.artisub.diveshop.controller.dao.IDAO;
import com.artisub.diveshop.controller.dao.MarcaDao;
import com.artisub.diveshop.model.Marca;

public class MarcaCatalogo extends Catalogo {

	private static final long serialVersionUID = 4109115221222981596L;
	
	private IDAO<Marca> marcaService = new MarcaDao();
	private List<Marca> lista_marcas = marcaService.findAll();

	public MarcaCatalogo() {
		super("Catalog de Marcas", "Catalogo de Marcas");
		setTableData();
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
		table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
	}

	@Override
	protected void addItem() {
		String name = JOptionPane.showInputDialog(this, "Nombre del nuevo Color");
		name = name.toUpperCase();
		Marca marca = new Marca();
		marca.setNombre(name);
		marca = marcaService.create(marca);
		lista_marcas.add(marca);
		model.insertRow(model.getRowCount(), new Object[] {marca.getNombre()});

	}

	@Override
	protected void editItem() {
		Marca marca = marcaService.findById(lista_marcas.get(table.getSelectedRow()).getId());
		new Dialogo("Marcas","Editar Marcas", marca.getNombre()) {
			private static final long serialVersionUID = 6392757110572220864L;
			@Override
			protected void okAction() {
				String val = textfield.getText().toUpperCase();
				marca.setNombre(val);
				marcaService.update(marca);
				model.setValueAt(marca.getNombre(), table.getSelectedRow(), table.getSelectedColumn());
				table.repaint();
				dispose();
			}
		};

	}

	@Override
	protected void deleteItem() {
		Marca marca = marcaService.findById(lista_marcas.get(table.getSelectedRow()).getId());
		int res = JOptionPane.showConfirmDialog(this, "Esta seguro de borrar \""+marca.getNombre()+"\" ?");
		if(JOptionPane.OK_OPTION == res){
			model.removeRow(table.getSelectedRow());
			marcaService.delete(marca);
		}

	}

	@Override
	protected void setTableData() {
		for(Marca m : lista_marcas){
			model.insertRow(model.getRowCount(), new Object[] { m.getNombre()});
		}

	}

}
