package com.artisub.diveshop.view.pos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.artisub.diveshop.controller.dao.FormapagoDao;
import com.artisub.diveshop.controller.dao.IDAO;
import com.artisub.diveshop.controller.dao.ProductoDao;
import com.artisub.diveshop.controller.dao.VentaDao;
import com.artisub.diveshop.model.Formapago;
import com.artisub.diveshop.model.Producto;
import com.artisub.diveshop.model.Venta;

public class CobroFrame extends JFrame {

	private static final long serialVersionUID = -7264286677556811520L;
	
	IDAO<Venta> ventaService = new VentaDao();
	IDAO<Producto> productoService = new ProductoDao();
	IDAO<Formapago> formaPagoService = new FormapagoDao();
	
	List<Formapago> lista_formapago = formaPagoService.findAll();
	
	//private Venta v = null;

	private JPanel contentPane ,panel;
	private JTextField pagoTextField;
	private JComboBox<String> formPagocomboBox;
	
	PuntoDeVenta puntoVenta;

	/**
	 * Create the frame.
	 */
	public CobroFrame(PuntoDeVenta puntoVenta) {
		this.puntoVenta = puntoVenta;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(320, 320);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		panel.setBackground(SystemColor.control);
		panel.setBorder(new TitledBorder(null, "Cobro", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblMontoRecibido = new JLabel("Monto Recibido:");
		lblMontoRecibido.setForeground(Color.DARK_GRAY);
		lblMontoRecibido.setFont(new Font("Lao UI", Font.BOLD, 13));
		lblMontoRecibido.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMontoRecibido.setBounds(10, 50, 113, 14);
		panel.add(lblMontoRecibido);
		
		pagoTextField = new JTextField();
		pagoTextField.setForeground(Color.RED);
		pagoTextField.setFont(new Font("Lao UI", Font.BOLD, 13));
		pagoTextField.setBounds(147, 42, 108, 30);
		panel.add(pagoTextField);
		pagoTextField.setColumns(10);
		
		JLabel lblFormaDePago = new JLabel("Forma de Pago:");
		lblFormaDePago.setForeground(Color.DARK_GRAY);
		lblFormaDePago.setFont(new Font("Lao UI", Font.BOLD, 13));
		lblFormaDePago.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFormaDePago.setBounds(10, 87, 115, 20);
		panel.add(lblFormaDePago);
		
		formPagocomboBox = new JComboBox<String>();
		formPagocomboBox.setFont(new Font("Lao UI", Font.PLAIN, 12));
		formPagocomboBox.setBounds(147, 81, 108, 26);
		for(Formapago p : lista_formapago){
			formPagocomboBox.addItem(p.getFormapago());
		}
		
		panel.add(formPagocomboBox);
		
		JLabel labelMaterCard = new JLabel("");
		labelMaterCard.setHorizontalAlignment(SwingConstants.CENTER);
		labelMaterCard.setIcon(new ImageIcon("src/main/resources/Icons/mastercard.png"));
		labelMaterCard.setBounds(139, 118, 59, 37);
		panel.add(labelMaterCard);
		
		JLabel labelVisa = new JLabel("");
		labelVisa.setIcon(new ImageIcon("src/main/resources/Icons/visa.png"));
		labelVisa.setHorizontalAlignment(SwingConstants.CENTER);
		labelVisa.setBounds(196, 118, 59, 37);
		panel.add(labelVisa);
		
		JLabel label = new JLabel("$");
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("Lao UI", Font.BOLD, 15));
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(120, 42, 24, 23);
		panel.add(label);
		
		JButton btnCobrar = new JButton("Cobrar");
		btnCobrar.setForeground(Color.DARK_GRAY);
		btnCobrar.setBounds(165, 185, 90, 65);
		btnCobrar.setFont(new Font("Lao UI", Font.BOLD, 12));
		btnCobrar.setBackground(Color.WHITE);
		btnCobrar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCobrar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCobrar.setIcon(new ImageIcon("src/main/resources/Icons/cobro.png"));
		btnCobrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cobrar();
				
			}
		});
		btnCobrar.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ex){
				if(ex.getKeyCode() == KeyEvent.VK_ENTER ){
					cobrar();
				}
			}
		});
		panel.add(btnCobrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(Color.DARK_GRAY);
		btnCancelar.setBounds(37, 185, 90, 65);
		btnCancelar.setFont(new Font("Lao UI", Font.BOLD, 12));
		btnCancelar.setBackground(Color.WHITE);
		btnCancelar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCancelar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCancelar.setIcon(new ImageIcon("src/main/resources/Icons/cancelar.png"));
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				close();
				
			}
		});
		panel.add(btnCancelar);
	}
	
	private void cobrar(){
		Double pago = Double.parseDouble(pagoTextField.getText());
		Double cambio = pago - puntoVenta.totalAC;
		puntoVenta.pagadoLbl.setText(puntoVenta.decimalFormat.format(pago));
		puntoVenta.cambioLbl.setText(puntoVenta.decimalFormat.format(cambio));
		persistirVenta();
		Producto prod = null;
		for(Venta v : puntoVenta.lista_ventas){
			prod = productoService.findById(v.getProducto().getId());
			prod.setExistencias(prod.getExistencias()-(Integer.parseInt(puntoVenta.cantidadTextField.getText())));
			
			if(prod.getExistencias() > 0)
				productoService.update(prod);
		}
		puntoVenta.lista_ventas.clear();
		puntoVenta.btnNuevaVenta.requestFocus();
		close();
	}
	
	private void persistirVenta(){
		Formapago fp = null;
		for(Formapago f : lista_formapago){
			if(f.getFormapago().compareTo(formPagocomboBox.getSelectedItem().toString()) == 0){
				fp =f;
			}
		}
			
		//Recorre lista de ventas y guarda una a una cada venta dentro de la lista
		for(Venta v : puntoVenta.lista_ventas){
			v.setFormapago(fp);
			ventaService.create(v);
		}
		
	}
	
	private void close(){
		this.dispose();
	}
	

}
