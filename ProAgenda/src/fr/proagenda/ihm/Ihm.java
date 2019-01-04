package fr.proagenda.ihm;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;

import fr.proagenda.application.Application;
import fr.proagenda.classes.User;
import fr.proagenda.ihm.IhmMenu;
import fr.proagenda.redefineswing.*;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

 
/**
 * Classe IHM
 * @author Mathieu
 *
 */
@SuppressWarnings("serial")
public class Ihm extends JFrame{
//	private static int height= 900;
//	private static int width = 500; 

	private JPanel contentPane;
	private JTextField pseudoField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JTextField textFieldPseudo;
	private JRadioButton rdbtnAfficher;
	private JLabel lblTaillemdp;
	private JComboBox<String> comboBox;
	private JButton btnValider;
	private JCheckBox chckbxNewCheckBox;
	private JProgressBar progressBar ;
	private JButton validBtn;
	private JButton button;
	private JButton btnRetour_1;
	
    private int posX;
	private int posY;

	
	/**
	 * Fenetre Principale : Log de l'application 
	 * @return 
	 * TODO modifier le passage de l'utilisateur entre les fonctions : User --> int ID  
	 */
	public Ihm() {	
		int font = Application.getRandomNumber();
		
		this.setUndecorated(true);
		try { 
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		    e.printStackTrace();
		}
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 500);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		// TOP WINDOW 
		JPanel panelCloseWindow = new JPanel();
		panelCloseWindow.setBounds(876, 0, 29, 23);
		getContentPane().add(panelCloseWindow);
		panelCloseWindow.setLayout(null);
		
		JButton btnX = new JButton(new ImageIcon(Ihm.class.getResource("/fr/proagenda/img/close-window-32.png")));
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(DISPOSE_ON_CLOSE);
			}
		});
		
		btnX.setVisible(true);
		panelCloseWindow.add(btnX);
		btnX.setBounds(0, 0, 23, 23);
		btnX.setBackground(Color.DARK_GRAY);
		panelCloseWindow.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnX}));
		
		JPanel panelMainFenetre = new JPanel();
		panelMainFenetre.setBackground(Color.DARK_GRAY);
		panelMainFenetre.setBounds(309, 0, 596, 522);
		getContentPane().add(panelMainFenetre);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Ihm.class.getResource("/fr/proagenda/img/fond_"+font+".jpeg")));
		lblNewLabel.setBounds(-5, 0, 320, 500);
		getContentPane().add(lblNewLabel);
		panelMainFenetre.setLayout(null);
		
		JPanel panelTopWindow = new JPanel();
		addMouseListener(new MouseAdapter() {
            @Override
            //on recupere les coordonnées de la souris
            public void mousePressed(MouseEvent e) {
                posX = e.getX();    //Position X de la souris au clic
                posY = e.getY();    //Position Y de la souris au clic
            }
        });
         
        panelTopWindow.addMouseMotionListener(new MouseMotionAdapter() {
            // A chaque deplacement on recalcul le positionnement de la fenetre
            @Override
            public void mouseDragged(MouseEvent e) {
                int depX = e.getX() - posX;
                int depY = e.getY() - posY;
                for(int i = 0 ; i < getWindows().length; i++) {
                	getWindows()[i].setLocation(getX()+depX, getY()+depY);
                }
            }
        });
		panelTopWindow.setBounds(0, 0, 866, 23);
		getContentPane().add(panelTopWindow);
		
		
		//le reste
		JLabel lblPseudo = new JLabel("Pseudo : ");
		lblPseudo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPseudo.setForeground(Color.LIGHT_GRAY);
		lblPseudo.setBounds(81, 151, 82, 14);
		panelMainFenetre.add(lblPseudo);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe :");
		lblMotDePasse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMotDePasse.setForeground(Color.LIGHT_GRAY);
		lblMotDePasse.setBounds(74, 219, 89, 14);
		panelMainFenetre.add(lblMotDePasse);
		
//		pseudoField = new JTextField();
//		pseudoField.setBounds(210, 148, 199, 20);
//		panelMainFenetre.add(pseudoField);
//		pseudoField.setColumns(10);
		pseudoField = new JTextField(20) {
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
			pseudoField.setBounds(210,148,199,20);
			panelMainFenetre.add(pseudoField);
		
		
		passwordField = new JPasswordField() {
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
		
		passwordField.setBounds(210, 216, 199, 20);
		panelMainFenetre.add(passwordField);

//		JLabel lblProagenda = new JLabel("ProAgenda");
//      lblProagenda.setFont(new Font("Tahoma", Font.PLAIN, 70));
//      lblProagenda.setBounds(282, 11, 340, 85);
//      contentPane.add(lblProagenda);
//
//      JSeparator separator_1 = new JSeparator();
//      separator_1.setBounds(232, 107, 420, 2);
//      contentPane.add(separator_1);

		validBtn = new JButton("Valider");
		validBtn.setIcon(new ImageIcon(Ihm.class.getResource("/fr/proagenda/img/check-mark-12-16.png")));
		validBtn.setBounds(266, 276, 95, 22);
		panelMainFenetre.add(validBtn);
		
		button = new JButton("Nouvel utilisateur");
		button.setIcon(new ImageIcon(Ihm.class.getResource("/fr/proagenda/img/add-user-16.png")));
		button.setBounds(233, 335, 156, 22);
		panelMainFenetre.add(button);
		
		
//		
		listenerIHM();
		
		
	}
	
	/**
	 * Contient tous les listener du constructeur Ihm()
	 * listener fenetre principale
	 */
	public void listenerIHM() {
		validBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pseudo = pseudoField.getText();
				char []motPasse = passwordField.getPassword();
				int connexion = Application.getResultsCmpLogins(pseudo, Application.getShaApp(new String (motPasse)));
				System.out.println("connexion : "+ connexion);
				if(connexion == 0) {
					JLabel lblPseudoOuMot = new JLabel("pseudo ou mot de passe incorrect");
					lblPseudoOuMot.setForeground(Color.RED);
					lblPseudoOuMot.setBounds(327, 300, 253, 14);
					contentPane.add(lblPseudoOuMot);
					contentPane.revalidate();
					contentPane.repaint();
				}else if (connexion == 1) {
					User next = new User(pseudo, Application.getShaApp(new String (motPasse)));
					next = Application.getDataUserByUser(next);
					System.out.println("oooooo");
					if(next.getId_metier() == 1) {
						System.out.println("Technicien");
						getContentPane().removeAll();
						getContentPane().add(new IhmMenu(next));
						getContentPane().revalidate();
						getContentPane().repaint();

					}else if(next.getId_metier() == 0){
						System.out.println("Patron");
						getContentPane().removeAll();
						getContentPane().add(new IhmMenuPatron(next));
						getContentPane().revalidate();
						getContentPane().repaint();
					}else if(next.getId_metier() == 2){
						System.out.println("Autre");
						getContentPane().removeAll();
						//TODO page clients normaux 
						//getContentPane().add(new IhmMenuPatron(next));
						getContentPane().revalidate();
						getContentPane().repaint();
					}

					}
				}
			}
		);
		
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					String pseudo = pseudoField.getText();
					char []motPasse = passwordField.getPassword();
					int connexion = Application.getResultsCmpLogins(pseudo, Application.getShaApp(new String (motPasse)));
					System.out.println("connexion : "+ connexion);
					if(connexion == 0) {
						JLabel lblPseudoOuMot = new JLabel("pseudo ou mot de passe incorrect");
						lblPseudoOuMot.setForeground(Color.RED);
						lblPseudoOuMot.setBounds(327, 300, 253, 14);
						contentPane.add(lblPseudoOuMot);
						contentPane.revalidate();
						contentPane.repaint();
					}else if (connexion == 1) {
						User next = new User(pseudo, Application.getShaApp(new String (motPasse)));
						next = Application.getDataUserByUser(next);
						System.out.println("oooooo");
						if(next.getId_metier() == 1) {
							System.out.println("Technicien");
							getContentPane().removeAll();
							getContentPane().add(new IhmMenu(next));
							getContentPane().revalidate();
							getContentPane().repaint();

						}else if(next.getId_metier() == 0){
							System.out.println("Patron");
							getContentPane().removeAll();
							getContentPane().add(new IhmMenuPatron(next));
							getContentPane().revalidate();
							getContentPane().repaint();
						}

  
					}
				 }
			}
		});
		
		pseudoField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					String pseudo = pseudoField.getText();
					char []motPasse = passwordField.getPassword();
					int connexion = Application.getResultsCmpLogins(pseudo, Application.getShaApp(new String (motPasse)));
					System.out.println("connexion : "+ connexion);
					if(connexion == 0) {
						JLabel lblPseudoOuMot = new JLabel("pseudo ou mot de passe incorrect");
						lblPseudoOuMot.setForeground(Color.RED);
						lblPseudoOuMot.setBounds(327, 300, 253, 14);
						contentPane.add(lblPseudoOuMot);
						contentPane.revalidate();
						contentPane.repaint();
					}else if (connexion == 1) {
						User next = new User(pseudo, Application.getShaApp(new String (motPasse)));
						getContentPane().removeAll();
						getContentPane().add(new IhmMenu(next));
						getContentPane().revalidate();
						getContentPane().repaint();


					}
				 }
			}
		});
		
		validBtn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
					String pseudo = pseudoField.getText();
					char []motPasse = passwordField.getPassword();
					int connexion = Application.getResultsCmpLogins(pseudo, Application.getShaApp(new String (motPasse)));
					System.out.println("connexion : "+ connexion);
					if(connexion == 0) {
						JLabel lblPseudoOuMot = new JLabel("pseudo ou mot de passe incorrect");
						lblPseudoOuMot.setForeground(Color.RED);
						lblPseudoOuMot.setBounds(327, 300, 253, 14);
						contentPane.add(lblPseudoOuMot);
						contentPane.revalidate();
						contentPane.repaint();
					}else if (connexion == 1) {
						User next = new User(pseudo, Application.getShaApp(new String (motPasse)));
						getContentPane().removeAll();
						getContentPane().add(new IhmMenu(next));
						getContentPane().revalidate();
						getContentPane().repaint();


					}
				 }
			}
		});
		
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getContentPane().removeAll();
				getContentPane().add(new IhmNouvelUtilisateur());
//				new IhmNouvelUtilisateur(getContentPane());
				getContentPane().revalidate();
				getContentPane().repaint();
//				DefineNouvelUtilisateurFenetre(contentPane);
				
			}
		});
	}
	
	
	
	

	/**
	 * @param fils : fenetre principale qu'on va effacer
	 * Déclaration de tous les éléments graphique de la fonction nouvelUtilisateur 
	 */
	public void DefineNouvelUtilisateurFenetre(JPanel fils){
		/*Variables*/
	    int Decalage = 130;
	    
	    /*démarrage*/
	    fils.removeAll();
	    fils.repaint();
	    
	    
	    /*Déclaration et initialisation JLabels*/
	    JLabel lblNom = new JLabel("Nom : ");
	    lblNom.setBounds(10+Decalage, 57, 87, 14);
	    fils.add(lblNom);
	    
	    JLabel lblPrenom = new JLabel("Prenom : ");
	    lblPrenom.setBounds(10+Decalage, 100, 87, 14);
	    fils.add(lblPrenom);
	    
	    JLabel lblPseudo = new JLabel("Pseudo : ");
	    lblPseudo.setBounds(10+Decalage, 135, 87, 14);
	    fils.add(lblPseudo);
	    
	    JLabel lblMetier = new JLabel("metier :");
	    lblMetier.setBounds(10+Decalage, 169, 46, 14);
	    contentPane.add(lblMetier);
	    
	    JLabel lblMotDePasse = new JLabel("Mot de passe : ");
	    lblMotDePasse.setBounds(10+Decalage, 201, 126, 14);
	    fils.add(lblMotDePasse);
	    
	    JLabel lblRetapezLeMot = new JLabel("Retapez le Mot de passe : ");
	    lblRetapezLeMot.setBounds(10+Decalage, 282, 168, 14);
	    fils.add(lblRetapezLeMot);
	    
	    
	    	    
	    /*Déclaration JTextField*/
	    textFieldNom = new JTextField();
	    textFieldNom.setBounds(177+Decalage, 54, 285, 20);
	    fils.add(textFieldNom);
	    
	    textFieldPrenom = new JTextField();
	    textFieldPrenom.setEnabled(false);
	    textFieldPrenom.setBounds(177+Decalage, 97, 285, 20);
	    fils.add(textFieldPrenom);
	    
	    textFieldPseudo = new JTextField();
	    textFieldPseudo.setEnabled(false);
	    textFieldPseudo.setBounds(177+Decalage, 128, 285, 20);
	    fils.add(textFieldPseudo);
	    
	    passwordField = new JPasswordField();
	    passwordField.setEnabled(false);
	    passwordField.setBounds(177+Decalage, 198, 285, 20);
	    fils.add(passwordField);
	    
	    passwordField_1 = new JPasswordField();
	    passwordField_1.setEnabled(false);
	    passwordField_1.setBounds(177+Decalage, 279, 285, 20);
	    fils.add(passwordField_1);
	    
	    
	    
	    /*ProgressBar*/
	    progressBar = new JProgressBar();
	    progressBar.setBounds(213+Decalage, 254, 220, 14);
	    fils.add(progressBar);
	    
	    
	    
	    /*listes*/
	    comboBox = new JComboBox<String>();
	    comboBox.setEnabled(false);
	    comboBox.setBounds(261+Decalage, 167, 109, 20);
			
	    fils.add(comboBox);
	    comboBox.addItem("");
	    comboBox.addItem("Technicien");
	    comboBox.addItem("Autre");
	    
	    
	    
	    /*Déclaration des Boutons*/
	    btnRetour_1 = new JButton("Retour");
	    btnRetour_1.setIcon(new ImageIcon(Ihm.class.getResource("/com/sun/javafx/scene/web/skin/Undo_16x16_JFX.png")));
        btnRetour_1.setBounds(10, 11, 89, 23);
        fils.add(btnRetour_1);
	    
	    btnValider = new JButton("Valider");
	    btnValider.setEnabled(false);
	    btnValider.setBounds(278+Decalage, 346, 89, 23);
	    fils.add(btnValider);
	    
	    
	    
	    /*Déclaration checkBox / radioButton*/
	    rdbtnAfficher = new JRadioButton("afficher");
	    rdbtnAfficher.setEnabled(false);
	    rdbtnAfficher.setBounds(468+Decalage, 197, 109, 23);
	    fils.add(rdbtnAfficher);
	    
	    chckbxNewCheckBox = new JCheckBox("");
	    chckbxNewCheckBox.setEnabled(false);
	    chckbxNewCheckBox.setBounds(468+Decalage, 282, 97, 23);
	    fils.add(chckbxNewCheckBox);
	    
	    
	    
	    /*JLabel "dynamique"*/
	    lblTaillemdp = new JLabel("Mot de passe court ");
	    
	    @SuppressWarnings("unused")
		JLabel lblErreurSQL = new JLabel("Pseudo déjà utilisé");
	    
	    
	    
	    /*Appel Des Listenner*/
	    nouvelUtilisateurLstenner(fils);
	    
	}
	
	/**
	 * définis les listenner de DefineNouvelUtilisateurFenetre
	 * @param fils : JPanel de DefineNouvelUtilisateurFenetre
	 */
	public void nouvelUtilisateurLstenner(JPanel fils) {
		/*Variables*/
	    int Decalage = 130;
	    
	    
	    btnRetour_1.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {

	            dispose();
	            Ihm retour=new Ihm();
	            retour.setVisible(true);
	        }
	    });
		textFieldNom.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e){
				textFieldPrenom.setEnabled(true);
			}
		});
		
		textFieldPrenom.addKeyListener(new KeyAdapter() {
				public void keyReleased(KeyEvent e) {
					textFieldPseudo.setEnabled(true);
				}
		});
		
		textFieldPseudo.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e){
				comboBox.setEnabled(true);
			}
		});
		
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				passwordField.setEnabled(true);
				passwordField_1.setEnabled(true);
				rdbtnAfficher.setEnabled(true);
			}
		});
		
		rdbtnAfficher.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            if(rdbtnAfficher.isSelected()) {
	                passwordField.setEchoChar((char)0);
	                passwordField_1.setEnabled(false);
	            }else {
	                passwordField.setEchoChar('•');
	                passwordField_1.setEnabled(true);
	            }

	        }
	    });
		
		passwordField.addKeyListener(new KeyAdapter() {
	        @SuppressWarnings("deprecation")
			@Override
	        public void keyReleased(KeyEvent e){
	            int val =100 * passwordField.getCaretPosition() /40 ;
	            progressBar.setValue(val);
	             try{
	                InputStream flux=new FileInputStream("MostPopularPasswords.txt"); 
	                InputStreamReader lecture=new InputStreamReader(flux);
	                BufferedReader buff=new BufferedReader(lecture);
	                String ligne;
	                int valT =0;
	                ligne=buff.readLine();
	                while ((ligne=buff.readLine())!=null){
	                    if(passwordField.getText().equals(ligne)) {
	                        lblTaillemdp.setText("mot de passe Courant, modifiez le !");
	                        lblTaillemdp.setBounds(220+Decalage, 229, 200, 14);
	                        lblTaillemdp.setForeground(Color.RED);
	                        valT=2;
	                        progressBar.setForeground(Color.RED);
	                    }else if(passwordField.getText().indexOf(ligne)>=0) {
	                        lblTaillemdp.setText("Un mot de passe courant est contenu");
	                        lblTaillemdp.setBounds(215+Decalage, 229, 220, 14);
	                        lblTaillemdp.setForeground(Color.ORANGE);
	                        valT =1;
	                        progressBar.setForeground(Color.ORANGE);
	                    }else if(passwordField.getCaretPosition()<10 && valT !=2 && valT!=1) {
	                        lblTaillemdp.setText("mot de passe Court !");
	                        lblTaillemdp.setBounds(250+Decalage, 229, 200, 14);
	                        lblTaillemdp.setForeground(Color.BLUE);
	                        if(valT==1) {
	                            progressBar.setForeground(Color.ORANGE);
	                        }else if(valT==2){
	                            progressBar.setForeground(Color.RED);
	                        }else {
	                            progressBar.setForeground(Color.BLUE);
	                        }
	                        valT=3;

	                    }else if(passwordField.getText().indexOf(ligne)==-1){
	                        if(valT==0) {
	                            lblTaillemdp.setText("Bon mot de passe");
	                            lblTaillemdp.setBounds(260+Decalage, 229, 200, 14);
	                            lblTaillemdp.setForeground(Color.GREEN);
	                            progressBar.setForeground(Color.GREEN);
	                        }else if(valT == 1) {

	                        }else if (valT ==2) {

	                        }else if(valT == 3) {

	                        }
	                    }
	                }
	                buff.close(); 
	                }		
	            catch (Exception f){
	                System.out.println(f.toString());
	            }
	            fils.add(lblTaillemdp);
	            if(passwordField.getCaretPosition()==0) {
	                fils.remove(lblTaillemdp);
	            }
	            fils.revalidate();
	            fils.repaint();
	        }
	    });
		
		
		passwordField_1.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyReleased(KeyEvent e) {
	            char []mdp1=passwordField.getPassword();
	            char []mdp2=passwordField_1.getPassword();
	            if(new String (mdp1).equals(new String (mdp2))) {
//					
	                        chckbxNewCheckBox.setSelected(true);
	                        chckbxNewCheckBox.revalidate();
	                        chckbxNewCheckBox.repaint();
	                        btnValider.setEnabled(true);
	            }else {
	                chckbxNewCheckBox.setSelected(false);
	                btnValider.setEnabled(false);
	            }
	        }
	    });
	    
		
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				char []mdp1=passwordField.getPassword();
				char []mdp2=passwordField_1.getPassword();
				int save=-1;
				
				System.out.println("0x001");
				if(new String (mdp1).equals(new String (mdp2))) {
					System.out.println("0x002");
					chckbxNewCheckBox.setSelected(true);
					chckbxNewCheckBox.revalidate();
					chckbxNewCheckBox.repaint();
					if(comboBox.getSelectedItem().equals("Technicien")) {
						User temp = new User(textFieldNom.getText(),textFieldPrenom.getText(),1,textFieldPseudo.getText(), Application.getShaApp(new String (mdp1)));
						save = Application.saveUserApp(temp);
						if(save==1000) {
							dispose();
							Ihm continuer=new Ihm();
							continuer.setVisible(true);
						}else if(save==0x003) {
							
						}
					}else if(comboBox.getSelectedItem().equals("Autre")) {
						User temp = new User(textFieldNom.getText(),textFieldPrenom.getText(),0,textFieldPseudo.getText(), Application.getShaApp(new String (mdp1)));
						save = Application.saveUserApp(temp);
						if(save==1000) {
							dispose();
							Ihm continuer=new Ihm();
							continuer.setVisible(true);
						}
					}
				}	
			}
		});
	}
}
