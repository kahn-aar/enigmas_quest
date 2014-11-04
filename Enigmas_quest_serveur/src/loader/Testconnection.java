package loader;

import java.sql.SQLException;

import requetes.RequetePlayer;

public class Testconnection {

	public static void main( String args[] ) throws SQLException {
		 Controleur controleur = new Controleur();
		 
		 // test de requete
		 
		 RequetePlayer rp = new RequetePlayer();
		 //rp.nbQueteByLogin(Controleur.getConn(), "Bob");
		 
		 //rp.positionByLogin(Controleur.getConn(), "Bob");
		 
		 rp.allPlayers(Controleur.getConn());
		 
		 //Fin du test OK
	 }
}
