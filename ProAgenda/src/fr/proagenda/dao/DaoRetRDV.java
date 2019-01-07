package fr.proagenda.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.proagenda.classes.PropertyAcces;
import fr.proagenda.classes.Rdv;

/**
 * Classe Dao pour les rendez-vous
 */
public class DaoRetRDV {
	private ArrayList<String> ret;
	
	private static PropertyAcces prop = new PropertyAcces();
	private static String url = "jdbc:mysql://"+prop.getDbAddress()+":"+prop.getDbPort()+"/proagenda?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
	private static String login = prop.getDbLogin();
	private static String passwd = prop.getDbPswd();
	static Connection cn =null;
	static PreparedStatement pstmt =null;
	static ResultSet rs =null;
	static Statement st =null;
	
	/**
	 * Constructeur retournant la liste des rendez-vous pour un id de compte donn�
	 * @param idAccount
	 */
	public DaoRetRDV(int idAccount) {
		try {

 			// Etape 1 : Chargement du driver
 			try {
 				Class.forName("com.mysql.cj.jdbc.Driver");
 			}catch(ClassNotFoundException err){
 				System.err.println("Pilote non trouv�..");
 				System.err.println(err);
 				//     System.exit(1) ;
 			}

 			// Etape 2 : r�cup�ration de la connexion
 			try {
 				cn = DriverManager.getConnection(url, login, passwd);
 			}catch(SQLException err) {
 				System.err.println("Connexion impossible");
 				System.err.println(err);
 				//System.exit(1) ;
 			}

 			// Etape 3 : Cr�ation d'un statement
 			st = cn.createStatement();

 			String sql = "SELECT addr_rdv , date_heure_rdv  FROM t_rdv WHERE id_account="+idAccount;

 			// Etape 4 : ex�cution requ�te
 			rs = st.executeQuery(sql);

 			// Si r�cup donn�es alors �tapes 5 (parcours Resultset)
 			ArrayList<String> envoie= new ArrayList<>();

 			while (rs.next()) {
 				envoie.add(rs.getString("date_heure_rdv"));
 				envoie.add(rs.getString("date_heure_rdv"));
 				envoie.add(rs.getString("addr_rdv"));
 				envoie.add("test");
 				setRet(envoie);
 			}
 			System.out.println("Bien marche");
 				
 			
 		} catch (SQLException e) {
 			e.printStackTrace();
 		} finally {
 			try {
 			// Etape 6 : lib�rer ressources de la m�moire.
 				cn.close();
 				st.close();
 			} catch (SQLException e) {
 				e.printStackTrace();
 			}
 		}
	}
	


	public ArrayList<String> getRet() {
		return ret;
	}
	
	public void setRet(ArrayList<String> ret) {
		this.ret = ret;
	}

	/**
	 * Retourne une liste de rendez-vous en fonction de l'idAccount
	 * @param idAccount
	 * @return
	 */
	public static ArrayList<Rdv> getRdvByIdAccount(int idAccount){
		ArrayList<Rdv> list = new ArrayList<Rdv>();
		
		try {

 			// Etape 1 : Chargement du driver
 			try {
 				Class.forName("com.mysql.cj.jdbc.Driver");
 			}catch(ClassNotFoundException err){
 				System.err.println("Pilote non trouv�..");
 				System.err.println(err);
 				//     System.exit(1) ;
 			}

 			// Etape 2 : r�cup�ration de la connexion
 			try {
 				cn = DriverManager.getConnection(url, login, passwd);
 			}catch(SQLException err) {
 				System.err.println("Connexion impossible");
 				System.err.println(err);
 				//System.exit(1) ;
 			}

 			// Etape 3 : Cr�ation d'un statement
 			st = cn.createStatement();

 			String sql = "SELECT *  FROM t_rdv WHERE id_account="+idAccount;

 			// Etape 4 : ex�cution requ�te
 			rs = st.executeQuery(sql);

 			// Si r�cup donn�es alors �tapes 5 (parcours Resultset)
 			while (rs.next()) {
 				Rdv r = new Rdv(rs.getDate("date_heure_rdv"), rs.getString("addr_rdv"), rs.getInt("id_rdv"), rs.getInt("id_account"));
 				list.add(r);
 			}
 			System.out.println("Rdv charg�s");
 				
 			
 		} catch (SQLException e) {
 			e.printStackTrace();
 		} finally {
 			try {
 			// Etape 6 : lib�rer ressources de la m�moire.
 				cn.close();
 				st.close();
 			} catch (SQLException e) {
 				e.printStackTrace();
 			}
 		}
		
		
		return list;
		
	}

}
