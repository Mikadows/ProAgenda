package fr.proagenda.main;

import fr.proagenda.ihm.Ihm;
import java.awt.EventQueue;


public class Main {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				Ihm frame = new Ihm();
				frame.setResizable(false);
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});

	}

}
