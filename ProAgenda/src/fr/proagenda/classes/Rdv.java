package fr.proagenda.classes;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class décrivant un rendez-vous
 * @author Gwendal
 *
 */
public class Rdv {
	private Date dateFormat;
	private String date;
	private String heure;
	private String adresse;
	private int idRdv;
	private int idAccount;

	/**
	 * Constructeur
	 * @param date
	 * @param adresse
	 * @param idRdv
	 * @param idAccount
	 */
	public Rdv(Date date, String adresse, int idRdv, int idAccount) {
		SimpleDateFormat formater = null;
		
		formater = new SimpleDateFormat("dd-MM-yy");
		this.date = formater.format(date);
		formater = new SimpleDateFormat("hh:mm:ss");
		this.heure = formater.format(date);
		this.adresse = adresse;
		this.idRdv = idRdv;
		this.idAccount = idAccount;
	}
	
	public String getHeure() {
		return heure;
	}

	public String getAdresse() {
		return adresse;
	}		
	
	public Date getDateFormat() {
		return dateFormat;
	}

	public String getDate() {
		return date;
	}
	
	public int getIdRdv() {
		return idRdv;
	}

	public int getIdAccount() {
		return idAccount;
	}


}
