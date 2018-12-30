package fr.proagenda.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.proagenda.classes.PropertyAcces;
import fr.proagenda.classes.User;

/**
 * Classe Data Acces Object : Permmet d'accéder à la BDD
 * @author Gwendal
 *
 */
public class Dao {

	private static PropertyAcces prop = new PropertyAcces();
	private static String url = "jdbc:mysql://"+prop.getDbAddress()+":"+prop.getDbPort()+"/proagenda?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
	private static String login = prop.getDbLogin();
	private static String passwd = prop.getDbPswd();
	private static Connection cn =null;
	private static PreparedStatement pstmt =null;
	private static ResultSet rs =null;
	private static Statement st =null;
	
	/**
	 * Compare les identifiants
	 * @author Mathieu
	 * @param loginT : demande le pseudo donné dans la fenêtre de log
	 * @param mdpT : demande le mot de passe donné dans la fenetre de log -> enregistré en SHA-256 ! 
	 * @return : renvoie 1 si login et mdp sont bon, 0 si 1 des 2 est mauvais ou erreur 
	 */
	public static int comparerLoginsDao(String loginT, String mdpT) {

		int ret = 0;
		
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

			String sql = "SELECT login_account , mdp_account  FROM t_account";

			// Etape 4 : exécution requête
			rs = st.executeQuery(sql);

			// Si récup données alors étapes 5 (parcours Resultset)

			while (rs.next()) {
				if(rs.getString("login_account").equals(loginT)) {
					System.out.println("test 1 OK ");
					System.out.println("mdp arg : "+ mdpT );
					System.out.println("mdp bdd : "+ rs.getString("mdp_account"));
					if(rs.getString("mdp_account").equals(mdpT)) {
						System.out.println("test 2 OK ");
						ret = 1;
					}
				}
//				System.out.println(rs.getString("login_account"));
//				System.out.println(rs.getString("mdp_account"));
				
			}
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
	
	return ret;
	}

	/**
	 * Modifie le mot de passe
	 * @param pseudo
	 * @param utilisateur
	 */
	public static void modifierPseudoDao(String pseudo, User utilisateur) {
	

			// Etape 1 : Chargement du driver
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			}catch(ClassNotFoundException err){
				System.err.println("Pilote non trouvé..");
				System.err.println(err);
				System.exit(1) ;
			}

			// Etape 2 : récupération de la connexion
			try {
				cn = DriverManager.getConnection(url, login, passwd);
			}catch(SQLException err) {
				System.err.println("Connexion impossible");
				System.err.println(err);
				System.exit(1) ;
			}

			// Etape 3 : Création d'un statement
			try {
				pstmt = cn.prepareStatement("UPDATE t_account SET login_account = ? WHERE login_account=?");
				pstmt.setString(1, pseudo);
				pstmt.setString(2, utilisateur.getPseudo());
				pstmt.executeUpdate();
			}catch (Exception e){
				System.err.println("requete non effectuee");
				System.err.println(e);
				System.exit(1);
			}
	
	
		try {
		// Etape 6 : libérer ressources de la mémoire.
			cn.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sauvegarde un utilisateur 
	 * @param user
	 * @return
	 */
	public static int saveUserDao(User user) {
		int ret =1;
		
			// Etape 1 : Chargement du driver
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			}catch(ClassNotFoundException err){
				System.err.println("Pilote non trouvé..");
				System.err.println(err);
				System.exit(1) ;
				ret = 0x001;
			}

			// Etape 2 : récupération de la connexion
			try {
				cn = DriverManager.getConnection(url, login, passwd);
			}catch(SQLException err) {
				System.err.println("Connexion impossible");
				System.err.println(err);
				System.exit(1) ;
				ret = 0x002;
			}

			// Etape 3 : Création d'un statement
			try {
				pstmt = cn.prepareStatement("INSERT INTO `t_account` ( `nom_account`, `prenom_account`, `login_account`, `mdp_account`, `id_job`) VALUES ( ?, ?, ?, ?, ?);");
				pstmt.setString(1, user.getNom());
				pstmt.setString(2, user.getPrenom());
				pstmt.setString(3, user.getPseudo());
				pstmt.setString(4, user.getEncryptedPassword());
				pstmt.setInt(5, user.getId_metier());
				pstmt.executeUpdate();
				ret = 1000;
			}catch (Exception e){
				System.err.println("requete non effectuee");
				System.err.println(e);
				//System.exit(1);
				ret = 0x003;
			}
	
	
		try {
		// Etape 6 : libérer ressources de la mémoire.
			cn.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			ret = 0x004;
		}
		
		return ret;		
	}
	
	/**
	 * Retourne un user complet après l'identification
	 * @param u
	 * @return
	 */
	public static User retAllDataByUser(User u) {
		User u1 = null;
		
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

			String sql = "SELECT * FROM t_account WHERE login_account=\""+u.getPseudo()+"\"";

			// Etape 4 : exécution requête
			rs = st.executeQuery(sql);

			// Si récup données alors étapes 5 (parcours Resultset)
			while(rs.next()) {
				User ret = new User(rs.getString("nom_account"), rs.getString("prenom_account"), rs.getInt("id_job"), rs.getInt("id_account"), rs.getString("login_account"), rs.getString("mdp_account"));
				System.out.println(ret.getId_metier());
				u1 = ret;				
			}

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
		
		return u1;
	}
	
}
