package com.artisub.diveshop.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public abstract class Dialogo extends JFrame {
	
	private static final long serialVersionUID = 4136870369080166466L;
	
	Container container;
	JPanel panel, textPanel, buttonPanel, titlePanel;
	JLabel label;
	JTextField textfield;
	JButton okBtn, cancelBtn;
	
	String panelTitle;

	public Dialogo(String title, String panelTitle, String nombreItem){
		super(title);
		setSize(340, 150);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.panelTitle = panelTitle;
		init();
		textfield.setText(nombreItem);
		textfield.selectAll();
		setVisible(true);
	}
	
	private void init(){
		container = getContentPane();
		
		panel =  new JPanel(new BorderLayout());
		textPanel = new JPanel();
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
		textPanel.setBorder(new TitledBorder(panelTitle));
		
		
		buttonPanel = new JPanel(new FlowLayout());
		label = new JLabel(" Ingrese el Nombre");
		textfield = new JTextField();
		textfield.setPreferredSize(new Dimension(200,23));
		
		okBtn = new JButton("Aceptar");
		okBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				okAction();
				
			}
		});
		
		cancelBtn =  new JButton("Cancelar");
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();			
			}
		});
		
		buttonPanel.add(okBtn);
		buttonPanel.add(cancelBtn);
		textPanel.add(textfield);
		textPanel.add(Box.createVerticalStrut(10));
		panel.add(textPanel, BorderLayout.CENTER);
		panel.add(buttonPanel, BorderLayout.PAGE_END);
		container.add(panel);
	}
	

	protected abstract void okAction();
}
