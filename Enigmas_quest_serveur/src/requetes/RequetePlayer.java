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
 * Regroupe tout les appels à la base MySQL concernant les joueurs
 * @author leovidal
 *
 */
public class RequetePlayer {

	/**
	 * 
	 * @param conn
	 * @param nom
	 * @return nombre de quete pour un joueur donné
	 * @throws SQLException
	 */
	public int nbQueteByLogin(Connection conn, String nom) throws SQLException{
		int result;
		// Get a statement from the connection
		PreparedStatement st = conn.prepareStatement("SELECT quetesRealisees FROM player WHERE login = ?");
		// Execute the query
		st.setString(1, nom);
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			result = rs.getInt("quetesRealisees");
		} else {
			result = 0;
		}
		System.out.println(result);
		return result;
	}
	
	/**
	 * 
	 * @param conn
	 * @param nom
	 * @return nombre de points pour un joueur donné
	 * @throws SQLException
	 */
	public int nbPointsByLogin(Connection conn, String nom) throws SQLException{
		int result;
		// Get a statement from the connection
		PreparedStatement st = conn.prepareStatement("SELECT points FROM player WHERE login = ?");
		// Execute the query
		st.setString(1, nom);
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			result = rs.getInt("points");
		} else {
			result = 0;
		}
		System.out.println(result);
		return result;
	}
	
	/**
	 * 
	 * @param conn
	 * @param nom
	 * @return position du joueur choisi
	 * @throws SQLException
	 */
	public Position positionByLogin(Connection conn, String nom) throws SQLException{
		float longitude, latitude;
		int id = 0;
		
		// Get a statement from the connection
		PreparedStatement st = conn.prepareStatement("SELECT longitude, latitude, positionId FROM player J, positions P WHERE J.positionId = P.id AND login = ?");
		// Execute the query
		st.setString(1, nom);
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			longitude = rs.getFloat("longitude");
			latitude = rs.getFloat("latitude");
			id = rs.getInt("positionId");
			System.out.println(longitude + "\n");
			System.out.println(latitude + "\n");
		} else {
			longitude = 0;
			latitude = 0;
		}
		
		Position position = new Position(id, longitude, latitude);
		return position;
	}
	
	/**
	 * retourne la liste de tout les joueurs inscrits dans la base
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Player> allPlayers(Connection conn) throws SQLException{
		ArrayList<Player> result = new ArrayList<Player>();
		String login, salt;
		Position position;
		int idPosition;
		int quetes;
		int points;
		
		// Get a statement from the connection
		Statement stmt = conn.createStatement();

		// Execute the query
		ResultSet rs = stmt.executeQuery("SELECT * FROM player");

		// Loop through the result set
		while (rs.next()) {
			login = rs.getString("login");
			salt = rs.getString("salt");
			idPosition = rs.getInt("positionId");
			position = positionByLogin(conn, login);
			quetes = rs.getInt("quetesRealisees");
			points = rs.getInt("points");

			result.add(new Player(login, salt, points, quetes, position));
		}
		// Close the result set, statement and the connection
		rs.close();
		stmt.close();
		return result;
	}
	
	/**
	 * Ajoute un joueur
	 * @param conn
	 * @param player
	 * @throws SQLException
	 */
	public void addPlayer(Connection conn, Player player) throws SQLException{
		
		PreparedStatement st = conn.prepareStatement("INSERT INTO player(login, salt) VALUES (?,?)");
		// Execute the query
		st.setString(1, player.getLogin());
		st.setString(2, player.getSalt());
		st.executeQuery();
		
	}
	
	/**
	 * Change la position d'un joueur
	 * @param conn
	 * @param player
	 * @param position
	 * @throws SQLException
	 */
	public void changePosition(Connection conn, Player player, Position position) throws SQLException{
		
		//add position
		RequetePosition rp = new RequetePosition();
		rp.addPosition(conn, position.getLongitude(), position.getLatitude());
		
		//get position's id
		int positionId = rp.getIdByCoor(conn, position.getLongitude(), position.getLatitude());
		
		//update player
		PreparedStatement st = conn.prepareStatement("UPDATE player SET position = ? WHERE login = ? ");
		// Execute the query
		st.setInt(1, positionId);
		st.setString(2, player.getLogin());
		st.executeQuery();
		
	}
	
	/**
	 * retourne un objet joueur selon son login
	 * @param conn
	 * @param login
	 * @return
	 * @throws SQLException
	 */
	public Player getPlayerByLogin(Connection conn, String login) throws SQLException{
		Player result;
		int points, quetes, positionId;
		String salt;
		
		// Get a statement from the connection
		PreparedStatement st = conn.prepareStatement("SELECT * FROM player WHERE login = ?");
		// Execute the query
		st.setString(1, login);
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			points = rs.getInt("points");
			salt = rs.getString("salt");
			quetes = rs.getInt("quetesRealisees");
			positionId = rs.getInt("positionId");
			RequetePosition rp = new RequetePosition();
			result = new Player(login, salt,  points, quetes, rp.getPositionById(conn, positionId));
		} else {
			result = null;
		}
		
		return result;
	}
}
