package com.artisub.diveshop.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.persistence.NoResultException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import com.artisub.diveshop.controller.dao.IDAO;
import com.artisub.diveshop.controller.dao.ProductoDao;
import com.artisub.diveshop.controller.dao.VentaDao;
import com.artisub.diveshop.model.Producto;
import com.artisub.diveshop.model.Venta;



public class PuntoDeVenta extends JInternalFrame {
	
	static final Double TIPO_DE_CAMBIO = 16.0; 
	
	IDAO<Producto> productoService = new ProductoDao();
	IDAO<Venta> ventaService = new VentaDao();
	List<Venta> lista_ventas = new ArrayList<Venta>();
	Venta venta = null;
	Producto producto = null;
	
	private static final long serialVersionUID = -7182516204320202906L;
	private JPanel panel,contentPane,northPanel,northLeftPanel,northRightPanel,midPanel,sumatoriaPanel,labelPanel;
//	private JMenuBar menuBar;
//	private JMenu mnCatalogos;
//	private JMenuItem mntmProductos, mntmTallas, mntmArticulos,mntmMarcas,mntmFormasDePago;
	public JTable table;
	private JScrollPane scrollPane;
	protected JLabel subTotalLbl, descuentoLbl, pagadoLbl, cambioLbl, totalLbl,lblParte,lblModelo,lblCantidad,
					lblPrecioUnitario,lblExistencias, lblPrecioUusd,lblFecha,lblVenta,lblHora,lblSubTotalT,
					lblDescuentoT,lblPagadoT,lblCambioT,lblTotalT;
	private JLabel lblParte_1;
	
	protected JButton btnAgregar,cobrarBtn,btnNuevaVenta,cancelarVentaBtn;
	private JRadioButton rdbtnTicket,rdbtnFactura;
	private Box horizontalBox;
	
	public DefaultTableModel model = new DefaultTableModel();
	protected JTextField numParteTextField, modeloTextField, cantidadTextField,precioTextField;
	protected JTextField fechaTextField, descuentoTextField,existenciasTextField,ventaNumTextField, precioPesosTextField,codigoTextField;
	
	
	
	protected DecimalFormat decimalFormat = new DecimalFormat("#,###,###,##0.00");
	
	//ACUMULADORES
	protected Double subTotalAC=0.0, descuento=0.0, pagado=0.0, cambio=0.0, totalAC=0.0;
	
	PuntoDeVenta self = this;
	
	/**
	 * Create the frame.
	 */
	public PuntoDeVenta() {
		super("Punto de Venta", true, true, true,true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(115, 0, 1120,668);
		
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.scrollbar);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(15, 15));
		
		northPanel = new JPanel();
		northPanel.setBackground(SystemColor.scrollbar);
		contentPane.add(northPanel, BorderLayout.NORTH);
		
		northLeftPanel = new JPanel();
		northLeftPanel.setBackground(SystemColor.control);
		northLeftPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		northLeftPanel.setPreferredSize(new Dimension(630, 150));
		northPanel.add(northLeftPanel);
		northLeftPanel.setLayout(null);
		
		lblParte = new JLabel("Codigo:");
		lblParte.setForeground(Color.DARK_GRAY);
		lblParte.setFont(new Font("Lao UI", Font.BOLD, 13));
		lblParte.setHorizontalAlignment(SwingConstants.RIGHT);
		lblParte.setBounds(10, 17, 63, 22);
		northLeftPanel.add(lblParte);
		
		
		numParteTextField = new JTextField();
		numParteTextField.setFont(new Font("Lao UI", Font.PLAIN, 12));
		numParteTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		numParteTextField.setBounds(83, 55, 109, 28);
		numParteTextField.setColumns(10);
		numParteTextField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					numParteTextFieldSearch();            // realiza la busqueda al presionar la tecla Enter
				}
				
			}
		});
		northLeftPanel.add(numParteTextField);
		
		
		lblModelo = new JLabel("Descripcion:");
		lblModelo.setForeground(Color.DARK_GRAY);
		lblModelo.setFont(new Font("Lao UI", Font.BOLD, 13));
		lblModelo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblModelo.setBounds(187, 17, 89, 14);
		northLeftPanel.add(lblModelo);
		
		modeloTextField = new JTextField();
		modeloTextField.setFont(new Font("Lao UI", Font.PLAIN, 12));
		modeloTextField.setBounds(281, 14, 339, 28);
		northLeftPanel.add(modeloTextField);
		modeloTextField.setColumns(10);
		
		lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setForeground(Color.DARK_GRAY);
		lblCantidad.setFont(new Font("Lao UI", Font.BOLD, 13));
		lblCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCantidad.setBounds(10, 101, 63, 14);
		northLeftPanel.add(lblCantidad);
		
		cantidadTextField = new JTextField();
		cantidadTextField.setFont(new Font("Lao UI", Font.BOLD, 12));
		cantidadTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		cantidadTextField.setColumns(10);
		cantidadTextField.setBounds(83, 94, 109, 28);
		cantidadTextField.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent ex){
				if(ex.getKeyCode() == KeyEvent.VK_ENTER ){
					descuento = Double.parseDouble(descuentoTextField.getText());
					descuentoTextField.setText(descuento.toString());
					
					venta = new Venta();
					venta.setCantidad(Short.parseShort(cantidadTextField.getText()));
					venta.setComentarios("");
					venta.setDescuento(descuento);
					venta.setFeha(new Date());
					venta.setNumventa(Integer.parseInt(ventaNumTextField.getText()));
					venta.setProducto(producto);
					venta.setSubtotal(producto.getPrecio().multiply(new BigDecimal(TIPO_DE_CAMBIO)));
					venta.setTotal(Double.parseDouble((producto.getPrecio().multiply(new BigDecimal(TIPO_DE_CAMBIO)).multiply(new BigDecimal(cantidadTextField.getText()))).toString()));
					lista_ventas.add(venta);
	
					
					subTotalAC+= Double.parseDouble((producto.getPrecio().toString()))*Integer.parseInt(cantidadTextField.getText())*TIPO_DE_CAMBIO;
					descuento += (descuento/100)*subTotalAC;
					totalAC = subTotalAC - descuento;
					subTotalLbl.setText(decimalFormat.format(subTotalAC));
					totalLbl.setText(decimalFormat.format(totalAC));
					if(descuento != 0.0)
						descuentoLbl.setText(decimalFormat.format(descuento));
					
					model.insertRow(model.getRowCount(), new Object[] { venta.getProducto().getNumparte(), venta.getProducto().getArticulo(),
																		venta.getProducto().getDescripcion(),venta.getProducto().getColor(), venta.getProducto().getTalla(),
																		venta.getCantidad(), descuentoTextField.getText(),venta.getSubtotal(), venta.getTotal()});
					Integer nuevas_existencias = Integer.parseInt(existenciasTextField.getText()) - Integer.parseInt(cantidadTextField.getText());
					existenciasTextField.setText(nuevas_existencias.toString());
					codigoTextField.requestFocus();
					codigoTextField.selectAll();
				}
			}
		});
		northLeftPanel.add(cantidadTextField);
		
		lblPrecioUnitario = new JLabel("Precio U. (MXN): ");
		lblPrecioUnitario.setForeground(Color.DARK_GRAY);
		lblPrecioUnitario.setFont(new Font("Lao UI", Font.BOLD, 13));
		lblPrecioUnitario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrecioUnitario.setBounds(205, 62, 109, 14);
		northLeftPanel.add(lblPrecioUnitario);
		
		precioTextField = new JTextField();
		precioTextField.setFont(new Font("Lao UI", Font.PLAIN, 12));
		precioTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		precioTextField.setEditable(false);
		precioTextField.setColumns(10);
		precioTextField.setBounds(531, 55, 89, 28);
		northLeftPanel.add(precioTextField);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(476, 94, 89, 28);
		northLeftPanel.add(btnAgregar);
		
		lblExistencias = new JLabel("Existencias: ");
		lblExistencias.setForeground(Color.DARK_GRAY);
		lblExistencias.setFont(new Font("Lao UI", Font.BOLD, 13));
		lblExistencias.setHorizontalAlignment(SwingConstants.RIGHT);
		lblExistencias.setBounds(225, 101, 89, 14);
		northLeftPanel.add(lblExistencias);
		
		existenciasTextField = new JTextField();
		existenciasTextField.setFont(new Font("Lao UI", Font.PLAIN, 12));
		existenciasTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		existenciasTextField.setEditable(false);
		existenciasTextField.setColumns(10);
		existenciasTextField.setBounds(316, 94, 52, 28);
		northLeftPanel.add(existenciasTextField);
		
		lblPrecioUusd = new JLabel("Precio U. (USD): ");
		lblPrecioUusd.setForeground(Color.DARK_GRAY);
		lblPrecioUusd.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrecioUusd.setFont(new Font("Lao UI", Font.BOLD, 13));
		lblPrecioUusd.setBounds(422, 62, 109, 14);
		northLeftPanel.add(lblPrecioUusd);
		
		precioPesosTextField = new JTextField();
		precioPesosTextField.setFont(new Font("Lao UI", Font.PLAIN, 12));
		precioPesosTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		precioPesosTextField.setEditable(false);
		precioPesosTextField.setColumns(10);
		precioPesosTextField.setBounds(316, 55, 96, 28);
		northLeftPanel.add(precioPesosTextField);
		
		lblParte_1 = new JLabel("# Parte:");
		lblParte_1.setForeground(Color.DARK_GRAY);
		lblParte_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblParte_1.setFont(new Font("Lao UI", Font.BOLD, 13));
		lblParte_1.setBounds(10, 62, 63, 14);
		northLeftPanel.add(lblParte_1);
		
		codigoTextField = new JTextField();
		codigoTextField.setFont(new Font("Lao UI", Font.PLAIN, 12));
		codigoTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		codigoTextField.setColumns(10);
		codigoTextField.setBounds(83, 15, 109, 28);
		codigoTextField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					codigoTextFieldSearch();
				}
			}
		});
		northLeftPanel.add(codigoTextField);
		
		northRightPanel = new JPanel();
		northRightPanel.setBackground(SystemColor.control);
		northRightPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		northRightPanel.setPreferredSize(new Dimension(450, 150));
		northPanel.add(northRightPanel);
		northRightPanel.setLayout(null);
		
		lblFecha = new JLabel("Fecha:");
		lblFecha.setForeground(Color.DARK_GRAY);
		lblFecha.setFont(new Font("Lao UI", Font.BOLD, 13));
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFecha.setBounds(245, 28, 46, 14);
		northRightPanel.add(lblFecha);
		
		lblVenta = new JLabel("Venta Numero:");
		lblVenta.setForeground(Color.DARK_GRAY);
		lblVenta.setFont(new Font("Lao UI", Font.BOLD, 13));
		lblVenta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVenta.setBounds(10, 28, 106, 14);
		northRightPanel.add(lblVenta);
		
		lblHora = new JLabel("Descuento:");
		lblHora.setForeground(Color.DARK_GRAY);
		lblHora.setFont(new Font("Lao UI", Font.BOLD, 13));
		lblHora.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHora.setBounds(0, 89, 116, 14);
		northRightPanel.add(lblHora);
		
		fechaTextField = new JTextField();
		fechaTextField.setFont(new Font("Lao UI", Font.BOLD, 12));
		fechaTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		fechaTextField.setColumns(10);
		fechaTextField.setBounds(301, 22, 139, 28);
		Date fecha = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd / MM / yyyy");
		fechaTextField.setText(dateFormat.format(fecha));
		northRightPanel.add(fechaTextField);
		
		
		descuentoTextField = new JTextField("0.00");
		descuentoTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		descuentoTextField.setToolTipText("Define un descuento global.");
		descuentoTextField.setColumns(10);
		descuentoTextField.setBounds(126, 83, 68, 28);
		northRightPanel.add(descuentoTextField);
		
		rdbtnTicket = new JRadioButton("Ticket");
		rdbtnTicket.setForeground(Color.DARK_GRAY);
		rdbtnTicket.setFont(new Font("Lao UI", Font.BOLD, 13));
		rdbtnTicket.setBounds(275, 85, 76, 23);
		northRightPanel.add(rdbtnTicket);
		
		rdbtnFactura = new JRadioButton("Factura");
		rdbtnFactura.setForeground(Color.DARK_GRAY);
		rdbtnFactura.setFont(new Font("Lao UI", Font.BOLD, 13));
		rdbtnFactura.setBounds(353, 85, 91, 23);
		northRightPanel.add(rdbtnFactura);
		
		ventaNumTextField = new JTextField();
		ventaNumTextField.setFont(new Font("Lao UI", Font.BOLD, 12));
		ventaNumTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		ventaNumTextField.setColumns(10);
		ventaNumTextField.setBounds(126, 22, 109, 28);
		setNumeroVenta();
		northRightPanel.add(ventaNumTextField);
		
		JLabel label = new JLabel("%");
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("Lao UI", Font.BOLD, 16));
		label.setBounds(194, 84, 41, 21);
		northRightPanel.add(label);
		
		midPanel = new JPanel();
		midPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(midPanel, BorderLayout.CENTER);
		midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));
		
		
		
		model.addColumn("# Parte");
		model.addColumn("Articulo");
		model.addColumn("Modelo");
		model.addColumn("Color");
		model.addColumn("Talla");
		model.addColumn("Cantidad");
		model.addColumn("Desc.");
		model.addColumn("Precio U.(MXN)");
		model.addColumn("Importe");
		
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(290, 120));
		table.setFillsViewportHeight(true);
		table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
		
		scrollPane = new JScrollPane(table);
		midPanel.add(scrollPane);
		
		sumatoriaPanel = new JPanel();
		sumatoriaPanel.setBackground(SystemColor.control);
		midPanel.add(sumatoriaPanel);
		sumatoriaPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
	
		
		labelPanel = new JPanel();
		labelPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		labelPanel.setBackground(SystemColor.scrollbar);
		labelPanel.setLayout(null);
		labelPanel.setPreferredSize(new Dimension(400, 200));
		
		sumatoriaPanel.add(labelPanel);
		
		lblSubTotalT = new JLabel("Sub Total:   $ ");
		lblSubTotalT.setForeground(Color.DARK_GRAY);
		lblSubTotalT.setFont(new Font("Lao UI", Font.BOLD, 22));
		lblSubTotalT.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSubTotalT.setBounds(31, 11, 167, 36);
		labelPanel.add(lblSubTotalT);
		
		lblDescuentoT = new JLabel("Descuento:   $ ");
		lblDescuentoT.setForeground(Color.DARK_GRAY);
		lblDescuentoT.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescuentoT.setFont(new Font("Lao UI", Font.BOLD, 22));
		lblDescuentoT.setBounds(31, 40, 167, 36);
		labelPanel.add(lblDescuentoT);
		
		lblPagadoT = new JLabel("Pagado:   $ ");
		lblPagadoT.setForeground(Color.DARK_GRAY);
		lblPagadoT.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPagadoT.setFont(new Font("Lao UI", Font.BOLD, 22));
		lblPagadoT.setBounds(31, 70, 167, 36);
		labelPanel.add(lblPagadoT);
		
		lblCambioT = new JLabel("Cambio:   $ ");
		lblCambioT.setForeground(Color.DARK_GRAY);
		lblCambioT.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCambioT.setFont(new Font("Lao UI", Font.BOLD, 22));
		lblCambioT.setBounds(31, 100, 167, 36);
		labelPanel.add(lblCambioT);
		
		lblTotalT = new JLabel("Total:   $ ");
		lblTotalT.setForeground(Color.BLUE);
		lblTotalT.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalT.setFont(new Font("Lao UI", Font.BOLD, 34));
		lblTotalT.setBounds(41, 142, 167, 36);
		labelPanel.add(lblTotalT);
		
		subTotalLbl = new JLabel("0.00");
		subTotalLbl.setForeground(Color.DARK_GRAY);
		subTotalLbl.setFont(new Font("Lao UI", Font.BOLD, 22));
		subTotalLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		subTotalLbl.setBounds(218, 15, 158, 28);
		labelPanel.add(subTotalLbl);
		
		descuentoLbl = new JLabel("0.00");
		descuentoLbl.setForeground(Color.DARK_GRAY);
		descuentoLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		descuentoLbl.setFont(new Font("Lao UI", Font.BOLD, 22));
		descuentoLbl.setBounds(218, 44, 158, 28);
		labelPanel.add(descuentoLbl);
		
		pagadoLbl = new JLabel("0.00");
		pagadoLbl.setForeground(Color.DARK_GRAY);
		pagadoLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		pagadoLbl.setFont(new Font("Lao UI", Font.BOLD, 22));
		pagadoLbl.setBounds(218, 74, 158, 28);
		labelPanel.add(pagadoLbl);
		
		cambioLbl = new JLabel("0.00");
		cambioLbl.setForeground(Color.DARK_GRAY);
		cambioLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		cambioLbl.setFont(new Font("Lao UI", Font.BOLD, 22));
		cambioLbl.setBounds(218, 104, 158, 28);
		labelPanel.add(cambioLbl);
		
		totalLbl = new JLabel("0.00");
		totalLbl.setForeground(Color.BLUE);
		totalLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		totalLbl.setFont(new Font("Lao UI", Font.BOLD, 35));
		totalLbl.setBounds(203, 141, 187, 36);
		labelPanel.add(totalLbl);
		
		panel = new JPanel();
		panel.setBackground(SystemColor.control);
		midPanel.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		
		cobrarBtn = new JButton("Vender");
		cobrarBtn.setFont(new Font("Lao UI", Font.BOLD, 11));
		cobrarBtn.setPreferredSize(new Dimension(110, 55));
		cobrarBtn.setBackground(Color.WHITE);
		cobrarBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		cobrarBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		cobrarBtn.setIcon(new ImageIcon("src/main/resources/Icons/vender.png"));
		cobrarBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CobroFrame(self).setVisible(true);
				
			}
		});
		cobrarBtn.requestFocus();
		
		btnNuevaVenta = new JButton("Nueva Venta");
		btnNuevaVenta.setFont(new Font("Lao UI", Font.BOLD, 11));
		btnNuevaVenta.setPreferredSize(new Dimension(110, 55));
		btnNuevaVenta.setBackground(Color.WHITE);
		btnNuevaVenta.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNuevaVenta.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNuevaVenta.setIcon(new ImageIcon("src/main/resources/Icons/nueva_venta.png"));
		btnNuevaVenta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nuevaVentaClear();
			}
		});
		btnNuevaVenta.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ex){
				if(ex.getKeyCode() == KeyEvent.VK_ENTER ){
					nuevaVentaClear();
				}
			}
		});
		
		panel.add(btnNuevaVenta);
		
		cancelarVentaBtn = new JButton("Cancelar Venta");
		cancelarVentaBtn.setFont(new Font("Lao UI", Font.BOLD, 11));
		cancelarVentaBtn.setPreferredSize(new Dimension(110, 55));
		cancelarVentaBtn.setBackground(Color.WHITE);
		cancelarVentaBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		cancelarVentaBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		cancelarVentaBtn.setIcon(new ImageIcon("src/main/resources/Icons/cancelar_venta.png"));
		panel.add(cancelarVentaBtn);
		panel.add(cobrarBtn);
		
		
		
//		horizontalBox = Box.createHorizontalBox();
//		contentPane.add(horizontalBox, BorderLayout.SOUTH);
	}
	
	private void numParteTextFieldSearch(){
		String numparte = numParteTextField.getText();
		Properties p = new Properties();
		p.put("numparte", numparte);
		
		try{
			producto = productoService.getByQuery("Producto.findNumParte",p);
			modeloTextField.setText(producto.getArticulo()+"  "+producto.getDescripcion()+" "+producto.getColor()+"  "+producto.getTalla());
			cantidadTextField.setText("1");
			cantidadTextField.requestFocus();
			cantidadTextField.selectAll();
			precioTextField.setText(producto.getPrecio().toString());
			existenciasTextField.setText(producto.getExistencias().toString());
			precioPesosTextField.setText(producto.getPrecio().multiply(new BigDecimal(TIPO_DE_CAMBIO)).toString());
			codigoTextField.setText(producto.getCodigo());
		}catch(NoResultException ex){
			JOptionPane.showMessageDialog(null, "No hay Resultados", "Sin Resultados", JOptionPane.WARNING_MESSAGE);
			modeloTextField.setText("");
			cantidadTextField.setText("");
			precioTextField.setText("");
			existenciasTextField.setText("");
			precioTextField.setText("");
			codigoTextField.setText("");
			numParteTextField.setText("");
			precioPesosTextField.setText("");
		}
	}
	
	private void codigoTextFieldSearch(){
		String codigo = codigoTextField.getText();
		Properties p = new Properties();
		p.put("codigo", codigo);
		
		try{
			producto = productoService.getByQuery("Producto.findCodigo",p);
			modeloTextField.setText(producto.getArticulo()+"  "+producto.getDescripcion()+" "+producto.getColor()+"  "+producto.getTalla());
			cantidadTextField.setText("1");
			cantidadTextField.requestFocus();
			cantidadTextField.selectAll();
			precioTextField.setText(producto.getPrecio().toString());
			existenciasTextField.setText(producto.getExistencias().toString());
			precioPesosTextField.setText(producto.getPrecio().multiply(new BigDecimal(TIPO_DE_CAMBIO)).toString());
			numParteTextField.setText(producto.getNumparte());
		}catch(NoResultException ex){
			JOptionPane.showMessageDialog(null, "No hay Resultados", "Sin Resultados", JOptionPane.WARNING_MESSAGE);
			modeloTextField.setText("");
			cantidadTextField.setText("");
			precioTextField.setText("");
			existenciasTextField.setText("");
			precioTextField.setText("");
			codigoTextField.setText("");
			numParteTextField.setText("");
			precioPesosTextField.setText("");
		}	
	}
	
	private void setNumeroVenta(){
		Properties p = new Properties();
		try{
			venta = ventaService.getByQuery("Producto.findLast",p);
			Integer numventa = venta.getNumventa()+1;
			ventaNumTextField.setText(numventa.toString());
		}catch(NoResultException e){
			ventaNumTextField.setText("1");
		}
		
		
	}
	
	private void nuevaVentaClear(){
		descuentoTextField.setText("0.00");
		modeloTextField.setText("");
		cantidadTextField.setText("");
		precioTextField.setText("");
		existenciasTextField.setText("");
		precioTextField.setText("");
		codigoTextField.setText("");
		numParteTextField.setText("");
		precioPesosTextField.setText("");
		
		while(model.getRowCount() > 0){
			model.removeRow(0);
		}
		
		
	}
}
