package requetes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import mappage.*;

/**
 * regroupe tout les appels à la base MySQL concernant la table positions
 * @author leovidal
 *
 */
public class RequetePosition {

	/**
	 * 
	 * @param conn
	 * @param id
	 * @return objet position
	 * @throws SQLException
	 */
	public Position getPositionById(Connection conn, int id) throws SQLException{
		Position result;
		float longitude;
		float latitude;
		// Get a statement from the connection
		PreparedStatement st = conn.prepareStatement("SELECT longitude, latitude FROM positions WHERE id = ?");
		// Execute the query
		st.setInt(1, id);
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			longitude = rs.getFloat("longitude");
			latitude = rs.getFloat("latitude");
			result = new Position(id, longitude, latitude);
		} else {
			result = null;
		}
		System.out.println(result);
		return result;
	}
	
	/**
	 * ajoute une position
	 * @param conn
	 * @param longitude
	 * @param latitude
	 * @throws SQLException
	 */
	public void addPosition(Connection conn, float longitude, float latitude) throws SQLException{
		PreparedStatement st = conn.prepareStatement("INSERT INTO positions(longitude, latitude) VALUES (?,?)");
		// Execute the query
		st.setFloat(1, longitude);
		st.setFloat(2, latitude);
		st.executeUpdate();
	}
	
	/**
	 * 
	 * @param conn
	 * @param longitude
	 * @param latitude
	 * @return
	 * @throws SQLException
	 */
	public int getIdByCoor(Connection conn, float longitude, float latitude) throws SQLException{
		int positionId;
		PreparedStatement st = conn.prepareStatement("SELECT id FROM positions WHERE longitude = ? AND latitude = ?");
		// Execute the query
		st.setFloat(1, longitude);
		st.setFloat(2, latitude);
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			positionId = rs.getInt("id");
		}else{
			positionId = 0;
		}
		
		return positionId;
	}
	
	
}
