package com.artisub.diveshop.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public abstract class Catalogo extends JFrame{

	private static final long serialVersionUID = 1L;
	
	protected Container container;
	protected JPanel productosPanel, menuButtonPanel, cancelPanel;
	protected JButton nuevo, modificar, borrar, cancelar, aceptar;
	protected JScrollPane scrollPane;
	protected JTable table;
	protected DefaultTableModel model;

	protected String paneltitle;
	
	public Catalogo(String titleframe, String paneltitle) {
		super(titleframe);
		this.paneltitle = paneltitle;
		setSize(340, 320);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		init();
		table.getColumnModel().getColumn(0).setPreferredWidth(1);
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
		
//		model.addColumn("ID");
		
		
		
		scrollPane = new JScrollPane(table);
		
		productosPanel = new JPanel();
		productosPanel.setLayout(new BoxLayout(productosPanel, BoxLayout.Y_AXIS));
//		formasdepagoPanel.setBorder(new TitledBorder(null, paneltitle, TitledBorder.LEADING, TitledBorder.TOP, null, Color.DARK_GRAY));
		productosPanel.setBorder(BorderFactory.createTitledBorder(paneltitle));
		productosPanel.add(scrollPane);
		productosPanel.add(Box.createVerticalStrut(10));
		
		menuButtonPanel = new JPanel(new FlowLayout());
		nuevo = new JButton("Nuevo");
		nuevo.setBackground(Color.WHITE);
		nuevo.setPreferredSize(new Dimension(100, 60));
		nuevo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addItem();
				table.repaint();	
			}
		});
		
		modificar = new JButton("Modificar");
		modificar.setBackground(Color.WHITE);
		modificar.setPreferredSize(new Dimension(100,60));
		modificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editItem();
				table.repaint();
			}
		});
		
		borrar = new JButton("Borrar");
		borrar.setBackground(Color.WHITE);
		borrar.setPreferredSize(new Dimension(100,60));
		borrar.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteItem();
				table.repaint();
			}
		});
		
		cancelar = new JButton("Cancelar");
		cancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			
			}
		});
		
		aceptar = new JButton("Aceptar");
		aceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		menuButtonPanel.add(nuevo);
		menuButtonPanel.add(modificar);
		menuButtonPanel.add(borrar);
		
		cancelPanel = new JPanel(new FlowLayout());
		cancelPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cancelPanel.add(cancelar);
		cancelPanel.add(aceptar);
		
		
		container.add(menuButtonPanel, BorderLayout.PAGE_START);
		container.add(productosPanel, BorderLayout.CENTER);
		container.add(cancelPanel, BorderLayout.PAGE_END);
		
	}
	
	protected abstract void addItem();
	
	protected abstract void editItem();
	
	protected abstract void deleteItem();
	
	protected abstract void setTableData();
}
