package fr.proagenda.ihm;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;

import fr.proagenda.application.Application;
import fr.proagenda.classes.Rdv;
import fr.proagenda.classes.User;

import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

@SuppressWarnings("serial")
public class IhmSupprRdv extends JPanel {
	private int font = Application.getRandomNumber();
	private JComboBox<String> comboBox;
	private JPanel panelMainFenetre;
	protected int posX;
	protected int posY;
	private JButton btnRetour;
	
	/**
	 * Create the panel.
	 */
	public IhmSupprRdv(User user) {		
		int font = Application.getRandomNumber();
		
		this.setBounds(0, 0, 900, 500);
		setLayout(null);
		
		/*
		 * Croix de fermeture
		 */
		JPanel panelCloseWindow = new JPanel();
		panelCloseWindow.setBackground(Color.DARK_GRAY);
		panelCloseWindow.setBounds(871, 0, 29, 23);
		add(panelCloseWindow);
		panelCloseWindow.setLayout(null);
		
		JButton btnX = new JButton(new ImageIcon(IhmMenu.class.getResource("/fr/proagenda/img/close-window-32.png")));
		btnX.setBounds(0, 0, 29, 23);
		panelCloseWindow.add(btnX);
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((Window) getRootPane().getParent()).dispose();

			}
		});
		
		btnX.setVisible(true);
		btnX.setBackground(Color.DARK_GRAY);
		
		//TEST

		// TOP WINDOW 
		panelMainFenetre = new JPanel();
		panelMainFenetre.setBackground(Color.DARK_GRAY);
		panelMainFenetre.setBounds(0, 0, 900, 522);
		this.add(panelMainFenetre);
		panelMainFenetre.setLayout(null);
		
		addMouseListener(new MouseAdapter() {

			@Override
            //on recupere les coordonnées de la souris
            public void mousePressed(MouseEvent e) {
                posX = e.getX();    //Position X de la souris au clic
                posY = e.getY();    //Position Y de la souris au clic
            }
        });

		
		/*
		 * Bouton retour
		 */
		btnRetour = new JButton("Retour");
        btnRetour.setIcon(new ImageIcon(Ihm.class.getResource("/com/sun/javafx/scene/web/skin/Undo_16x16_JFX.png")));
        btnRetour.setBounds(340, 11, 89, 23);
        btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				add(new IhmMenuPatron(user));
				revalidate();
				repaint();
			}
		});
        panelMainFenetre.add(btnRetour);
		
        /*
         * Image du côté
         */
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Ihm.class.getResource("/fr/proagenda/img/fond_"+font+".jpeg")));
		label.setBounds(-5, 0, 320, 500);
		panelMainFenetre.add(label);
		
		/*
		 * Titre
		 */
		JLabel lblAjouterUnRendezvous = new JLabel("Supprimer un rendez-vous");
		lblAjouterUnRendezvous.setForeground(Color.LIGHT_GRAY);
		lblAjouterUnRendezvous.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblAjouterUnRendezvous.setBounds(315, 45, 585, 84);
		panelMainFenetre.add(lblAjouterUnRendezvous);
		
		/*
		 * Separator
		 */
		JSeparator separator = new JSeparator();
		separator.setBounds(350, 127, 517, 2);
		panelMainFenetre.add(separator);
		
		/*
		 * Combobox liste RDV
		 */
		JComboBox<String> comboBoxRdv = new JComboBox<String>();
		comboBoxRdv.setBounds(505, 242, 214, 20);
		panelMainFenetre.add(comboBoxRdv);
		comboBoxRdv.setEnabled(false);
		
		/*
		 * Combobox liste utilisateurs
		 */
		ArrayList<User> users = Application.ihmtoDAOGetNomPrenomUSER();
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(505, 181, 214, 20);
		panelMainFenetre.add(comboBox);
		comboBox.addItem(" ");

		
		for(int i = 0; i < users.size(); i++ ) {
			comboBox.addItem(users.get(i).getNom()+" "+users.get(i).getPrenom());
		}
		
		
		/*
		 * Bouton valider
		 */
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Application.deleteRdvById(Application.retIDCompteWithName((String) comboBox.getSelectedItem(), users),
										   comboBoxRdv.getSelectedItem().toString().substring(0,19),
										   comboBoxRdv.getSelectedItem().toString().substring(20));
				removeAll();
				add(new IhmMenuPatron(user));
				revalidate();
				repaint();
			}
		});
		btnValider.setIcon(new ImageIcon(IhmNouveauRdv.class.getResource("/fr/proagenda/img/check-mark-12-16.png")));
		btnValider.setBounds(572, 362, 89, 23);
		panelMainFenetre.add(btnValider);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBoxRdv.setEnabled(true);
				comboBoxRdv.removeAllItems();
				ArrayList<String> rendezVous = Application.ihmToDAOGetRdv(Application.retIDCompteWithName((String) comboBox.getSelectedItem(), users));
				for(int i = 0; i < rendezVous.size(); i+=4 ) {
					
					comboBoxRdv.addItem(rendezVous.get(i).substring(0,10)+" "+rendezVous.get(i+1).substring(11,19)+" "+rendezVous.get(i+2));
				} 
			}
		});
		button.setIcon(new ImageIcon(IhmNouveauRdv.class.getResource("/fr/proagenda/img/check-mark-12-16.png")));
		button.setBounds(729, 180, 29, 23);
		panelMainFenetre.add(button);
		
		JPanel panelTopWindow = new JPanel();
		
		panelTopWindow.addMouseMotionListener(new MouseMotionAdapter() {
	   // A chaque deplacement on recalcul le positionnement de la fenetre
		@SuppressWarnings("static-access")
		@Override
		public void mouseDragged(MouseEvent e) {
				   int depX = e.getX() - posX;
				   int depY = e.getY() - posY;
				   JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(panelMainFenetre);
           for(int i = 0 ; i < topFrame.getWindows().length ; i++) {
        	   topFrame.getWindows()[i].setLocation(getX()+depX, getY()+depY);
           }
	    }
		});
		panelTopWindow.setBounds(0, 0, 866, 23);
		this.add(panelTopWindow);
		panelTopWindow.setLayout(null);
		
		this.revalidate();
		this.repaint();

	}
}
