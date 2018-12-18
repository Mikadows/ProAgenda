package fr.proagenda.ihm;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import fr.proagenda.classes.User;
import fr.proagenda.ihm.IhmDeuxiemePage;
import fr.proagenda.ihm.IhmModificationPseudo;
import fr.proagenda.ihm.IhmModificationMDP;

public class IhmMenu extends JFrame {
	
	private JButton btnRetour;
	private JButton btnVoirLesRendezvous;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	
	private JLabel lblMenu;
	private JLabel lblProagenda;
	JSeparator separator_1;
	
	private JSeparator separator;

	/**
	 * menu avec voir les rendez vous, modifier mot de passe et modifier login
	 * @param fils : fenetre initiante 
	 * @param user : User contenant les informations de l'utilisateur 
	 */
	public IhmMenu(JPanel fils, User user) {
		fils.removeAll();
		
		btnRetour = new JButton("Déconnexion");
		btnRetour.setIcon(new ImageIcon(Ihm.class.getResource("/javax/swing/plaf/metal/icons/ocean/error.png")));
		btnRetour.setBounds(374, 409, 142, 41);
		fils.add(btnRetour);
		
		lblMenu = new JLabel("Menu");
		lblMenu.setFont(new Font("Tahoma", Font.ITALIC, 50));
		lblMenu.setBounds(374, 107, 142, 75);
		fils.add(lblMenu);
		
		separator = new JSeparator();
		separator.setBounds(346, 180, 185, 2);
		fils.add(separator);
		
		btnVoirLesRendezvous = new JButton("Voir les rendez-vous");
		btnVoirLesRendezvous.setIcon(new ImageIcon(Ihm.class.getResource("/com/sun/javafx/scene/web/skin/DrawHorizontalLine_16x16_JFX.png")));
		btnVoirLesRendezvous.setBounds(346, 229, 185, 23);
		fils.add(btnVoirLesRendezvous);
		
		btnNewButton = new JButton("Modifier Mot de Passe");
		btnNewButton.setIcon(new ImageIcon(Ihm.class.getResource("/com/sun/javafx/scene/web/skin/FontBackgroundColor_16x16_JFX.png")));
		btnNewButton.setBounds(346, 283, 185, 23);
		fils.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Modifier Identifiant");
		btnNewButton_1.setIcon(new ImageIcon(Ihm.class.getResource("/com/sun/javafx/scene/web/skin/FontBackgroundColor_16x16_JFX.png")));
		btnNewButton_1.setBounds(346, 337, 185, 23);
		fils.add(btnNewButton_1);
		
		lblProagenda = new JLabel("ProAgenda");
		lblProagenda.setFont(new Font("Tahoma", Font.PLAIN, 70));
		lblProagenda.setBounds(282, 11, 340, 85);
		fils.add(lblProagenda);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(232, 107, 420, 2);
		fils.add(separator_1);
		
		fils.revalidate();
		fils.repaint();
		
		listenerIhmMenu(fils, user);
	}
	
	/**
	 * Contient tous les listener menuDeLaMortQuiTue(JPanel fils, User user)
	 * @param fils : JPannel de la fenetre précédente 
	 * @param user : utilisateur en cours d'utilisation
	 */
	public void listenerIhmMenu(JPanel fils,User user) {
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getWindows()[0].dispose();
				Ihm continuer=new Ihm();
				continuer.setVisible(true);	
			}
		});
		
		btnVoirLesRendezvous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IhmDeuxiemePage nextPage = new IhmDeuxiemePage(fils, user);
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IhmModificationMDP mdp = new IhmModificationMDP(fils, user);
			}
		});
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IhmModificationPseudo pseudo = new IhmModificationPseudo(fils, user);
			}
		});
	}

}
