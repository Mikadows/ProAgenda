package fr.proagenda.ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.proagenda.application.Application;
import fr.proagenda.classes.User;

public class IhmModificationPseudo {
	private JTextField pseudoField;
	private JTextField pseudoField_1;

	/** 
	 * affichage de la modification du pseudo
	 * @param fils : JPannel de la fenetre précédente
	 * @param utilisateur : User utilisateur en cours 
	 */
	protected IhmModificationPseudo(JPanel fils,User utilisateur) {
		
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
		
		JLabel lblModifierPseudo = new JLabel("nouveau Pseudo : ");
		lblModifierPseudo.setBounds(106, 127, 356, 14);
		fils.add(lblModifierPseudo);
		
		pseudoField = new JTextField();
		pseudoField.setBounds(268, 124, 339, 20);
		fils.add(pseudoField);
		
		JLabel lblTapezLe = new JLabel("tapez le \u00E0 nouveau : ");
		lblTapezLe.setBounds(106, 185, 356, 14);
		fils.add(lblTapezLe);
		
		pseudoField_1 = new JTextField();
		pseudoField_1.setBounds(268, 182, 339, 20);
		fils.add(pseudoField_1);
		
		JButton btnValider = new JButton("Valider");
		btnValider.setBounds(387, 248, 89, 23);
		fils.add(btnValider);
		
		btnValider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String pseudo1 = pseudoField.getText();
				String pseudo2 = pseudoField.getText();
				System.out.println("pseudo 1 : "+new String (pseudo1)+"   pseudo 2 : "+new String (pseudo2));
				if(pseudo1.equals(pseudo2)) {
					Application.modifPseudoApp(pseudo1, utilisateur);
				}
			}
		});
		
		fils.revalidate();
		fils.repaint();
	}

}
