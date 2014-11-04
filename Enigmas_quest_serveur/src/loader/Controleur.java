package loader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;

import mappage.*;
import requetes.*;

public class Controleur {

	private static Connection conn;
	private static final String configurationFile = "BD.properties";
	
	/**
	 * Constructeur par défaut.
	 */
	public Controleur() {	
		try {
			  String jdbcDriver, dbUrl, username, password;
			  DatabaseAccessProperties dap = new DatabaseAccessProperties(configurationFile);
			  jdbcDriver	= dap.getJdbcDriver();
			  dbUrl			= dap.getDatabaseUrl();
			  username		= dap.getUsername();
			  password		= dap.getPassword();
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
