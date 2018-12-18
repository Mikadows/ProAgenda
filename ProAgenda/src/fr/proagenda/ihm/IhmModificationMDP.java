package fr.proagenda.ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import fr.proagenda.application.Application;
import fr.proagenda.classes.User;

public class IhmModificationMDP {
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * affichage des champs pour modifier le mot de passe 
	 * @param fils : JPannel de la fenetre précédente
	 * @param utilisateur : User utilisateur en cours 
	 */
	protected IhmModificationMDP(JPanel fils,User utilisateur) {
		fils.removeAll();
		

		JButton btnRetour_1 = new JButton("Retour");
		
        btnRetour_1.setIcon(new ImageIcon(Ihm.class.getResource("/com/sun/javafx/scene/web/skin/Undo_16x16_JFX.png")));
        btnRetour_1.setBounds(10, 11, 89, 23);
        btnRetour_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IhmMenu menu = new IhmMenu(fils, utilisateur);
			}
		});
        fils.add(btnRetour_1);
		
		JLabel lblModifierMotDe = new JLabel("nouveau mot de passe : ");
		lblModifierMotDe.setBounds(106, 127, 356, 14);
		fils.add(lblModifierMotDe);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(268, 124, 339, 20);
		fils.add(passwordField);
		
		JLabel lblTapezLe = new JLabel("tapez le \u00E0 nouveau : ");
		lblTapezLe.setBounds(106, 185, 356, 14);
		fils.add(lblTapezLe);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(268, 182, 339, 20);
		fils.add(passwordField_1);
		
		JButton btnValider = new JButton("Valider");
		btnValider.setBounds(387, 248, 89, 23);
		fils.add(btnValider);
		
		btnValider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				char []mdp1 = passwordField.getPassword();
				char []mdp2 = passwordField_1.getPassword();
				System.out.println("mdp 1 : "+new String (mdp1)+"   mdp 2 : "+new String (mdp2));
				if(Application.getShaApp(new String (mdp1)).equals(Application.getShaApp(new String (mdp2)))) {
					Application.modifPseudoApp(Application.getShaApp(new String (mdp1)),utilisateur);
					System.out.println("bonjour");
				}
			}
		});
		
		fils.revalidate();
		fils.repaint();
	}
	
}
