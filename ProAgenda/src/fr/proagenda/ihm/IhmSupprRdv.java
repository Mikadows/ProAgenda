package fr.proagenda.ihm;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import fr.proagenda.application.Application;
import fr.proagenda.classes.User;

import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class IhmSupprRdv extends JPanel {
	private int font = Application.getRandomNumber();
	
	/**
	 * Create the panel.
	 */
	public IhmSupprRdv(User user) {		
		this.setBounds(0, 0, 900, 500);
		this.setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		/*
		 * Croix de fermeture
		 */
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(IhmNouveauRdv.class.getResource("/fr/proagenda/img/close-window-32.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((Window) getRootPane().getParent()).dispose();
			}
		});
		btnNewButton.setBounds(877, 0, 23, 23);
		add(btnNewButton);
		
		/*
		 * Bouton retour
		 */
		JButton btnRetour_1 = new JButton("Retour");
        btnRetour_1.setIcon(new ImageIcon(Ihm.class.getResource("/com/sun/javafx/scene/web/skin/Undo_16x16_JFX.png")));
        btnRetour_1.setBounds(340, 11, 89, 23);
        btnRetour_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				add(new IhmMenuPatron(user));
				revalidate();
				repaint();
			}
		});
        add(btnRetour_1);
		
        /*
         * Image du côté
         */
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Ihm.class.getResource("/fr/proagenda/img/fond_"+font+".jpeg")));
		label.setBounds(-5, 0, 320, 500);
		add(label);
		
		/*
		 * Titre
		 */
		JLabel lblAjouterUnRendezvous = new JLabel("Supprimer un rendez-vous");
		lblAjouterUnRendezvous.setForeground(Color.LIGHT_GRAY);
		lblAjouterUnRendezvous.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblAjouterUnRendezvous.setBounds(315, 45, 585, 84);
		add(lblAjouterUnRendezvous);
		
		/*
		 * Separator
		 */
		JSeparator separator = new JSeparator();
		separator.setBounds(350, 127, 517, 2);
		add(separator);
		
		/*
		 * Bouton valider
		 */
		JButton btnValider = new JButton("Valider");
		btnValider.setIcon(new ImageIcon(IhmNouveauRdv.class.getResource("/fr/proagenda/img/check-mark-12-16.png")));
		btnValider.setBounds(572, 362, 89, 23);
		add(btnValider);

	}

}
