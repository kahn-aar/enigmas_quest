package requetes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import mappage.*;

/**
 * regroupe tout les appels à la base MySQL concernant la table photo
 * @author leovidal
 *
 */
public class RequetePhoto {

	/**
	 * retourne un objet photo en fonction de son numero
	 * @param conn
	 * @param num
	 */
	public Photo getPhotoByNum(Connection conn, int num){
		
	}
	
	/**
	 * retourne un objet photo selon sa position
	 * @param conn
	 * @param position
	 * @return
	 */
	public Photo getPhotoByPosition(Connection conn, Position position){
		
	}
	
	/**
	 * retourne une liste de toute les photos du theme demandé
	 * @param conn
	 * @param theme
	 * @return
	 */
	public ArrayList<Photo> getPhotosByTheme(Connection conn, String theme){
		
	}
}
