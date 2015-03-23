package com.artisub.diveshop.view.pos;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.persistence.NoResultException;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.artisub.diveshop.controller.dao.IDAO;
import com.artisub.diveshop.controller.dao.ProductoDao;
import com.artisub.diveshop.model.Producto;

public class SearchFrame extends JInternalFrame {

	private static final long serialVersionUID = -4515877651048073657L;
	
	Container container;
	JPanel searchPanel, buttonPanel;
	JTextField searchField;
	JLabel searchLbl;
	JTable table;
	DefaultTableModel modelo;
	JScrollPane scroll;
	String searchText;
	JButton chooseBtn, cancelBtn;
	public Producto producto;
	private PuntoDeVenta pv;
	
	public IDAO<Producto> productoService = new ProductoDao();
    public List<Producto> lista_productos = productoService.findAll();
    
    protected static HashMap<Integer, Integer> posindex = new HashMap<Integer, Integer>();
	
	public SearchFrame(PuntoDeVenta pv, String searchText){
		this.searchText = searchText;
		this.pv = pv;
		setBounds(350, 100, 620, 350);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		init();
	}
	
	public void init(){
		container = getContentPane();
		container.setLayout(new BorderLayout());
		
		searchPanel = new JPanel(new FlowLayout());
		
		searchField = new JTextField(25);
		searchLbl = new JLabel("Buscar: ");
		
		modelo = new DefaultTableModel();
		modelo.addColumn("Id");
		modelo.addColumn("Marca");
		modelo.addColumn("Articulo");
		modelo.addColumn("Modelo");
		modelo.addColumn("Color");
		modelo.addColumn("Talla");
		modelo.addColumn("Existencias");
		modelo.addColumn("Precio");
		
		table = new JTable(modelo);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		
		scroll = new JScrollPane(table);
		
		setTableData();
		
		final TableRowSorter<TableModel> sorter;
	    sorter = new TableRowSorter<TableModel>(modelo);
	    table.setRowSorter(sorter);
	    
	    searchField.setText(searchText);
	    String expr = searchText.toUpperCase();
        sorter.setRowFilter(RowFilter.regexFilter(expr));
        sorter.setSortKeys(null);
	        
        
	    int i = 0;
		for(Producto p : lista_productos){
			posindex.put(p.getId(), i);
			i++;
		}
	    
	    searchField.addKeyListener(new KeyAdapter() {
	    	public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					String expr = searchField.getText().toUpperCase();
			        sorter.setRowFilter(RowFilter.regexFilter(expr));
			        sorter.setSortKeys(null);
				}
	    	}
		});
	    
	    chooseBtn = new JButton("Escoger");
	    chooseBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			    descripcionTextFieldSearch();
				dispose();
			}
		});
	    
	    cancelBtn = new JButton("Cancelar");
	    cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
	    
	    buttonPanel = new JPanel(new FlowLayout());
	    buttonPanel.add(chooseBtn);
	    buttonPanel.add(cancelBtn);
	    
		searchPanel.add(searchLbl);
		searchPanel.add(searchField);
		container.add(searchPanel, BorderLayout.PAGE_START);
		container.add(scroll, BorderLayout.CENTER);
		container.add(buttonPanel, BorderLayout.PAGE_END);
	}
	
	void setTableData() {
		for(Producto p : lista_productos){
			modelo.insertRow(modelo.getRowCount(), new Object[] {p.getId(),
																 p.getMarca().toString(), 
																 p.getArticulo().toString(), 
																 p.getDescripcion(), p.getColor().toString(), 
																 p.getTalla().toString(), 
																 p.getExistencias(), 
																 p.getPrecio()});
		}
	}
	
	private void descripcionTextFieldSearch(){
		
		try{
			producto = productoService.findById((int)(table.getModel().getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 0)));
			pv.producto = producto;
			pv.modeloTextField.setText(producto.getArticulo()+"  "+producto.getDescripcion()+" "+producto.getColor()+"  "+producto.getTalla());
			pv.cantidadTextField.setText("1");
			pv.cantidadTextField.requestFocus();
			pv.cantidadTextField.selectAll();
			pv.precioTextField.setText(producto.getPrecio().toString());
			pv.existenciasTextField.setText(producto.getExistencias().toString());
			pv.codigoTextField.setText(producto.getCodigo());
			pv.numParteTextField.setText(producto.getNumparte());
			pv.precioPesosTextField.setText(producto.getPrecio().multiply(new BigDecimal(PuntoDeVenta.TIPO_DE_CAMBIO)).toString());
			
		}catch(NoResultException ex){
			JOptionPane.showMessageDialog(null, "No hay Resultados", "Sin Resultados", JOptionPane.WARNING_MESSAGE);
			pv.modeloTextField.setText("");
			pv.cantidadTextField.setText("");
			pv.precioTextField.setText("");
			pv.existenciasTextField.setText("");
			pv.precioTextField.setText("");
			pv.codigoTextField.setText("");
			pv.numParteTextField.setText("");
			pv.precioPesosTextField.setText("");
		}
		
	}
	
}
