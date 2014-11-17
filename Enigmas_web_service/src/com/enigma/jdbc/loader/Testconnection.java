package com.enigma.jdbc.loader;

import java.sql.SQLException;

import com.enigma.jdbc.request.*;

public class Testconnection {

	public static void main( String args[] ) throws SQLException {
		Controleur controleur = new Controleur();
		 
		// test de requete
		// objet requeteur
		RequetePlayer rp = new RequetePlayer();
		RequeteQuestion rq = new RequeteQuestion();
		RequeteCombat rc = new RequeteCombat();
		RequetePhoto rph = new RequetePhoto();
		RequeteVideo rv = new RequeteVideo();
		RequeteQuete rquete = new RequeteQuete();
		RequetePosition rpos = new RequetePosition();
		 
		//Fin du test OK
	 }
}
