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
 * regroupe tout les appels à la base MySQL concernant les combats
 * @author leovidal
 *
 */
public class RequeteCombat {

	/**
	 * retourne un objet combat selon son numéro
	 * @param conn
	 * @param num
	 * @return
	 * @throws SQLException
	 */
	public Combat getCombatByNum(Connection conn, int num) throws SQLException{
		Combat result;
		String idPlayer1, idPlayer2;
		int numQuestion;
		Question question;
		
		// Get a statement from the connection
		PreparedStatement st = conn.prepareStatement("SELECT * FROM combat WHERE numero = ?");
		// Execute the query
		st.setInt(1, num);
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			idPlayer1 = rs.getString("player1");
			idPlayer2 = rs.getString("player2");
			numQuestion = rs.getInt("question");
			RequetePlayer rp = new RequetePlayer();
			RequeteQuestion rq = new RequeteQuestion();
			question = rq.getQuestionByNum(conn, numQuestion);
			result = new Combat(question.getPosition(), num, rp.getPlayerByLogin(conn, idPlayer1), rp.getPlayerByLogin(conn, idPlayer2), question);
		} else {
			result = null;
		}
		
		
		return result;
	}
	
	/**
	 * retourne tous les combats dans une arraylist d'objet combat
	 * @param conn
	 * @return
	 * @throws SQLException 
	 */
	public ArrayList<Combat> getAllCombat(Connection conn) throws SQLException{
		ArrayList<Combat> result = new ArrayList<Combat>();
		Position position;
		int numC, idPosition, numQ;
		String idPlayer1, idPlayer2;
		Question question;
		Player player1, player2;
		
		// Get a statement from the connection
		Statement stmt = conn.createStatement();

		// Execute the query
		ResultSet rs = stmt.executeQuery("SELECT * FROM combat");

		// Loop through the result set
		while (rs.next()) {
			numC = rs.getInt("numero");
			numQ = rs.getInt("question");
			idPlayer1 = rs.getString("player1");
			idPlayer2 = rs.getString("player2");
			idPosition = rs.getInt("positionId");
			RequetePosition rp = new RequetePosition();
			position = rp.getPositionById(conn, idPosition);
			RequeteQuestion rq = new RequeteQuestion();
			question = rq.getQuestionByNum(conn, numQ);
			RequetePlayer rplay = new RequetePlayer();
			player1 = rplay.getPlayerByLogin(conn, idPlayer1);
			player2 = rplay.getPlayerByLogin(conn, idPlayer2);

			result.add(new Combat(position, numC, player1, player2, question));
		}
		// Close the result set, statement and the connection
		rs.close();
		stmt.close();
		return result;
	}
}
