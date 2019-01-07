package fr.proagenda.application;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;

import fr.proagenda.dao.Dao;
import fr.proagenda.dao.DaoRetNomPrenom;
import fr.proagenda.dao.DaoRetRDV;
import fr.proagenda.classes.Rdv;
import fr.proagenda.classes.User;

/**
 * Classe Application : G�re la partie algo
 * @author Gwendal
 *
 */
public class Application {
	
	public static int saveUserApp(User u) {
		return Dao.saveUserDao(u);
	}
	
	
	/**
	 * Modifie le pseudo en base
	 * @param pseudo
	 * @param utilisateur
	 */
	public static void modifPseudoApp(String pseudo, User utilisateur) {
		Dao.modifierPseudoDao(pseudo, utilisateur);
	}
	
	public static void modifMdpApp(String mdp, User utilisateur) {
		Dao.modifierMDPDao(mdp, utilisateur);
	}
	
	/**
	 * Retourne le r�sultat de la query en Dao
	 * @param loginT
	 * @param mdpT
	 * @return
	 */
	public static int getResultsCmpLogins(String loginT, String mdpT) {
		return Dao.comparerLoginsDao(loginT, mdpT);
	}

	/**
	 * Encode en SHA256
	 * @author Mathieu
	 * @param input : mot de passe en clair 
	 * @return : renvoie le mot de passe hach� en SHA-256 
	 */
	public static String getShaApp(String input) { 
  
        try { 
  
            // Static getInstance method is called with hashing SHA 
            MessageDigest md = MessageDigest.getInstance("SHA-256"); 
  
            // digest() method called 
            // to calculate message digest of an input 
            // and return array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
  
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
  
            return hashtext; 
        } 
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            System.out.println("Exception thrown"
                               + " for incorrect algorithm: " + e); 
  
            return null; 
        } 
    }
	
	/**
	 * fonction permettant de r�cupr�rer les nom et pr�nom des techniciens
	 * @return test : ArrayList<Object> contenant nom pr�nom
	 */
	public ArrayList<Object> ihmtoDAOGetNomPrenom(){
		DaoRetNomPrenom data = new DaoRetNomPrenom();
		ArrayList<Object> test = data.getRet();
		
		return test;
	}
	
	/**
	 * fonction permettant de r�cupr�rer les nom et pr�nom des techniciens
	 * @return test : ArrayList<Object> contenant nom pr�nom
	 */
	public ArrayList<User> ihmtoDAOGetNomPrenomUSER(){
		DaoRetNomPrenom data = new DaoRetNomPrenom();
		ArrayList<User> test = data.getRet2();
		
		return test;
	}
	
	/**
	 * fonction permettant de r�cupr�rer lesrendez-vous des techniciens
	 * @return test : ArrayList<Object> contenant date heure adresse string
	 */
	public ArrayList<String> ihmToDAOGetRdv(int idAccount){
		DaoRetRDV data = new DaoRetRDV(idAccount);
		ArrayList<String> test = data.getRet();
		
		return test;
	}
	
	/**
	 * permet de r�cup�rer l'id du compte pass� en parametre
	 * @author mathieu
	 * @param nomPrenom : String de la forme : "Nom Pr�nom"
	 * @param users : ArrayList<Users> contenant tous les techniciens
	 * @return id : val de l'id ou -1 si pas de valeur
	 */
	public static int retIDCompteWithName(String nomPrenom, ArrayList<User> users) {
		int valID=-1;
		for(int i = 0; i < users.size(); i++) {
			if(nomPrenom.equals(users.get(i).getNom()+" "+users.get(i).getPrenom())) {
				valID=users.get(i).getIdCompte();
			}
		}
		return valID;
	}
	
	/**
	 * Retourne la liste des rdv en fonction de l'idAccount
	 * @param idAccount
	 * @return
	 */
	public static ArrayList<Rdv> getListRdvByIdAccount(int idAccount){
		return DaoRetRDV.getRdvByIdAccount(idAccount);
	}
	
	/**
	 * Retourne un nombre al�atoire entre 1 1et 6
	 * @return
	 */
	public static int getRandomNumber() {
		Random rand = new Random();
		int n = rand.nextInt(6) + 1;	//6 is the maximum and the 1 is our minimum.
		return n;
	}
	
	/**
	 * Retourne un user complet apr�s l'identification
	 * @param u
	 * @return
	 */
	public static User getDataUserByUser(User u) {
		return Dao.retAllDataByUser(u);
	}
	
	/**
	 * Sauvegarde les Rdv
	 * @param rdv
	 * @return
	 */
	public static int saveRdvApp(Rdv rdv) {
		return Dao.saveRdv(rdv);
	}
	
}
