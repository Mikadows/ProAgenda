package fr.proagenda.classes;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Classe permetant d'acceder au fichier properties
 * @author gSiwiorek
 *
 */
public class PropertyAcces {
	private String dbAddress;
	private String dbLogin;
	private String dbPswd;
	private String dbPort;
	
	public PropertyAcces() {
		super();
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("C:/Users/mathieu/Documents/config.properties");

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			this.dbAddress = prop.getProperty("dbAddress");
			this.dbLogin = prop.getProperty("dbLogin");
			this.dbPswd = prop.getProperty("dbPassword");
			this.dbPort = prop.getProperty("dbPort");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	  }
	
	public String getDbAddress() {
		return dbAddress;
	}

	public String getDbPort() {
		return dbPort;
	}

	public String getDbLogin() {
		return dbLogin;
	}
	
	public String getDbPswd() {
		return dbPswd;
	}
	
}
