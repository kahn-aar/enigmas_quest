package com.enigma.jdbc.loader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.enigma.jdbc.request.RequeteCombat;
import com.enigma.jdbc.request.RequetePhoto;
import com.enigma.jdbc.request.RequetePlayer;
import com.enigma.jdbc.request.RequetePosition;
import com.enigma.jdbc.request.RequeteQuestion;
import com.enigma.jdbc.request.RequeteQuete;
import com.enigma.jdbc.request.RequeteVideo;

/**
 * Classe controleur singleton
 * @author teamut
 *
 */
public class Controleur {
	
	private static final String configurationFile = "BD.properties";

	private static Controleur instance = null;
	
	private static Connection conn;
	
	
	/**
	 * Get instance
	 * @return
	 */
	public static Controleur getInstance() {
		if(instance == null) {
			instance = new Controleur();
		}
		return instance;
		
	}
	/**
	 * Constructeur par d�faut.
	 */
	public Controleur() {	
		try {
			  String jdbcDriver, dbUrl, username, password;
			  /*DatabaseAccessProperties dap = new DatabaseAccessProperties(configurationFile);
			  jdbcDriver	= dap.getJdbcDriver();
			  dbUrl			= dap.getDatabaseUrl();
			  username		= dap.getUsername();
			  password		= dap.getPassword();*/
			  jdbcDriver = "com.mysql.jdbc.Driver";
			  dbUrl = "jdbc:mysql://mysql2.alwaysdata.com/bidoul_604";
			  username = "bidoul_quest";
			  password = "ift604";
		      // Load the database driver
		      Class.forName(jdbcDriver) ;
		      // Get a connection to the database
		      conn = DriverManager.getConnection(dbUrl, username, password);
			  // Print information about connection warnings
			  SQLWarningsExceptions.printWarnings(conn); 
		  }
		  catch( SQLException se ) {
			  // Print information about SQL exceptions
			  SQLWarningsExceptions.printExceptions(se);
			  
		      return;
		  }
		  catch( Exception e ) {
		      System.err.println( "Exception: " + e.getMessage()) ;
		      e.printStackTrace();
		      return;
		  }	
		if(conn != null){
			System.out.println("Connected to database");
		}else{
			System.out.println("Error during the connection");
		}
	}
	/**
	 * Accesseur permettant de récupérer la connexion
	 * @return connexion
	 */
	public static Connection getConn() {
		return conn;
	}
	/**
	 * Permet de modifier la connexion
	 * @param conn
	 */
	public static void setConn(Connection conn) {
		Controleur.conn = conn;
	}
}
