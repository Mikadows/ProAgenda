package fr.proagenda.ihm;

import java.awt.Button;
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
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;

import fr.proagenda.application.Application;
import fr.proagenda.classes.User;


/**
 * Classe IHM
 * @author Mathieu
 *
 */
@SuppressWarnings("serial")
public class Ihm extends JFrame{
	private int height= 900;
	private int width = 500; 

	private JPanel contentPane;
	private JTextField pseudoField;
	private JTextField pseudoField_1;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JMenuBar menuBar_1;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JTextField textFieldPseudo;
	private JRadioButton rdbtnAfficher;
	private JLabel lblTaillemdp;
	private JComboBox<String> comboBox;
	private JComboBox<Object[]> comboBoxObj;
	private JToggleButton tglbtnRetour;
	private JButton btnValider;
	private JCheckBox chckbxNewCheckBox;
	private JProgressBar progressBar ;
	private JTable table_1;
	

	
	/**
	 * Create the frame.
	 * @return 
	 */
	public Ihm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, height, width);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPseudo = new JLabel("Pseudo : ");
		lblPseudo.setBounds(288, 158, 71, 14);
		contentPane.add(lblPseudo);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe :");
		lblMotDePasse.setBounds(257, 202, 102, 14);
		contentPane.add(lblMotDePasse);
		
		pseudoField = new JTextField();
		pseudoField.setBounds(374, 158, 156, 20);
		contentPane.add(pseudoField);
		pseudoField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(374, 202, 156, 20);
		contentPane.add(passwordField);
		
		JLabel lblProagenda = new JLabel("ProAgenda");
        lblProagenda.setFont(new Font("Tahoma", Font.PLAIN, 70));
        lblProagenda.setBounds(282, 11, 340, 85);
        contentPane.add(lblProagenda);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(232, 107, 420, 2);
        contentPane.add(separator_1);
		
		Button validBtn = new Button("Valider");
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
					deuxiemeFenetre(contentPane,next);
				}
				//User test = new User(pseudo,new String (motPasse));
				}
			}
		);
		validBtn.setBounds(412, 255, 70, 22);
		contentPane.add(validBtn);
		
		Button button = new Button("Nouvel utilisateur");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				nouvelUtilisateur(contentPane);
				DefineNouvelUtilisateurFenetre(contentPane);
				
			}
		});
		button.setBounds(374, 338, 156, 22);
		contentPane.add(button);	
	}
	
	/**
	 * 
	 * @param test
	 * @param utilisateur
	 */
	public void deuxiemeFenetre(JPanel test, User utilisateur) {
		test.removeAll();
		
		
		afficheChoixTechnicienOrderByNameUSER(test);

		menuBar_1 = new JMenuBar();
		menuBar_1.setBounds(0, 0, 97, 21);
		test.add(menuBar_1);
		
		JMenu mnCompte_1 = new JMenu("Compte");
		menuBar_1.add(mnCompte_1);
		
		JMenuItem mntmModifierPseudo_1 = new JMenuItem("Modifier  Pseudo");
		mnCompte_1.add(mntmModifierPseudo_1);
		
		JMenuItem mntmModifierMotDe_1 = new JMenuItem("Modifier Mot de passe");
		mnCompte_1.add(mntmModifierMotDe_1);
		
		JMenuItem mntmConsulterLesInformations_1 = new JMenuItem("consulter les informations");
		mnCompte_1.add(mntmConsulterLesInformations_1);
		
		mntmModifierPseudo_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				afficheModifierPseudo(test,utilisateur);
				
			}
		});
		
		mntmModifierMotDe_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				afficheModifierMDP(test,utilisateur);
				
			}
		});
		
		test.revalidate();
		
		test.repaint();
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
	    tglbtnRetour = new JToggleButton("retour");
	    tglbtnRetour.setBounds(0, 0, 121, 23);
	    fils.add(tglbtnRetour); 
	    
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
	    
	    
		tglbtnRetour.addActionListener(new ActionListener() {
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
	
	private void afficheModifierMDP(JPanel fils,User utilisateur) {
		//deuxiemeFenetre(fils);
		
		JLabel lblModifierMotDe = new JLabel("nouveau mot de passe : ");
		lblModifierMotDe.setBounds(106, 127, 356, 14);
		getContentPane().add(lblModifierMotDe);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(268, 124, 339, 20);
		getContentPane().add(passwordField);
		
		JLabel lblTapezLe = new JLabel("tapez le \u00E0 nouveau : ");
		lblTapezLe.setBounds(106, 185, 356, 14);
		getContentPane().add(lblTapezLe);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(268, 182, 339, 20);
		getContentPane().add(passwordField_1);
		
		JButton btnValider = new JButton("Valider");
		btnValider.setBounds(387, 248, 89, 23);
		getContentPane().add(btnValider);
		
		btnValider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				char []mdp1 = passwordField.getPassword();
				char []mdp2 = passwordField_1.getPassword();
				System.out.println("mdp 1 : "+new String (mdp1)+"   mdp 2 : "+new String (mdp2));
				if(Application.getShaApp(new String (mdp1)).equals(Application.getShaApp(new String (mdp2)))) {
					Application.modifPseudoApp(Application.getShaApp(new String (mdp1)),utilisateur);
					System.out.println("bonjour");
				}
			}
		});
		
		fils.revalidate();
		fils.repaint();
	}
	
	private void afficheModifierPseudo(JPanel fils,User utilisateur) {
		//deuxiemeFenetre(fils);
		
		JLabel lblModifierPseudo = new JLabel("nouveau Pseudo : ");
		lblModifierPseudo.setBounds(106, 127, 356, 14);
		getContentPane().add(lblModifierPseudo);
		
		pseudoField = new JTextField();
		pseudoField.setBounds(268, 124, 339, 20);
		getContentPane().add(pseudoField);
		
		JLabel lblTapezLe = new JLabel("tapez le \u00E0 nouveau : ");
		lblTapezLe.setBounds(106, 185, 356, 14);
		getContentPane().add(lblTapezLe);
		
		pseudoField_1 = new JTextField();
		pseudoField_1.setBounds(268, 182, 339, 20);
		getContentPane().add(pseudoField_1);
		
		JButton btnValider = new JButton("Valider");
		btnValider.setBounds(387, 248, 89, 23);
		getContentPane().add(btnValider);
		
		btnValider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String pseudo1 = pseudoField.getText();
				String pseudo2 = pseudoField.getText();
				System.out.println("pseudo 1 : "+new String (pseudo1)+"   pseudo 2 : "+new String (pseudo2));
				if(pseudo1.equals(pseudo2)) {
					Application.modifPseudoApp(pseudo1, utilisateur);
				}
			}
		});
		
		fils.revalidate();
		fils.repaint();
	}
	
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
			System.out.println("test obj : "+itemData.toString());
			//System.out.println("test : "+test.get(i));
			//comboBox.addItem(test.get(i)+" "+test.get(i+1));
		} 		
 		
 		JButton btnValider = new JButton("Valider");
		btnValider.setBounds(600, 30, 89, 23);
		getContentPane().add(btnValider);
 		 
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				afficheRDV(fils, (String)comboBox.getSelectedItem());
			}
		});
	}
	
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
		getContentPane().add(btnValider);
 		 
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Application app = new Application();
				app.retIDCompteWithName((String) comboBox.getSelectedItem(), users);
				afficheRDV(fils, app.retIDCompteWithName((String) comboBox.getSelectedItem(), users));
			}
		});
	}

	public void afficheRDV(JPanel fils,int id_account) {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(250, 70, 400, 200);
		fils.add(scrollPane);
	 
			      
	    table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Date", "Heure", "Adresse", "Autre"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		Application app = new Application();
		ArrayList<String> test = app.ihmToDAOGetRdv(id_account);
		
		DefaultTableModel dtm = (DefaultTableModel) table_1.getModel();
		for(int i = 0; i < test.size(); i+=4 ) {
				dtm.addRow(new Object[] {test.get(i),test.get(i+1),test.get(i+2),test.get(i+3)});
		} 		 
		scrollPane.setViewportView(table_1);
	}
}
