package fr.proagenda.ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import fr.proagenda.application.Application;
import fr.proagenda.classes.User;
import fr.proagenda.ihm.IhmChoixTechnicien;

public class IhmDeuxiemePage {
	private JComboBox<Object[]> comboBoxObj;
	private JComboBox<String> comboBox;

	/**
	 * fenetre affichant de le choix du technicien pour voir ses rendez vous 
	 * @param fils : JPannel de la fenetre précédente
	 * @param user : utilisateur en cours 
	 */
	public IhmDeuxiemePage(JPanel fils, User user) {
		fils.removeAll();
		afficheChoixTechnicienOrderByNameUSER(fils);
		JButton btnRetour_1 = new JButton("Retour");
		
        btnRetour_1.setIcon(new ImageIcon(Ihm.class.getResource("/com/sun/javafx/scene/web/skin/Undo_16x16_JFX.png")));
        btnRetour_1.setBounds(10, 11, 89, 23);
        btnRetour_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				IhmMenu menu = new IhmMenu(fils, user);
			}
		});
        fils.add(btnRetour_1);
	
		fils.revalidate();
		fils.repaint();
	}
	
	/**
	 * @deprecated --> better to use  afficheChoixTechnicienOrderByNameUSER(JPanel fils)
	 * affichage du choix du technicien pour voir ses rendez vous 
	 * @param fils : Jpannel de la fenetre précédente
	 */
	public void afficheChoixTechnicienOrderByNameOBJECT(JPanel fils) {
		
		comboBoxObj = new JComboBox<Object[]>();
		comboBoxObj.setBounds(320, 30, 200, 20);
	    
	    fils.add(comboBoxObj);
	    //comboBoxObj.addItem((Object[])"");

	    Application app = new Application();
		ArrayList<Object> test = app.ihmtoDAOGetNomPrenom();
		
		for(int i = 0; i < test.size(); i+=3 ) {
			Object[] itemData = new Object[] {test.get(i), test.get(i+1),test.get(i+2)};
			comboBoxObj.addItem(itemData);
		} 		
 		
 		JButton btnValider = new JButton("Valider");
		btnValider.setBounds(600, 30, 89, 23);
		fils.add(btnValider);
 		 
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				afficheRDV(fils, (String)comboBox.getSelectedItem());
			}
		});
	}
	
	/**
	 * !REMPLACE : afficheChoixTechnicienOrderByNameOBJECT(JPanel fils)!
	 * affichage du choix du technicien pour voir ses rendez vous 
	 * @param fils : Jpannel de la fenetre précédente
	 */
	public void afficheChoixTechnicienOrderByNameUSER(JPanel fils) {
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(320, 30, 200, 20);
	    
	    fils.add(comboBox);
	    //comboBoxObj.addItem((Object[])"");

	    Application app = new Application();
		ArrayList<User> users = app.ihmtoDAOGetNomPrenomUSER();
		
		for(int i = 0; i < users.size(); i++ ) {
			comboBox.addItem(users.get(i).getNom()+" "+users.get(i).getPrenom());
		} 		
 		
 		JButton btnValider = new JButton("Valider");
		btnValider.setBounds(600, 30, 89, 23);
		fils.add(btnValider);
 		 
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Application app = new Application();
				app.retIDCompteWithName((String) comboBox.getSelectedItem(), users);
				IhmChoixTechnicien techinicien = new IhmChoixTechnicien(fils, app.retIDCompteWithName((String) comboBox.getSelectedItem(), users));
//				afficheRDV(fils, app.retIDCompteWithName((String) comboBox.getSelectedItem(), users));
			}
		});
	}
}
