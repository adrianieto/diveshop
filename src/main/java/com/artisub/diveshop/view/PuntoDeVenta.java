package com.artisub.diveshop.view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PuntoDeVenta extends JFrame {
	
	private static final long serialVersionUID = -1115902474227481909L;
	
	Container container;
	JPanel panel;
	JButton prodBtn,marcaBtn, articuloBtn, tallaBtn, colorBtn;
	
	public PuntoDeVenta(){
		setSize(20,250);
		setLocation(50,75);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		init();
	}

	private void init(){
		container = getContentPane();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		
		prodBtn = new JButton("Productos");
		prodBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ProductoCatalogo().setVisible(true);
			}
		});
		marcaBtn = new JButton("Marcas");
		marcaBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MarcaCatalogo().setVisible(true);
			}
		});
		articuloBtn = new JButton("Articulos");
		articuloBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ArticuloCatalogo().setVisible(true);
				
			}
		});
		tallaBtn = new JButton("Tallas");
		tallaBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new TallaCatalogo().setVisible(true);
			}
		});
		colorBtn = new JButton("Colores");
		colorBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ColorCatalogo().setVisible(true);
			}
		});
		
		container.add(prodBtn);
		container.add(marcaBtn);
		container.add(articuloBtn);
		container.add(tallaBtn);
		container.add(colorBtn);
	}
}
