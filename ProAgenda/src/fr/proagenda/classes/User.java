package fr.proagenda.classes;

public class User {	
	private String nom;
	private String prenom;
	private int id_metier;
	private String pseudo;
	private String encryptedPassword;
	
	
	public User(String nom, String prenom,int idMetier, String pseudo, String password) {
		this.nom = nom;
		this.prenom = prenom;
		this.id_metier= idMetier;
		this.pseudo = pseudo;
		this.encryptedPassword = password;
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
		return id_metier;
	}

	public String getPseudo() {
		return pseudo;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}
}