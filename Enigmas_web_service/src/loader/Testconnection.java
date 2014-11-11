package loader;

import java.sql.SQLException;

import requetes.RequeteCombat;
import requetes.RequetePhoto;
import requetes.RequetePlayer;
import requetes.RequeteQuestion;
import requetes.RequeteQuete;
import requetes.RequeteVideo;

public class Testconnection {

	public static void main( String args[] ) throws SQLException {
		 Controleur controleur = new Controleur();
		 
		 // test de requete
		 
			RequetePlayer rp = new RequetePlayer();
			rp.allPlayers(controleur.getConn());
			RequeteQuestion rq = new RequeteQuestion();
			RequeteCombat rc = new RequeteCombat();
			RequetePhoto rph = new RequetePhoto();
			RequeteVideo rv = new RequeteVideo();
			RequeteQuete rquete = new RequeteQuete();
			
		 
		 
		 //Fin du test OK
	 }
}
