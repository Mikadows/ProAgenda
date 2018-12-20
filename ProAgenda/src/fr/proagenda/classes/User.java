package fr.proagenda.classes;

public class User {	
	
	private String nom;
	private String prenom;
	private int idMetier;
	private int idCompte;
	private String pseudo;
	private String encryptedPassword;
	
	
	public User(String nom, String prenom,int idMetier,int idCompte, String pseudo, String password) {
		this.nom = nom;
		this.prenom = prenom;
		this.idMetier= idMetier;
		this.pseudo = pseudo;
		this.encryptedPassword = password;
		this.idCompte=idCompte;
	}
	
	public User(String nom, String prenom,int idMetier, String pseudo, String password) {
		this.nom = nom;
		this.prenom = prenom;
		this.idMetier= idMetier;
		this.pseudo = pseudo;
		this.encryptedPassword = password;
		this.idCompte = 0;
	}
	
	public User(String pseudo,String password) {
		this.pseudo = pseudo;
		this.encryptedPassword = password;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}
	
	public int getId_metier() {
		return idMetier;
	}

	public int getIdCompte() {
		return idCompte;
	}

	public String getPseudo() {
		return pseudo;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}
}