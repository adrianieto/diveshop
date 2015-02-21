package com.artisub.diveshop.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.artisub.diveshop.controller.dao.ArticuloDao;
import com.artisub.diveshop.controller.dao.ColorsDao;
import com.artisub.diveshop.controller.dao.IDAO;
import com.artisub.diveshop.controller.dao.MarcaDao;
import com.artisub.diveshop.controller.dao.TallaDao;
import com.artisub.diveshop.model.Articulo;
import com.artisub.diveshop.model.Colors;
import com.artisub.diveshop.model.Marca;
import com.artisub.diveshop.model.Talla;

public abstract class PDialogo extends JFrame {

	private static final long serialVersionUID = 139952250200626283L;
	
	private JPanel contentPane;
	protected JTextField modeloTextField;
	protected JTextField partetextField;
	protected JTextField precioTextField;
	protected JTextField existenciasTextField;
	JPanel panel;
	JLabel lblArticulo ;
	JLabel lblArticulo_1;
	JLabel lblMarca;
	JLabel lblColor;
	JLabel lblPrecio;
	JLabel lblExistencias;
	JLabel lblTalla;	
	JLabel lblNumeroDeParte;
	JButton btnGuardar;
	JButton btnCancelar;
	
	protected JComboBox<String> articuloComboBox;
	protected JComboBox<String> tallaComboBox;
	protected JComboBox<String> marcaComboBox;
	protected JComboBox<String> colorComboBox;

	protected IDAO<Articulo> articuloService = new ArticuloDao();
	protected List<Articulo> lista_articulos = articuloService.findAll();
	
	protected IDAO<Talla> tallaService = new TallaDao();
	protected List<Talla> lista_tallas = tallaService.findAll();
	
	protected IDAO<Marca> marcaService = new MarcaDao();
	protected List<Marca> lista_marcas = marcaService.findAll();
	
	protected IDAO<Colors> colorService = new ColorsDao();
	protected List<Colors> lista_colors = colorService.findAll();
	
	
	
	/**
	 * Create the frame.
	 */
	public PDialogo() {
		setTitle("Nuevo Producto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 410, 390);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(17, 10, 360, 300);
//		panel.setBorder(new\ TitledBorder(UIManager.getBorder("TitledBorder.border"), "Nuevo Producto", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBorder(BorderFactory.createTitledBorder("Nuevo Producto"));
		panel.setPreferredSize(new Dimension(360, 300));
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblArticulo = new JLabel("Modelo: ");
		lblArticulo.setBounds(32, 143, 64, 14);
		panel.add(lblArticulo);
		
		articuloComboBox = new JComboBox<String>();
		articuloComboBox.setBounds(32, 107, 134, 25);
		panel.add(articuloComboBox);
		
		modeloTextField = new JTextField();
		modeloTextField.setBounds(32, 162, 134, 25);
		panel.add(modeloTextField);
		modeloTextField.setColumns(5);
		
		lblArticulo_1 = new JLabel("Articulo:");
		lblArticulo_1.setBounds(32, 88, 64, 14);
		panel.add(lblArticulo_1);
		
		lblMarca = new JLabel("Marca:");
		lblMarca.setBounds(32, 28, 46, 14);
		panel.add(lblMarca);
		
		tallaComboBox = new JComboBox<String>();
		tallaComboBox.setBounds(32, 264, 134, 25);
		panel.add(tallaComboBox);
		
		lblColor = new JLabel("Color:");
		lblColor.setBounds(32, 193, 46, 14);
		panel.add(lblColor);
		
		marcaComboBox = new JComboBox<String>();
		marcaComboBox.setBounds(32, 47, 134, 25);
		panel.add(marcaComboBox);
		
		lblTalla = new JLabel("Talla:");
		lblTalla.setBounds(32, 247, 46, 14);
		panel.add(lblTalla);
		
		colorComboBox = new JComboBox<String>();
		colorComboBox.setBounds(32, 211, 134, 25);
		panel.add(colorComboBox);
		
		lblNumeroDeParte = new JLabel("Numero de Parte:");
		lblNumeroDeParte.setBounds(205, 28, 106, 14);
		panel.add(lblNumeroDeParte);
		
		partetextField = new JTextField();
		partetextField.setBounds(205, 47, 124, 25);
		panel.add(partetextField);
		partetextField.setColumns(10);
		
		lblPrecio = new JLabel("Precio dls:");
		lblPrecio.setBounds(205, 88, 80, 14);
		panel.add(lblPrecio);
		
		precioTextField = new JTextField();
		precioTextField.setBounds(205, 107, 124, 25);
		panel.add(precioTextField);
		precioTextField.setColumns(10);
		
		lblExistencias = new JLabel("Existencias:");
		lblExistencias.setBounds(205, 143, 80, 14);
		panel.add(lblExistencias);
		
		existenciasTextField = new JTextField();
		existenciasTextField.setBounds(205, 162, 124, 25);
		panel.add(existenciasTextField);
		existenciasTextField.setColumns(10);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(88, 317, 89, 23);
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveProducto();
				dispose();
			}
		});
		contentPane.add(btnGuardar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(216, 317, 89, 23);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		contentPane.add(btnCancelar);
		
		populateComboBoxes();
	}
	
//	private void saveData(IDAO<?> dao, JComboBox<String> combobox, List<?> lista){
//		
//		Object o = combobox.getSelectedItem();
//		for(Object a : lista){
//			if(a.toString().compareTo(o.toString()){
//				
//			}
//		}
//		dao.create(obj);
//	}
	
	/**
	 * 
	 */
	protected abstract void saveProducto();
	
	protected Talla getSelectedTalla(){
		Talla talla = null;
		for(Talla t : lista_tallas){
			if(t.getNombre().compareTo(tallaComboBox.getSelectedItem().toString()) == 0){
				talla = t;
			}
		}
		return talla;
	}
	
	protected Colors getSelectedColor(){
		Colors color = null;
		for(Colors c : lista_colors){
			if(c.getNombre().compareTo(colorComboBox.getSelectedItem().toString()) == 0){
				color = c;
			}
		}
		return color;
	}
	
	protected Marca getSelectedMarca(){
		Marca marca = null;
		for(Marca m : lista_marcas){
			if(m.getNombre().compareTo(marcaComboBox.getSelectedItem().toString()) == 0){
				marca = m;
			}
		}
		return marca;
	}
	
	protected Articulo getSelectedArticulo(){
		Articulo articulo = null;
		for(Articulo a : lista_articulos){
			if(a.getNombre().compareTo(articuloComboBox.getSelectedItem().toString()) == 0){
				articulo = a;
			}
		}
		return articulo;
	}
	
	protected void populateComboBoxes(){
		
		for(Articulo a : lista_articulos){
			articuloComboBox.addItem(a.getNombre());
		}
		for(Talla t : lista_tallas){
			tallaComboBox.addItem(t.getNombre());
		}
		for(Marca m : lista_marcas){
			marcaComboBox.addItem(m.getNombre());
		}
		for(Colors c : lista_colors){
			colorComboBox.addItem(c.getNombre());
		}
	}
	
}
