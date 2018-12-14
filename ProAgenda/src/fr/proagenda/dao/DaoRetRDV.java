package fr.proagenda.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.proagenda.classes.PropertyAcces;

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
	
	public DaoRetRDV() {
		try {

 			// Etape 1 : Chargement du driver
 			try {
 				Class.forName("com.mysql.cj.jdbc.Driver");
 			}catch(ClassNotFoundException err){
 				System.err.println("Pilote non trouvé..");
 				System.err.println(err);
 				//     System.exit(1) ;
 			}

 			// Etape 2 : récupération de la connexion
 			try {
 				cn = DriverManager.getConnection(url, login, passwd);
 			}catch(SQLException err) {
 				System.err.println("Connexion impossible");
 				System.err.println(err);
 				//System.exit(1) ;
 			}

 			// Etape 3 : Création d'un statement
 			st = cn.createStatement();

 			String sql = "SELECT addr_rdv , date_heure_rdv  FROM t_rdv WHERE id_account=1";

 			// Etape 4 : exécution requête
 			rs = st.executeQuery(sql);

 			// Si récup données alors étapes 5 (parcours Resultset)
 			ArrayList<String> envoie= new ArrayList<>();

 			while (rs.next()) {
 				envoie.add(rs.getString("date_heure_rdv"));
 				envoie.add(rs.getString("date_heure_rdv"));
 				envoie.add(rs.getString("addr_rdv"));
 				envoie.add("test");
 				setRet(envoie);
 			}
 			System.out.println("bien amrche");
 				
 			
 		} catch (SQLException e) {
 			e.printStackTrace();
 		} finally {
 			try {
 			// Etape 6 : libérer ressources de la mémoire.
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
