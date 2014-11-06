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
 * regroupe tout les appels à la base MySQL concernant les questions
 * @author leovidal
 *
 */
public class RequeteQuestion {

	/**
	 * recupere un question selon son numero
	 * @param conn
	 * @param num
	 * @return
	 * @throws SQLException
	 */
	public Question getQuestionByNum(Connection conn, int num) throws SQLException{
		Question result;
		String question;
		String reponse;
		int positionId;
		int numQ;
		
		// Get a statement from the connection
		PreparedStatement st = conn.prepareStatement("SELECT * FROM question WHERE numero = ?");
		// Execute the query
		st.setInt(1, num);
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			question = rs.getString("question");
			reponse = rs.getString("reponse");
			positionId = rs.getInt("positionId");
			numQ = rs.getInt("numero");
			RequetePosition rp = new RequetePosition();
			result = new Question(rp.getPositionById(conn, positionId), numQ, question, reponse);
		} else {
			result = null;
		}
		
		return result;
	}
	
	/**
	 * recupere une question en fonction d'une position
	 * @param conn
	 * @param position
	 * @return
	 * @throws SQLException
	 */
	public Question getQuestionByPosition(Connection conn, Position position) throws SQLException{
		Question result;
		String question;
		String reponse;
		int numQ;
		
		// Get a statement from the connection
		PreparedStatement st = conn.prepareStatement("SELECT * FROM question WHERE positionId = ?");
		// Execute the query
		st.setInt(1, position.getId());
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			question = rs.getString("question");
			reponse = rs.getString("reponse");
			numQ = rs.getInt("numero");
			result = new Question(position, numQ, question, reponse);
		} else {
			result = null;
		}
		
		return result;
	}
	
	/**
	 * retourne toutes les questions dans une array list d'objet question
	 * @param conn
	 * @return
	 * @throws SQLException 
	 */
	public ArrayList<Question> getAllQuestion(Connection conn) throws SQLException{
		ArrayList<Question> result = new ArrayList<Question>();
		String question, reponse;
		Position position;
		int numero, idPosition;
		
		// Get a statement from the connection
		Statement stmt = conn.createStatement();

		// Execute the query
		ResultSet rs = stmt.executeQuery("SELECT * FROM question");

		// Loop through the result set
		while (rs.next()) {
			question = rs.getString("question");
			reponse = rs.getString("reponse");
			numero = rs.getInt("numero");
			idPosition = rs.getInt("positionId");
			RequetePosition rp = new RequetePosition();
			position = rp.getPositionById(conn, idPosition);

			result.add(new Question(position, numero, question, reponse));
		}
		// Close the result set, statement and the connection
		rs.close();
		stmt.close();
		return result;
	}
	
	/**
	 * renvoie toutes les questions pour une position donnée
	 * @param conn
	 * @param position
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Question> getAllQuestionByPosition(Connection conn, Position position) throws SQLException{
		ArrayList<Question> result = new ArrayList<Question>();
		String question, reponse;
		int numero;
		
		// Get a statement from the connection
		PreparedStatement st = conn.prepareStatement("SELECT * FROM question WHERE positionId = ?");
		// Execute the query
		st.setInt(1, position.getId());
		ResultSet rs = st.executeQuery();

		// Loop through the result set
		while (rs.next()) {
			question = rs.getString("question");
			reponse = rs.getString("reponse");
			numero = rs.getInt("numero");

			result.add(new Question(position, numero, question, reponse));
		}
		// Close the result set, statement and the connection
		rs.close();
		return result;
	}
	
}
