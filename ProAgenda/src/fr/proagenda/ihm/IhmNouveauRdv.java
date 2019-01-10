package fr.proagenda.ihm;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.proagenda.application.Application;
import fr.proagenda.classes.Rdv;
import fr.proagenda.classes.User;
import fr.proagenda.redefineswing.RoundedCornerBorder;
import fr.proagenda.ihm.Cal;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import javax.swing.JComboBox;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class IhmNouveauRdv extends JPanel {
	private int font = Application.getRandomNumber();
	private JTextField textFieldAddress;
	private JTextField textFieldDate;
	private Cal fenCal;
	
	private String date;
	private int posX,posXTemp;
	private int posY,posYTemp;
	private int jour,mois,annee;
	private JPanel panelMainFenetre;

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
        panelMainFenetre.add(btnRetour_1);
		
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
		JLabel lblAjouterUnRendezvous = new JLabel("Ajout d'un rendez-vous");
		lblAjouterUnRendezvous.setForeground(Color.LIGHT_GRAY);
		lblAjouterUnRendezvous.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblAjouterUnRendezvous.setBounds(350, 45, 517, 84);
		panelMainFenetre.add(lblAjouterUnRendezvous);
		
		/*
		 * Separator
		 */
		JSeparator separator = new JSeparator();
		separator.setBounds(350, 127, 517, 2);
		panelMainFenetre.add(separator);
		
		/*
		 * label technicien
		 */
		JLabel lblNewLabel = new JLabel("Technicien :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setBounds(383, 182, 78, 14);
		panelMainFenetre.add(lblNewLabel);
		
		/*
		 * Label adresse
		 */
		JLabel lblAdresse = new JLabel("Adresse :");
		lblAdresse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAdresse.setForeground(Color.LIGHT_GRAY);
		lblAdresse.setBounds(399, 230, 62, 14);
		panelMainFenetre.add(lblAdresse);
		
		/*
		 * Saisie adresse
		 */
		textFieldAddress = new JTextField() {
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
		textFieldAddress.setBounds(505, 229, 214, 20);
		panelMainFenetre.add(textFieldAddress);
		textFieldAddress.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Date et Heure :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1.setBounds(358, 275, 103, 14);
		panelMainFenetre.add(lblNewLabel_1);
		
		textFieldDate = new JTextField() {
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
		textFieldDate.setColumns(10);
		textFieldDate.setBounds(505, 274, 78, 20);
		textFieldDate.setEnabled(false);
		panelMainFenetre.add(textFieldDate);
		
		JLabel label_1 = new JLabel("YYYY-MM-JJ espace hh:mm:ss");
		label_1.setForeground(Color.LIGHT_GRAY);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_1.setBounds(505, 305, 214, 23);
		panelMainFenetre.add(label_1);
		
	
		
		@SuppressWarnings("rawtypes")
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(505, 181, 214, 20);
		panelMainFenetre.add(comboBox);
		
		ArrayList<User> users = Application.ihmtoDAOGetNomPrenomUSER();
		
		for(int i = 0; i < users.size(); i++ ) {
			comboBox.addItem(users.get(i).getNom()+" "+users.get(i).getPrenom());
		}
		
		
		JButton btnCal = new JButton("");
		btnCal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO Récupérer les data du calendrier ...
//				IhmNewCalendar f = new IhmNewCalendar(); 
//			    f.setLocation(JFrame.getWindows()[JFrame.getWindows().length-2].getLocation().x+10,JFrame.getWindows()[JFrame.getWindows().length-2].getLocation().y+100);
//			    f.setVisible(true);
				
				JFrame calendar = new JFrame();
				calendar.setBounds(0,0,310,276);
				calendar.setUndecorated(true);
				calendar.setResizable(false);
				calendar.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				calendar.getContentPane().setBackground(Color.WHITE);
				calendar.getContentPane().setLayout(null);
				calendar.setVisible(true);
				
				
				try { 
				    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
				    e.printStackTrace();
				}
				
				
				// TOP WINDOW 
				JPanel panelCloseWindowCal = new JPanel();
				panelCloseWindowCal.setBounds(287, 0, 22, 23);
				calendar.getContentPane().add(panelCloseWindowCal);
				panelCloseWindowCal.setLayout(null);
				
				JButton btnXCal = new JButton(new ImageIcon(Ihm.class.getResource("/fr/proagenda/img/close-window-32.png")));
				btnXCal.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						calendar.setVisible(false);
					}
				});
				btnXCal.setBounds(0, 0, 23, 23);
				panelCloseWindowCal.add(btnXCal);
				
				btnXCal.setBackground(Color.DARK_GRAY);
				
				Cal calTemp = new Cal();
				calTemp.setBounds(0, 23, 310, 220);
				calTemp.setVisible(true);
				calendar.getContentPane().add(calTemp);
				
				JPanel panelTopWindowCal = new JPanel();
				panelTopWindowCal.addMouseMotionListener(new MouseMotionAdapter() {
					   // A chaque deplacement on recalcul le positionnement de la fenetre
					   @SuppressWarnings("static-access")
					@Override
					   public void mouseDragged(MouseEvent e) {
								   int depX = e.getX() - posX;
								   int depY = e.getY() - posY;
								   JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(panelMainFenetre);
				        	   topFrame.getWindows()[topFrame.getWindows().length-1].setLocation(getX()+depX, getY()+depY);
					   }
				});

				panelTopWindowCal.setLayout(null);
				panelTopWindowCal.setBackground(Color.DARK_GRAY);
				panelTopWindowCal.setBounds(0, 0, 288, 23);
				calendar.getContentPane().add(panelTopWindowCal);
				
				JPanel panelBotWindow = new JPanel();
				panelBotWindow.setBackground(Color.DARK_GRAY);
				panelBotWindow.setBounds(0, 242, 310, 34);
				calendar.getContentPane().add(panelBotWindow);
				panelBotWindow.setLayout(null);
				
				JButton btnValider = new JButton("Valider");
				btnValider.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						jour = calTemp.getActiveDay();
						mois = calTemp.GetActiveMonth()+1;
						annee = calTemp.getYearChoice();
						setDateNewRDV(Integer.toString(annee)+"-"+Integer.toString(mois).format("%02d", mois)+"-"+Integer.toString(jour).format("%02d", jour));
						textFieldDate.setText(Integer.toString(annee)+"-"+Integer.toString(mois).format("%02d", mois)+"-"+Integer.toString(jour).format("%02d", jour));
						calendar.setVisible(false);				
					}
				});
				btnValider.setIcon(new ImageIcon(IhmNewCalendar.class.getResource("/fr/proagenda/img/check-mark-12-16.png")));
				btnValider.setBounds(106, 2, 89, 23);
				panelBotWindow.add(btnValider);
				

			}
		});
		btnCal.setIcon(new ImageIcon(IhmNouveauRdv.class.getResource("/fr/proagenda/img/tear-of-calendar-16.png")));
		btnCal.setBounds(593, 275, 23, 23);
		panelMainFenetre.add(btnCal);
		
		JComboBox<String> comboBoxHeure = new JComboBox<String>();
		for(int i = 5 ; i < 22 ; i++) {
			comboBoxHeure.addItem(Integer.toString(i).format("%02d", i));
		}
		comboBoxHeure.setBounds(623, 274, 38, 20);
		panelMainFenetre.add(comboBoxHeure);
		
		JComboBox<String> comboBoxMinutes = new JComboBox<String>();
		for(int i = 0 ; i < 60 ; i+=5) {
				comboBoxMinutes.addItem(Integer.toString(i).format("%02d", i));
		}
		comboBoxMinutes.setBounds(674, 274, 38, 20);
		panelMainFenetre.add(comboBoxMinutes);
		
		JLabel lblH = new JLabel("h");
		lblH.setForeground(Color.LIGHT_GRAY);
		lblH.setBackground(Color.LIGHT_GRAY);
		lblH.setBounds(664, 277, 46, 14);
		panelMainFenetre.add(lblH);
		
		JLabel lblMin = new JLabel("min");
		lblMin.setForeground(Color.LIGHT_GRAY);
		lblMin.setBounds(716, 277, 46, 14);
		panelMainFenetre.add(lblMin);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDateNewRDV(textFieldDate.getText()+" "+comboBoxHeure.getSelectedItem()+":"+comboBoxMinutes.getSelectedItem()+":00");
				System.out.println("date : "+date);
				Rdv rendezVous = new Rdv(textFieldDate.getText(), textFieldAddress.getText(), Application.retIDCompteWithName((String) comboBox.getSelectedItem(), users));
				Application.saveRdvApp(rendezVous);
				removeAll();
				add(new IhmMenuPatron(user));
				revalidate();
				repaint();
			}
		});
		btnValider.setIcon(new ImageIcon(IhmNouveauRdv.class.getResource("/fr/proagenda/img/check-mark-12-16.png")));
		btnValider.setBounds(570, 362, 89, 23);
		panelMainFenetre.add(btnValider);
		
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
	
	public void setDateNewRDV(String date) {
		this.date=date;
	}
}
