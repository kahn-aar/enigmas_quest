package com.enigma.jdbc.request;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.enigma.jdbc.mapping.*;

public class RequeteVideo {

	/**
	 * retourne une video selon son numero
	 * @param conn
	 * @param num
	 * @return
	 * @throws SQLException 
	 */
	public Video getVideoByNum(Connection conn, int num) throws SQLException{
		Video result;
		Position position;
		int positionId;
		String theme, url;
		
		// Get a statement from the connection
		PreparedStatement st = conn.prepareStatement("SELECT * FROM video WHERE numero = ?");
		// Execute the query
		st.setInt(1, num);
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			theme = rs.getString("theme");
			positionId = rs.getInt("positionId");
			url = rs.getString("url");
			RequetePosition rp = new RequetePosition();
			position = rp.getPositionById(conn, positionId);
			result = new Video(position, num, theme, url);
		} else {
			result = null;
		}
		return result;
	}
	
	/**
	 * retourne une video selon sa position
	 * @param conn
	 * @param position
	 * @return
	 * @throws SQLException 
	 */
	public Video getVideoByPosition(Connection conn, Position position) throws SQLException{
		Video result;
		String theme, url;
		int num;
		
		// Get a statement from the connection
		PreparedStatement st = conn.prepareStatement("SELECT * FROM video WHERE positionId = ?");
		// Execute the query
		st.setInt(1, position.getId());
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			theme = rs.getString("theme");
			num = rs.getInt("numero");
			url = rs.getString("url");
			result = new Video(position, num, theme, url);
		} else {
			result = null;
		}
		
		return result;
	}
	
	/**
	 * retourne la liste de toutes les vidéos du theme choisi
	 * @param conn
	 * @param theme
	 * @return
	 * @throws SQLException 
	 */
	public ArrayList<Video> getVideosByTheme(Connection conn, String theme) throws SQLException{
		ArrayList<Video> result = new ArrayList<Video>();
		int idPosition, numero;
		String url;
		
		// Get a statement from the connection
		PreparedStatement st = conn.prepareStatement("SELECT * FROM video WHERE theme = ?");
		// Execute the query
		st.setString(1, theme);
		ResultSet rs = st.executeQuery();

		// Loop through the result set
		while (rs.next()) {
			idPosition = rs.getInt("positionId");
			numero = rs.getInt("numero");
			url = rs.getString("url");
			RequetePosition rp = new RequetePosition();
			result.add(new Video(rp.getPositionById(conn, idPosition), numero, theme, url));
		}
		// Close the result set, statement and the connection
		rs.close();
		return result;
	}
	
	/**
	 * renvoie toutes les videos pour une position donnée
	 * @param conn
	 * @param position
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Video> getVideosByPosition(Connection conn, Position position) throws SQLException{
		ArrayList<Video> result = new ArrayList<Video>();
		int numero;
		String theme, url;
		
		// Get a statement from the connection
		PreparedStatement st = conn.prepareStatement("SELECT * FROM video WHERE positionId = ?");
		// Execute the query
		st.setInt(1, position.getId());
		ResultSet rs = st.executeQuery();

		// Loop through the result set
		while (rs.next()) {
			numero = rs.getInt("numero");
			theme = rs.getString("theme");
			url = rs.getString("url");
			result.add(new Video(position, numero, theme, url));
		}
		// Close the result set, statement and the connection
		rs.close();
		return result;
	}
}
