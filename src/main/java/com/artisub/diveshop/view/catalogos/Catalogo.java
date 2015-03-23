package com.artisub.diveshop.view.catalogos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;


public abstract class Catalogo extends JInternalFrame{

	private static final long serialVersionUID = 1L;
	
	protected Container container;
	protected JPanel productosPanel, menuActionPanel, cancelPanel, menuPanel;
	protected JButton nuevo, editar, borrar, cancelar, aceptar, cMarcas, cArticulos, cTallas, cColores;
	protected JScrollPane scrollPane;
	protected JTable table;
	protected DefaultTableModel model;

	protected String paneltitle;
	
	public Catalogo(String titleframe, String paneltitle) {
		super(titleframe, true, true, true,true);
		this.paneltitle = paneltitle;
		setSize(340, 475);
//		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		init();
	}

	protected  void init(){
		container = getContentPane();
		container.setLayout(new BorderLayout());
		
		
		
		model = new DefaultTableModel();
		model.addColumn("Nombre");
		
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(290, 120));
		table.setFillsViewportHeight(true);
		table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
		
		
//		table.setShowGrid(false);
	    table.setShowVerticalLines(true);
	    table.addMouseListener(new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e){
	    		if(e.getClickCount() == 2){
	    				table.getSelectionModel().clearSelection();
	    		}
	    	}
		});
		

	    TableCellRenderer rendererFromHeader = table.getTableHeader().getDefaultRenderer();
	    JLabel headerLabel = (JLabel) rendererFromHeader;
	    headerLabel.setHorizontalAlignment(JLabel.CENTER);
		
		
		scrollPane = new JScrollPane(table);
		
		
		
		productosPanel = new JPanel();
		productosPanel.setBackground(SystemColor.control);
		productosPanel.setLayout(new BoxLayout(productosPanel, BoxLayout.Y_AXIS));
//		formasdepagoPanel.setBorder(new TitledBorder(null, paneltitle, TitledBorder.LEADING, TitledBorder.TOP, null, Color.DARK_GRAY));
		productosPanel.setBorder(BorderFactory.createTitledBorder(paneltitle));
	
		productosPanel.add(scrollPane);
		productosPanel.add(Box.createVerticalStrut(10));
		
		menuActionPanel = new JPanel(new FlowLayout());
		menuActionPanel.setBackground(Color.WHITE);
		
		nuevo = new JButton("Nuevo");
		nuevo.setFont(new Font("Lao UI",Font.BOLD,12));
//		nuevo.setBackground(Color.WHITE);
		nuevo.setPreferredSize(new Dimension(100, 80));
		nuevo.setVerticalTextPosition(SwingConstants.BOTTOM);
		nuevo.setHorizontalTextPosition(SwingConstants.CENTER);
		nuevo.setIcon(new ImageIcon("src/main/resources/Icons/nuevo.png"));
		nuevo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addItem();
				table.repaint();	
			}
		});
		
		editar = new JButton("Editar");
		editar.setFont(new Font("Lao UI",Font.BOLD,12));
//		editar.setBackground(Color.WHITE);
		editar.setPreferredSize(new Dimension(100,80));
		editar.setVerticalTextPosition(SwingConstants.BOTTOM);
		editar.setHorizontalTextPosition(SwingConstants.CENTER);
		editar.setIcon(new ImageIcon("src/main/resources/Icons/editar.png"));
		editar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editItem();
				table.repaint();
			}
		});
		
		borrar = new JButton("Borrar");
		borrar.setFont(new Font("Lao UI",Font.BOLD,12));
//		borrar.setBackground(Color.WHITE);
		borrar.setPreferredSize(new Dimension(100,80));
		borrar.setVerticalTextPosition(SwingConstants.BOTTOM);
		borrar.setHorizontalTextPosition(SwingConstants.CENTER);
		borrar.setIcon(new ImageIcon("src/main/resources/Icons/borrar.png"));
		borrar.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteItem();
				table.repaint();
			}
		});
		
		cancelar = new JButton("Cancelar");
		cancelar.setFont(new Font("Lao UI",Font.BOLD,12));
//		cancelar.setBackground(Color.WHITE);
		cancelar.setVerticalTextPosition(SwingConstants.BOTTOM);
		cancelar.setHorizontalTextPosition(SwingConstants.CENTER);
		cancelar.setIcon(new ImageIcon("src/main/resources/Icons/cancelar.png"));
		cancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			
			}
		});
		
		aceptar = new JButton("Aceptar");
		aceptar.setFont(new Font("Lao UI",Font.BOLD,12));
//		aceptar.setBackground(Color.WHITE);
		aceptar.setVerticalTextPosition(SwingConstants.BOTTOM);
		aceptar.setHorizontalTextPosition(SwingConstants.CENTER);
		aceptar.setIcon(new ImageIcon("src/main/resources/Icons/aceptar.png"));
		aceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		
		
		menuActionPanel.add(nuevo);
		menuActionPanel.add(editar);
		menuActionPanel.add(borrar);
		
		cancelPanel = new JPanel(new FlowLayout());
		cancelPanel.setBackground(SystemColor.control);
		cancelPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cancelPanel.add(cancelar);
		cancelPanel.add(aceptar);
		
		menuPanel = new JPanel();
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
		cMarcas = new JButton("Marcas");
		cArticulos = new JButton("Articulos");
		
		menuPanel.add(cMarcas);
		cMarcas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		menuPanel.add(cArticulos);
		
		container.add(menuActionPanel, BorderLayout.PAGE_START);
//		container.add(menuPanel, BorderLayout.LINE_START);
		container.add(productosPanel, BorderLayout.CENTER);
		container.add(cancelPanel, BorderLayout.PAGE_END);
		
	}
	
	protected abstract void addItem();
	
	protected abstract void editItem();
	
	protected abstract void deleteItem();
	
	protected abstract void setTableData();
}
