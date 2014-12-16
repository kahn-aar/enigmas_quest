package com.enigma.jdbc.request;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.enigma.jdbc.mapping.Position;
import com.enigma.jdbc.mapping.Quetes;

/**
 * regroupe tout les appels à la base MySQL concernant les quetes
 * @author leovidal
 *
 */
public class RequeteQuete {

	/**
	 * renvoie toutes quetes pour une position donnée
	 * @param conn
	 * @param position
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Quetes> getQuetesByPosition(Connection conn, Position position) throws SQLException{
		ArrayList<Quetes> result = new ArrayList<Quetes>();
		RequeteQuestion rq = new RequeteQuestion();
		RequeteCombat rc = new RequeteCombat();
		RequetePhoto rp = new RequetePhoto();
		RequeteVideo rv = new RequeteVideo();
		
		result.addAll(rq.getAllQuestionByPosition(conn, position));
		result.addAll(rc.getAllCombatByPosition(conn, position));
		result.addAll(rp.getPhotosByPosition(conn, position));
		result.addAll(rv.getVideosByPosition(conn, position));

		return result;
	}
	
	/**
	 * renvoie la quete qui a le numéro demandé
	 * @param conn
	 * @param num
	 * @return
	 * @throws SQLException
	 */
	public Quetes getQueteByNum(Connection conn, int num) throws SQLException{
		Quetes result;
		RequeteQuestion rq = new RequeteQuestion();
		RequeteCombat rc = new RequeteCombat();
		RequetePhoto rp = new RequetePhoto();
		RequeteVideo rv = new RequeteVideo();
		
		if((result = rq.getQuestionByNum(conn, num)) != null){ 
		}else if((result = rc.getCombatByNum(conn, num)) != null){
		}else if((result = rp.getPhotoByNum(conn, num)) != null){
		}else if((result = rv.getVideoByNum(conn, num)) != null){
		}else{
			result = null;
		}
		
		return result;
	}
	
	public ArrayList<Quetes> getAllQuest(Connection conn)  throws SQLException{
		
		ArrayList<Quetes> result = new ArrayList<Quetes>();
		RequeteQuestion rq = new RequeteQuestion();
		RequeteCombat rc = new RequeteCombat();
		RequetePhoto rp = new RequetePhoto();
		//RequeteVideo rv = new RequeteVideo();
		result.addAll(rc.getAllCombat(conn));
		result.addAll(rq.getAllQuestion(conn));
		result.addAll(rp.getAllPhoto(conn));
		
		return result;
	}
	
}
