package com.artisub.diveshop.view.catalogos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.artisub.diveshop.controller.dao.IDAO;
import com.artisub.diveshop.controller.dao.ProductoDao;
import com.artisub.diveshop.model.Producto;
import com.artisub.diveshop.view.MainFrame;

public class ProductoCatalogo extends Catalogo{

	private static final long serialVersionUID = -427568941200794323L;
	
	JLabel buscarLbl;
	JTextField searchText;
	JButton findBtn;
	
	public DefaultTableModel modelo = new DefaultTableModel();
	
	public IDAO<Producto> productoService = new ProductoDao();
    public List<Producto> lista_productos = productoService.findAll();
    
    protected static HashMap<Integer, Integer> posindex = new HashMap<Integer, Integer>();

	public ProductoCatalogo() {
		super("Catalogo Productos", "Catalogo Productos");
		
		modelo.addColumn("Id");
		modelo.addColumn("# Parte");
		modelo.addColumn("Marca");
		modelo.addColumn("Articulo");
		modelo.addColumn("Modelo");
		modelo.addColumn("Color");
		modelo.addColumn("Talla");
		modelo.addColumn("Existencias");
		modelo.addColumn("Precio");
		
		table.setModel(modelo);
		
		setTableData();
		
		setSize(960, 520);
		setLocation(200, 130);
		
		buscarLbl = new JLabel("Buscar: ");
		buscarLbl.setFont(new Font("Lao UI",Font.PLAIN,18));
		searchText = new JTextField(20);
		findBtn = new JButton();
		
		menuActionPanel.add(buscarLbl);
		menuActionPanel.add(searchText);
		menuActionPanel.add(findBtn);
		
		table.setPreferredScrollableViewportSize(new Dimension(900,320));
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	    centerRenderer.setHorizontalAlignment( JLabel.CENTER );
	    table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
	    table.getColumnModel().getColumn(5).setCellRenderer( centerRenderer );
	    table.getColumnModel().getColumn(6).setCellRenderer( centerRenderer );
	    table.getColumnModel().getColumn(7).setCellRenderer( centerRenderer );
	    
	    DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
	    rightRenderer.setHorizontalAlignment( JLabel.RIGHT );
	    table.getColumnModel().getColumn(8).setCellRenderer( rightRenderer);
		
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
//		table.getColumnModel().getColumn(7).setMinWidth(0);
//		table.getColumnModel().getColumn(7).setMaxWidth(0);
		
		table.getColumnModel().getColumn(4).setPreferredWidth(200);
		
		final TableRowSorter<TableModel> sorter;
	    sorter = new TableRowSorter<TableModel>(modelo);
	    table.setRowSorter(sorter);
	    
	    findBtn.setBackground(Color.WHITE);
	    findBtn.setIcon(new ImageIcon("src/main/resources/Icons/lupa.png"));
	    findBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String expr = searchText.getText().toUpperCase();
		        sorter.setRowFilter(RowFilter.regexFilter(expr));
		        sorter.setSortKeys(null);
		        int i = 0;
				for(Producto p : lista_productos){
					ProductoCatalogo.posindex.put(p.getId(), i);
					i++;
				}
			}
		});
		
	}
	
	public int getMapIndex(int id){
		int i = 0;
		for(Producto p : lista_productos){
			ProductoCatalogo.posindex.put(p.getId(), i);
			i++;
		}
		return posindex.get(id);
	}
	
	
	@Override
	protected void addItem() {
		NewProductoDialogo pd = new NewProductoDialogo(this);
		pd.setVisible(true);
		MainFrame.desktopPane.add(pd,JDesktopPane.POPUP_LAYER);
		try {
			pd.setSelected(true);
		} catch (PropertyVetoException e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	protected void editItem() {
//		Producto prod = productoService.findById((int)(table.getModel().getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 0)));
		EditProductoDialogo ep = new EditProductoDialogo(this);
		ep.setVisible(true);
		
		MainFrame.desktopPane.add(ep, JDesktopPane.POPUP_LAYER);
		try {
			ep.setSelected(true);
		} catch (PropertyVetoException e1) {
			e1.printStackTrace();
		}
		
	}



	@Override
	protected void deleteItem() {
//		Producto prod = productoService.findById((lista_productos.get(table.getSelectedRow()).getId()));
		Producto prod = productoService.findById((int)(table.getModel().getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 0)));
		int res = JOptionPane.showConfirmDialog(this, "Esta seguro de borrar \""+prod.getArticulo()+" "+prod.getDescripcion()+"\" ?");
		if(JOptionPane.OK_OPTION == res){
			productoService.delete(prod);
			lista_productos.remove(prod);
			posindex.clear();
			modelo.removeRow(getMapIndex(prod.getId()));
			
			
		}
		table.repaint();
	}

	@Override
	protected void setTableData() {
		for(Producto p : lista_productos){
			modelo.insertRow(modelo.getRowCount(), new Object[] {p.getId(),
																 p.getNumparte(), 
																 p.getMarca().toString(), 
																 p.getArticulo().toString(), 
																 p.getDescripcion(), p.getColor().toString(), 
																 p.getTalla().toString(), 
																 p.getExistencias(), 
																 p.getPrecio()});
		}
		
	}
	


}
