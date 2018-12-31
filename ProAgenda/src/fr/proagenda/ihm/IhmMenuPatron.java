package fr.proagenda.ihm;

import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;

import fr.proagenda.application.Application;
import fr.proagenda.classes.User;

@SuppressWarnings("serial")
public class IhmMenuPatron extends JPanel {

	
	private JButton btnRetour;
	private JButton btnVoirLesRendezvous;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	
	private JLabel lblMenu;
	private JLabel lblProagenda;
	JSeparator separator_1;
	
	private JSeparator separator;
	private JPanel panelMainFenetre;
	protected int posX;
	protected int posY;

	/**
	 * menu avec voir les rendez vous, modifier mot de passe et modifier login
	 * @param fils : fenetre initiante 
	 * @param user : User contenant les informations de l'utilisateur 
	 */
	public IhmMenuPatron(User user) {
		int font = Application.getRandomNumber();
		
		this.setBounds(0, 0, 900, 500);
		setLayout(null);
		
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

		btnRetour = new JButton("Déconnexion");
		btnRetour.setIcon(new ImageIcon(IhmMenu.class.getResource("/fr/proagenda/img/power-32.png")));
		btnRetour.setBounds(558,445,142,41);
		panelMainFenetre.add(btnRetour);
		
		lblMenu = new JLabel("Menu Patron");
		lblMenu.setForeground(Color.LIGHT_GRAY);
		lblMenu.setFont(new Font("Tahoma", Font.ITALIC, 50));
		lblMenu.setBounds(477,107,295,75);
		panelMainFenetre.add(lblMenu);
		
		separator = new JSeparator();
		separator.setBounds(477,180,295,2);
		panelMainFenetre.add(separator);
		
		btnVoirLesRendezvous = new JButton("Voir les rendez-vous");
		btnVoirLesRendezvous.setIcon(new ImageIcon(IhmMenu.class.getResource("/fr/proagenda/img/list.png")));
		btnVoirLesRendezvous.setBounds(531,227,185,23);
		panelMainFenetre.add(btnVoirLesRendezvous);
		
		btnNewButton = new JButton("Modifier Mot de Passe");
		btnNewButton.setIcon(new ImageIcon(IhmMenu.class.getResource("/fr/proagenda/img/edit.png")));
		btnNewButton.setBounds(531,339,185,23);
		panelMainFenetre.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Modifier Identifiant");
		btnNewButton_1.setIcon(new ImageIcon(IhmMenu.class.getResource("/fr/proagenda/img/edit.png")));
		btnNewButton_1.setBounds(531,384,185,23);
		panelMainFenetre.add(btnNewButton_1);
		
		lblProagenda = new JLabel("ProAgenda");
		lblProagenda.setForeground(Color.LIGHT_GRAY);
		lblProagenda.setFont(new Font("Tahoma", Font.PLAIN, 70));
		lblProagenda.setBounds(453,11,340,85);
		panelMainFenetre.add(lblProagenda);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(420,107,420,2);
		panelMainFenetre.add(separator_1);				
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Ihm.class.getResource("/fr/proagenda/img/fond_"+font+".jpeg")));
		label.setBounds(-5, 0, 320, 500);
		panelMainFenetre.add(label);
		
		JButton button = new JButton("Ajouter des rendez-vous");
		button.setIcon(new ImageIcon(IhmMenuPatron.class.getResource("/fr/proagenda/img/add-file-16.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.setBounds(531, 271, 185, 23);
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
		listenerIhmMenu( user);
	}
	
	/**
	 * Contient tous les listener menuDeLaMortQuiTue(JPanel fils, User user)
	 * @param fils : JPannel de la fenetre précédente 
	 * @param user : utilisateur en cours d'utilisation
	 */
	public void listenerIhmMenu(User user) {
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((Window) getRootPane().getParent()).dispose();
				Ihm continuer=new Ihm();
				continuer.setVisible(true);	
			}
		});
		
		btnVoirLesRendezvous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				IhmDeuxiemePage nextPage = new IhmDeuxiemePage(fils, user);
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				IhmModificationMDP mdp = new IhmModificationMDP(fils, user);
			}
		});
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				IhmModificationPseudo pseudo = new IhmModificationPseudo(fils, user);
			}
		});
	}
}
