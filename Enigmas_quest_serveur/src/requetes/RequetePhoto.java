package requetes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	 * @throws SQLException 
	 */
	public Photo getPhotoByNum(Connection conn, int num) throws SQLException{
		Photo result;
		Position position;
		int positionId;
		String theme;
		
		// Get a statement from the connection
		PreparedStatement st = conn.prepareStatement("SELECT * FROM photo WHERE numero = ?");
		// Execute the query
		st.setInt(1, num);
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			theme = rs.getString("theme");
			positionId = rs.getInt("positionId");
			RequetePosition rp = new RequetePosition();
			position = rp.getPositionById(conn, positionId);
			result = new Photo(position, num, theme);
		} else {
			result = null;
		}
		return result;
	}
	
	/**
	 * retourne un objet photo selon sa position
	 * @param conn
	 * @param position
	 * @return
	 * @throws SQLException 
	 */
	public Photo getPhotoByPosition(Connection conn, Position position) throws SQLException{
		Photo result;
		String theme;
		int num;
		
		// Get a statement from the connection
		PreparedStatement st = conn.prepareStatement("SELECT * FROM photo WHERE positionId = ?");
		// Execute the query
		st.setInt(1, position.getId());
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			theme = rs.getString("theme");
			num = rs.getInt("numero");
			result = new Photo(position, num, theme);
		} else {
			result = null;
		}
		
		return result;
	}
	
	/**
	 * retourne une liste de toute les photos du theme demandé
	 * @param conn
	 * @param theme
	 * @return
	 * @throws SQLException 
	 */
	public ArrayList<Photo> getPhotosByTheme(Connection conn, String theme) throws SQLException{
		ArrayList<Photo> result = new ArrayList<Photo>();
		int idPosition, numero;
		
		// Get a statement from the connection
		PreparedStatement st = conn.prepareStatement("SELECT * FROM photo WHERE theme = ?");
		// Execute the query
		st.setString(1, theme);
		ResultSet rs = st.executeQuery();

		// Loop through the result set
		while (rs.next()) {
			idPosition = rs.getInt("positionId");
			numero = rs.getInt("numero");
			
			RequetePosition rp = new RequetePosition();
			result.add(new Photo(rp.getPositionById(conn, idPosition), numero, theme));
		}
		// Close the result set, statement and the connection
		rs.close();
		return result;
	}
	
	/**
	 * renvoie toute les photos pour une position donnée
	 * @param conn
	 * @param position
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Photo> getPhotosByPosition(Connection conn, Position position) throws SQLException{
		ArrayList<Photo> result = new ArrayList<Photo>();
		int numero;
		String theme;
		
		// Get a statement from the connection
		PreparedStatement st = conn.prepareStatement("SELECT * FROM photo WHERE positionId = ?");
		// Execute the query
		st.setInt(1, position.getId());
		ResultSet rs = st.executeQuery();

		// Loop through the result set
		while (rs.next()) {
			numero = rs.getInt("numero");
			theme = rs.getString("theme");
			
			result.add(new Photo(position, numero, theme));
		}
		// Close the result set, statement and the connection
		rs.close();
		return result;
	}
}
