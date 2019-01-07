package fr.proagenda.ihm;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.proagenda.application.Application;
import fr.proagenda.classes.Rdv;
import fr.proagenda.classes.User;
import fr.proagenda.redefineswing.RoundedCornerBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class IhmNouveauRdv extends JPanel {
	private int font = Application.getRandomNumber();
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("unchecked")
	public IhmNouveauRdv(User user) {
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
		JLabel lblAjouterUnRendezvous = new JLabel("Ajout d'un rendez-vous");
		lblAjouterUnRendezvous.setForeground(Color.LIGHT_GRAY);
		lblAjouterUnRendezvous.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblAjouterUnRendezvous.setBounds(350, 45, 517, 84);
		add(lblAjouterUnRendezvous);
		
		/*
		 * Separator
		 */
		JSeparator separator = new JSeparator();
		separator.setBounds(350, 127, 517, 2);
		add(separator);
		
		/*
		 * label technicien
		 */
		JLabel lblNewLabel = new JLabel("Technicien :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setBounds(383, 182, 78, 14);
		add(lblNewLabel);
		
		/*
		 * Label adresse
		 */
		JLabel lblAdresse = new JLabel("Adresse :");
		lblAdresse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAdresse.setForeground(Color.LIGHT_GRAY);
		lblAdresse.setBounds(399, 230, 62, 14);
		add(lblAdresse);
		
		/*
		 * Saisie adresse
		 */
		textField = new JTextField() {
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
		textField.setBounds(505, 229, 214, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Date et Heure :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1.setBounds(358, 275, 103, 14);
		add(lblNewLabel_1);
		
		textField_1 = new JTextField() {
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
		textField_1.setColumns(10);
		textField_1.setBounds(505, 274, 214, 20);
		add(textField_1);
		
		JLabel label_1 = new JLabel("YYYY-MM-JJ espace hh:mm:ss");
		label_1.setForeground(Color.LIGHT_GRAY);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_1.setBounds(505, 305, 214, 23);
		add(label_1);
		
		
		
		@SuppressWarnings("rawtypes")
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(505, 181, 214, 20);
		add(comboBox);
		
	    Application app = new Application();
		ArrayList<User> users = app.ihmtoDAOGetNomPrenomUSER();
		
		for(int i = 0; i < users.size(); i++ ) {
			comboBox.addItem(users.get(i).getNom()+" "+users.get(i).getPrenom());
		}
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Rdv rendezVous = new Rdv(textField_1.getText(), textField.getText(), Application.retIDCompteWithName((String) comboBox.getSelectedItem(), users));
				Application.saveRdvApp(rendezVous);
			}
		});
		btnValider.setIcon(new ImageIcon(IhmNouveauRdv.class.getResource("/fr/proagenda/img/check-mark-12-16.png")));
		btnValider.setBounds(572, 362, 89, 23);
		add(btnValider);
		
	}
}
