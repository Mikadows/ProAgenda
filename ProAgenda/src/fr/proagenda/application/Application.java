package fr.proagenda.application;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import fr.proagenda.dao.Dao;
import fr.proagenda.classes.User;

/**
 * Classe Application : Gère la partie algo
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
	
	/**
	 * Retourne le résultat de la query en Dao
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
	 * @return : renvoie le mot de passe haché en SHA-256 
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
	
	
}
