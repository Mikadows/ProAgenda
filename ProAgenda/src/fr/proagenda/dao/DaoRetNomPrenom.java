package fr.proagenda.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DaoRetNomPrenom {
	private ArrayList<String> ret;
	
	static String url = "jdbc:mysql://localhost:3306/proagenda?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
	static String login = "root";
	static String passwd = "toor";
	static Connection cn =null;
	static PreparedStatement pstmt =null;
	static ResultSet rs =null;
	static Statement st =null;
	
	public DaoRetNomPrenom() {
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

 			String sql = "SELECT nom_account , prenom_account , id_account FROM t_account WHERE id_job=1 ORDER BY nom_account";

 			// Etape 4 : ex�cution requ�te
 			rs = st.executeQuery(sql);

 			// Si r�cup donn�es alors �tapes 5 (parcours Resultset)
 			ArrayList<String> envoie= new ArrayList<>();
 			
 			while (rs.next()) {
 				envoie.add(rs.getString("nom_account"));
 				envoie.add(rs.getString("prenom_account"));
 				setRet(envoie);
 			}
 				
 			
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
	
	

}
