package com.artisub.diveshop.view;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import com.artisub.diveshop.controller.dao.IDAO;
import com.artisub.diveshop.controller.dao.TallaDao;
import com.artisub.diveshop.model.Talla;

public class TallaCatalogo extends Catalogo {

	private static final long serialVersionUID = 3132792278770038231L;
	
	private IDAO<Talla> tallaService = new TallaDao();
	private List<Talla> lista_tallas = tallaService.findAll();

	public TallaCatalogo() {
		super("Catalogo de Tallas", "Catalogo de Tallas");
		setTableData();
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
		table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
	}

	@Override
	protected void addItem() {
		String name = JOptionPane.showInputDialog(this, "Nombre de la nueva Talla");
		name = name.toUpperCase();
		Talla talla = new Talla();
		talla.setNombre(name);
		talla = tallaService.create(talla);
		lista_tallas.add(talla);
		model.insertRow(model.getRowCount(), new Object[] {talla.getNombre()});
	}

	@Override
	protected void editItem() {
		Talla talla = tallaService.findById(lista_tallas.get(table.getSelectedRow()).getId());
		
		new Dialogo("Tallas", "Editar Tallas", talla.getNombre()) {
			private static final long serialVersionUID = 5027167321553161454L;
			@Override
			protected void okAction() {
				String val = textfield.getText();
				val = val.toUpperCase();
				talla.setNombre(val);
				tallaService.update(talla);
				model.setValueAt(val, table.getSelectedRow(), table.getSelectedColumn());
				dispose();
			}
		};
	}

	@Override
	protected void deleteItem() {
		Talla talla = tallaService.findById(lista_tallas.get(table.getSelectedRow()).getId());
		int res = JOptionPane.showConfirmDialog(this, "Esta seguro de borrar \""+ talla.getNombre()+"\" ?");
		if(JOptionPane.OK_OPTION == res){
			model.removeRow(table.getSelectedRow());
			tallaService.delete(talla);
		}
	}

	@Override
	protected void setTableData() {
		for(Talla t : lista_tallas){
			model.insertRow(model.getRowCount(), new Object[] {t.getNombre()});
		}

	}

}
