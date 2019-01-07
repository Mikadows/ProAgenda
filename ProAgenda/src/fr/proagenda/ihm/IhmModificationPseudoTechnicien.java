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
import javax.swing.JTextField;

import fr.proagenda.application.Application;
import fr.proagenda.classes.User;
import fr.proagenda.redefineswing.RoundedCornerBorder;

public class IhmModificationPseudoTechnicien extends JPanel {
	private JTextField pseudoField;
	private JTextField pseudoField_1;
	private JPanel panelMainFenetre;
	protected int posY;
	protected int posX;

	/** 
	 * affichage de la modification du pseudo
	 * @param fils : JPannel de la fenetre précédente
	 * @param utilisateur : User utilisateur en cours 
	 */
	protected IhmModificationPseudoTechnicien(User utilisateur) {
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
		
		JLabel lblModifierPseudo = new JLabel("nouveau Pseudo : ");
		lblModifierPseudo.setForeground(Color.LIGHT_GRAY);
		lblModifierPseudo.setBounds(358, 127, 356, 14);
		panelMainFenetre.add(lblModifierPseudo);
		
		pseudoField = new JTextField() {
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
		pseudoField.setBounds(520, 124, 339, 20);
		panelMainFenetre.add(pseudoField);
		
		JLabel lblTapezLe = new JLabel("tapez le \u00E0 nouveau : ");
		lblTapezLe.setForeground(Color.LIGHT_GRAY);
		lblTapezLe.setBounds(358, 185, 356, 14);
		panelMainFenetre.add(lblTapezLe);
		
		pseudoField_1 = new JTextField() {
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
		pseudoField_1.setBounds(520, 182, 339, 20);
		panelMainFenetre.add(pseudoField_1);
		
		JButton btnValider = new JButton("Valider");
		btnValider.setBounds(640, 248, 89, 23);
		panelMainFenetre.add(btnValider);
		
		btnValider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String pseudo1 = pseudoField.getText();
				String pseudo2 = pseudoField_1.getText();
				System.out.println("pseudo 1 : "+new String (pseudo1)+"   pseudo 2 : "+new String (pseudo2));
				if(pseudo1.equals(pseudo2)) {
					utilisateur.setPseudo(pseudo1);
					Application.modifPseudoApp(pseudo1, utilisateur);
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
