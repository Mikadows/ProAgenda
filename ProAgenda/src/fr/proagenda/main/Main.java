package fr.proagenda.main;

import fr.proagenda.ihm.Ihm;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;


public class Main {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				Ihm frame = new Ihm();
				frame.setUndecorated(true);
				frame.setResizable(false);
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});

	}

}
