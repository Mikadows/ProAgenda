package fr.proagenda.ihm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import fr.proagenda.application.Application;
import fr.proagenda.classes.User;
import fr.proagenda.redefineswing.RoundedCornerBorder;

public class IhmModificationMDPTechnicien extends JPanel {
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPanel panelMainFenetre;
	protected int posX;
	protected int posY;

	/**
	 * affichage des champs pour modifier le mot de passe 
	 * @param fils : JPannel de la fenetre pr�c�dente
	 * @param utilisateur : User utilisateur en cours 
	 */
	protected IhmModificationMDPTechnicien(User utilisateur) {
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
            //on recupere les coordonn�es de la souris
            public void mousePressed(MouseEvent e) {
                posX = e.getX();    //Position X de la souris au clic
                posY = e.getY();    //Position Y de la souris au clic
            }
        });

		JButton btnRetour_1 = new JButton("Retour");
		
        btnRetour_1.setIcon(new ImageIcon(Ihm.class.getResource("/com/sun/javafx/scene/web/skin/Undo_16x16_JFX.png")));
        btnRetour_1.setBounds(325, 11, 89, 23);
        btnRetour_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				add(new IhmMenu(utilisateur));
				revalidate();
				repaint();
			}
		});
        panelMainFenetre.add(btnRetour_1);
		
		JLabel lblModifierMotDe = new JLabel("nouveau mot de passe : ");
		lblModifierMotDe.setForeground(Color.LIGHT_GRAY);
		lblModifierMotDe.setBounds(355, 127, 356, 14);
		panelMainFenetre.add(lblModifierMotDe);
		
		passwordField = new JPasswordField(){
			 @Override protected void paintComponent(Graphics g) {
				    if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
				      Graphics2D g2 = (Graphics2D) g.create();
				      g2.setPaint(getBackground());
				      g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(
				          0, 0, getWidth() - 1, getHeight() - 1));
				      g2.dispose();
				    }
				    super.paintComponent(g);
				  }
				  @Override public void updateUI() {
				    super.updateUI();
				    setOpaque(false);
				    setBorder(new RoundedCornerBorder());
				  }
				};
		passwordField.setBounds(517, 124, 339, 20);
		panelMainFenetre.add(passwordField);
		
		JLabel lblTapezLe = new JLabel("tapez le \u00E0 nouveau : ");
		lblTapezLe.setForeground(Color.LIGHT_GRAY);
		lblTapezLe.setBounds(355, 185, 356, 14);
		panelMainFenetre.add(lblTapezLe);
		
		passwordField_1 = new JPasswordField(){
			 @Override protected void paintComponent(Graphics g) {
				    if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
				      Graphics2D g2 = (Graphics2D) g.create();
				      g2.setPaint(getBackground());
				      g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(
				          0, 0, getWidth() - 1, getHeight() - 1));
				      g2.dispose();
				    }
				    super.paintComponent(g);
				  }
				  @Override public void updateUI() {
				    super.updateUI();
				    setOpaque(false);
				    setBorder(new RoundedCornerBorder());
				  }
				};
		passwordField_1.setBounds(517, 182, 339, 20);
		panelMainFenetre.add(passwordField_1);
		
		JButton btnValider = new JButton("Valider");
		btnValider.setBounds(636, 248, 89, 23);
		panelMainFenetre.add(btnValider);
		
		btnValider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				char []mdp1 = passwordField.getPassword();
				char []mdp2 = passwordField_1.getPassword();
				System.out.println("mdp 1 : "+new String (mdp1)+"   mdp 2 : "+new String (mdp2));
				if(Application.getShaApp(new String (mdp1)).equals(Application.getShaApp(new String (mdp2)))) {
					Application.modifMdpApp(Application.getShaApp(new String (mdp1)),utilisateur);
					removeAll();
					add(new IhmMenu(utilisateur));
					revalidate();
					repaint();
				}
			}
		});
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Ihm.class.getResource("/fr/proagenda/img/fond_"+font+".jpeg")));
		label.setBounds(-5, 0, 320, 500);
		panelMainFenetre.add(label);
		
		revalidate();
		repaint();
	}
	
}
