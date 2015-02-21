package com.artisub.diveshop;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;


import com.artisub.diveshop.view.PuntoDeVenta;


public class App{

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				        if ("Nimbus".equals(info.getName())) {
				            UIManager.setLookAndFeel(info.getClassName());
				            break;
				        }
				    }
//					new FormaPagoCatalogo().setVisible(true);
//					new ColorCatalogo().setVisible(true);
//					new ArticuloCatalogo().setVisible(true);
//					new MarcaCatalogo().setVisible(true);
//					new TallaCatalogo().setVisible(true);
					new PuntoDeVenta().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}