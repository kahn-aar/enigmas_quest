package loader;

import java.sql.SQLException;

import mappage.Player;
import mappage.Position;
import requetes.RequeteCombat;
import requetes.RequetePhoto;
import requetes.RequetePlayer;
import requetes.RequetePosition;
import requetes.RequeteQuestion;
import requetes.RequeteQuete;
import requetes.RequeteVideo;

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
