package com.artisub.diveshop.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	public static JDesktopPane desktopPane;
	private JMenuBar menuBar;
	private JMenu mnCatalogos,mnPuntoDeVenta;;
	private JMenuItem mntmProductos, mntmTallas, mntmArticulos,mntmMarcas,mntmFormasDePago, mntmPuntoDeVenta;
	private JSeparator separator;
	private JButton pventa;
	private Font font = new Font("Lao UI",Font.PLAIN, 13);
	
	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0,
                  screenSize.width,
                  screenSize.height-40);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(SystemColor.controlHighlight);
//		desktopPane.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
		
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		createMenu();
//		pventa = new JButton("Punto de Venta");
//		pventa.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				PuntoDeVenta puntoDeVenta = new PuntoDeVenta();
//				puntoDeVenta.setVisible(true);
//				desktopPane.add(puntoDeVenta);
//			}
//		});
//		desktopPane.add(pventa);
		
		setContentPane(desktopPane);
	}
	
	private void createMenu(){
		menuBar = new JMenuBar();
		
		setJMenuBar(menuBar);
		
		
		mnPuntoDeVenta = new JMenu("Caja");
		mnPuntoDeVenta.setFont(font);
		menuBar.add(mnPuntoDeVenta);
		
		mnCatalogos = new JMenu("Inventario");
		mnCatalogos.setMnemonic('I');
		mnCatalogos.setFont(font);
		menuBar.add(mnCatalogos);
		
		mntmPuntoDeVenta = new JMenuItem("Punto de Venta");
		mntmPuntoDeVenta.setFont(font);
		mntmPuntoDeVenta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PuntoDeVenta pv = new PuntoDeVenta();
				pv.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				pv.setVisible(true);
				desktopPane.add(pv);
				try {
					pv.setSelected(true);
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		mnPuntoDeVenta.add(mntmPuntoDeVenta);
		
		mntmProductos = new JMenuItem("Productos");
		mntmProductos.setFont(font);
		mntmProductos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ProductoCatalogo pc = new ProductoCatalogo();
				pc.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				pc.setVisible(true);
				desktopPane.add(pc,JDesktopPane.POPUP_LAYER);
				try {
					pc.setSelected(true);
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}
			}
			
		
		});
		mnCatalogos.add(mntmProductos);
		
		separator = new JSeparator();
		mnCatalogos.add(separator);
		
		mntmTallas = new JMenuItem("Catalogo de Tallas");
		mntmTallas.setFont(font);
		mntmTallas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TallaCatalogo tc = new TallaCatalogo();
				tc.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				tc.setVisible(true);
				desktopPane.add(tc,JDesktopPane.POPUP_LAYER);
				tc.requestFocus();
			}
		});
		mnCatalogos.add(mntmTallas);
		
		JMenuItem mntmColores = new JMenuItem("Catalogo de Colores");
		mntmColores.setFont(font);
		mntmColores.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ColorCatalogo cc= new ColorCatalogo();
				cc.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				cc.setVisible(true);
				desktopPane.add(cc,JDesktopPane.POPUP_LAYER);
				cc.requestFocus();
			}
		});
		mnCatalogos.add(mntmColores);
		
		mntmArticulos = new JMenuItem("Catalogo de Articulos");
		mntmArticulos.setFont(font);
		mntmArticulos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArticuloCatalogo ac = new ArticuloCatalogo();
				ac.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				ac.setVisible(true);
				desktopPane.add(ac,JDesktopPane.POPUP_LAYER);
				ac.requestFocus();
			}
		});
		mnCatalogos.add(mntmArticulos);
		
		mntmMarcas = new JMenuItem("Catalogo de Marcas");
		mntmMarcas.setFont(font);
		mntmMarcas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MarcaCatalogo mc = new MarcaCatalogo();
				mc.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				mc.setVisible(true);
				desktopPane.add(mc,JDesktopPane.POPUP_LAYER);
				mc.requestFocus();
			}
		});
		mnCatalogos.add(mntmMarcas);
		
		mntmFormasDePago = new JMenuItem("Formas de Pago");
		mntmFormasDePago.setFont(font);
		mntmFormasDePago.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FormaPagoCatalogo fpc= new FormaPagoCatalogo();
				fpc.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				fpc.setVisible(true);
				desktopPane.add(fpc,JDesktopPane.POPUP_LAYER);
				fpc.requestFocus();
			}
		});
		mnCatalogos.add(mntmFormasDePago);
	}

}
