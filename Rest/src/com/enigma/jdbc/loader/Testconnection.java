package com.enigma.jdbc.loader;

import java.sql.SQLException;
import java.util.ArrayList;

import com.enigma.jdbc.mapping.Question;
import com.enigma.jdbc.request.RequeteCombat;
import com.enigma.jdbc.request.RequetePhoto;
import com.enigma.jdbc.request.RequetePlayer;
import com.enigma.jdbc.request.RequetePosition;
import com.enigma.jdbc.request.RequeteQuestion;
import com.enigma.jdbc.request.RequeteQuestionReponse;
import com.enigma.jdbc.request.RequeteQuete;
import com.enigma.jdbc.request.RequeteVideo;

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
		
		RequeteQuestionReponse rrr = new RequeteQuestionReponse();
		
		ArrayList<Question> result = rrr.getAllQuestionByLogin(controleur.getConn(), "bob");
		for (int i = 0; i<result.size(); i++){
			System.out.println(result.get(i).getQuestion());
		}
		
	 }
}
