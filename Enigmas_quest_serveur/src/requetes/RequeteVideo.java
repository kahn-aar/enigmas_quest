package requetes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import mappage.*;

public class RequeteVideo {

	/**
	 * retourne une video selon son numero
	 * @param conn
	 * @param num
	 * @return
	 */
	public Video getVideoByNum(Connection conn, int num){
		
	}
	
	/**
	 * retourne une video selon sa position
	 * @param conn
	 * @param position
	 * @return
	 */
	public Video getVideoByPosition(Connection conn, Position position){
		
	}
	
	/**
	 * retourne la liste de toutes les vidéos du theme choisi
	 * @param conn
	 * @param theme
	 * @return
	 */
	public ArrayList<Video> getVideosByTheme(Connection conn, String theme){
		
	}
}
