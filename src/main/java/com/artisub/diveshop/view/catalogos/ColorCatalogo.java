package com.artisub.diveshop.view;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import com.artisub.diveshop.controller.dao.ColorsDao;
import com.artisub.diveshop.controller.dao.IDAO;
import com.artisub.diveshop.model.Colors;

public class ColorCatalogo extends Catalogo {
	
	private static final long serialVersionUID = 7923446985058260114L;
	
	private IDAO<Colors> colorService = new ColorsDao();
	private List<Colors> lista_colores = colorService.findAll();
	
	public ColorCatalogo(){
		super("Catalogo de Colores", "Catalogo de Colores");
		setTableData();
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
		table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
	}

	
	@Override
	protected void addItem(){
		String name = JOptionPane.showInputDialog(this, "Nombre del nuevo Color");
		name = name.toUpperCase();
		Colors ncolor = new Colors();
		ncolor.setNombre(name);
		ncolor = colorService.create(ncolor);
		lista_colores.add(ncolor);
		model.insertRow(model.getRowCount(), new Object[] {ncolor.getNombre()});
	}
	
	@Override
	protected void editItem(){
		Colors color = colorService.findById(lista_colores.get(table.getSelectedRow()).getId());
		
		new Dialogo("Colores","Editar Color", color.getNombre()) {
			private static final long serialVersionUID = 1920091302649263361L;
			@Override
			protected void okAction() {
				String val = textfield.getText();
				val = val.toUpperCase();
				//Colors color = colorService.findById(lista_colores.get(table.getSelectedRow()).getId());
				color.setNombre(val);
				colorService.update(color);
				model.setValueAt(color.getNombre(), table.getSelectedRow(), table.getSelectedColumn());
				table.repaint();
				dispose();
			}
		};
	}
	
	@Override
	protected void deleteItem(){
		Colors color = colorService.findById(lista_colores.get(table.getSelectedRow()).getId());
		int res = JOptionPane.showConfirmDialog(this, "Esta seguro de borrar \""+color.getNombre()+"\" ?");
		if(JOptionPane.OK_OPTION == res){
			model.removeRow(table.getSelectedRow());
			colorService.delete(color);
		}
	}
	
	@Override
	protected void setTableData(){
		for(Colors c : lista_colores){
			model.insertRow(model.getRowCount(), new Object[] { c.getNombre()});
		}

	}
}

