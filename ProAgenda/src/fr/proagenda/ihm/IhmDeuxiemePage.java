package fr.proagenda.ihm;

import java.awt.Color;
import java.awt.Container;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import fr.proagenda.application.Application;
import fr.proagenda.classes.User;
import fr.proagenda.ihm.IhmChoixTechnicien;

public class IhmDeuxiemePage extends JPanel {
	private JComboBox<Object[]> comboBoxObj;
	private JComboBox<String> comboBox;
	protected int posX;
	protected int posY;
	private JPanel panelMainFenetre;
 
	/**
	 * fenetre affichant de le choix du technicien pour voir ses rendez vous 
	 * @param fils : JPannel de la fenetre précédente
	 * @param user : utilisateur en cours 
	 */
	public IhmDeuxiemePage(User user) {
		this.setBounds(0, 0, 900, 500);
		setLayout(null);
		
		JPanel panelCloseWindow = new JPanel();
		panelCloseWindow.setBounds(876, 0, 29, 23);
		this.add(panelCloseWindow);
		
		JButton btnX = new JButton(new ImageIcon(Ihm.class.getResource("/fr/proagenda/img/close-window-32.png")));
		btnX.setBounds(0, 0, 23, 23);
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((Window) getRootPane().getParent()).dispose();

			}
		});
		
		addMouseListener(new MouseAdapter() {

			@Override
            //on recupere les coordonnées de la souris
            public void mousePressed(MouseEvent e) {
                posX = e.getX();    //Position X de la souris au clic
                posY = e.getY();    //Position Y de la souris au clic
            }
        });

		btnX.setVisible(true);
		panelCloseWindow.setLayout(null);
		panelCloseWindow.add(btnX);
		btnX.setBackground(Color.DARK_GRAY);
		
		panelMainFenetre = new JPanel();
		panelMainFenetre.setBackground(Color.DARK_GRAY);
		panelMainFenetre.setBounds(0, 0, 900, 522);
		add(panelMainFenetre);
		
		JButton btnRetour_1 = new JButton("Retour");
		
        btnRetour_1.setIcon(new ImageIcon(Ihm.class.getResource("/com/sun/javafx/scene/web/skin/Undo_16x16_JFX.png")));
        btnRetour_1.setBounds(10, 11, 89, 23);
        btnRetour_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				IhmMenu menu = new IhmMenu(fils, user);
				removeAll();
				add(new IhmMenuPatron(user));
				revalidate();
				repaint();
			}
		});
        panelMainFenetre.setLayout(null);
        panelMainFenetre.add(btnRetour_1);
		

		
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(320, 30, 200, 20);
	    
	    panelMainFenetre.add(comboBox);

	    Application app = new Application();
		ArrayList<User> users = app.ihmtoDAOGetNomPrenomUSER();
		
		for(int i = 0; i < users.size(); i++ ) {
			comboBox.addItem(users.get(i).getNom()+" "+users.get(i).getPrenom());
		} 		
 		
 		JButton btnValider = new JButton("Valider");
		btnValider.setBounds(600, 30, 89, 23);
		panelMainFenetre.add(btnValider);
 		 
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Application app = new Application();
				app.retIDCompteWithName((String) comboBox.getSelectedItem(), users);
				panelMainFenetre.add(new IhmChoixTechnicien(app.retIDCompteWithName((String) comboBox.getSelectedItem(), users)));
				//IhmChoixTechnicien techinicien = new IhmChoixTechnicien(fils, app.retIDCompteWithName((String) comboBox.getSelectedItem(), users));
//				afficheRDV(fils, app.retIDCompteWithName((String) comboBox.getSelectedItem(), users));
			}
		});
         
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
	
		revalidate();
		repaint();
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
	public void afficheChoixTechnicienOrderByNameUSER() {
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(320, 30, 200, 20);
	    
	    this.add(comboBox);
	    //comboBoxObj.addItem((Object[])"");

	    Application app = new Application();
		ArrayList<User> users = app.ihmtoDAOGetNomPrenomUSER();
		
		for(int i = 0; i < users.size(); i++ ) {
			comboBox.addItem(users.get(i).getNom()+" "+users.get(i).getPrenom());
		} 		
 		
 		JButton btnValider = new JButton("Valider");
		btnValider.setBounds(600, 30, 89, 23);
		this.add(btnValider);
 		 
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Application app = new Application();
				app.retIDCompteWithName((String) comboBox.getSelectedItem(), users);
				//IhmChoixTechnicien techinicien = new IhmChoixTechnicien(fils, app.retIDCompteWithName((String) comboBox.getSelectedItem(), users));
//				afficheRDV(fils, app.retIDCompteWithName((String) comboBox.getSelectedItem(), users));
			}
		});
	}
}
