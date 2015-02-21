package com.artisub.diveshop.view;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import com.artisub.diveshop.controller.dao.FormapagoDao;
import com.artisub.diveshop.controller.dao.IDAO;
import com.artisub.diveshop.model.Formapago;

public class FormaPagoCatalogo extends Catalogo {
	
	private static final long serialVersionUID = 7923446985058260114L;
	
	private IDAO<Formapago> formapagoService = new FormapagoDao();
	private List<Formapago> lista_formapagos = formapagoService.findAll();
	
	public FormaPagoCatalogo(){
		super("Catalogo de Formas de Pago", "Catalogo de Formas de Pago");
		setTableData();
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
		table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
	}

	
	@Override
	protected void addItem(){
		String name = JOptionPane.showInputDialog(this, "Nombre de la nueva Forma de Pago");
		name = name.toUpperCase();
		Formapago nfp = new Formapago();
		nfp.setFormapago(name);
		nfp = formapagoService.create(nfp);
		lista_formapagos.add(nfp);
		model.insertRow(model.getRowCount(), new Object[] {nfp.getFormapago()});
	}
	
	@Override
	protected void editItem(){
		Formapago fp = formapagoService.findById(lista_formapagos.get(table.getSelectedRow()).getId());
		
		new Dialogo("Formas de Pago", "Editar formas de Pago", fp.getFormapago()) {
			private static final long serialVersionUID = -4662925180406083807L;
			@Override
			protected void okAction() {
				String val = textfield.getText();
				val = val.toUpperCase();
				fp.setFormapago(val);
				formapagoService.update(fp);
				model.setValueAt(fp.getFormapago(), table.getSelectedRow(), table.getSelectedColumn());
				dispose();
			}
		};
	}
	
	@Override
	protected void deleteItem(){
		Formapago color = formapagoService.findById(lista_formapagos.get(table.getSelectedRow()).getId());
		int res = JOptionPane.showConfirmDialog(this, "Esta seguro de borrar \""+color.getFormapago()+"\" ?");
		if(JOptionPane.OK_OPTION == res){
			model.removeRow(table.getSelectedRow());
			formapagoService.delete(color);
		}
		
	}
	
	@Override
	protected void setTableData(){
		for(Formapago p : lista_formapagos){
			model.insertRow(model.getRowCount(), new Object[] { p.getFormapago()});
		}

	}
}
