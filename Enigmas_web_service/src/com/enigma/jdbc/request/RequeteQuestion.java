package com.enigma.jdbc.request;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.enigma.jdbc.mapping.*;

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
		String possible1, possible2, possible3, possible4;
		int positionId;
		int numQ;
		
		// Get a statement from the connection
		PreparedStatement st = conn.prepareStatement("SELECT * FROM question WHERE numero = ?");
		// Execute the query
		st.setInt(1, num);
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			question = rs.getString("question");
			possible1 = rs.getString("possible1");
			possible2 = rs.getString("possible2");
			possible3 = rs.getString("possible3");
			possible4 = rs.getString("possible4");
			reponse = rs.getString("reponse");
			positionId = rs.getInt("positionId");
			numQ = rs.getInt("numero");
			RequetePosition rp = new RequetePosition();
			result = new Question(rp.getPositionById(conn, positionId), numQ, question, reponse, possible1, possible2, possible3, possible4);
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
		String possible1, possible2, possible3, possible4;
		int numQ;
		
		// Get a statement from the connection
		PreparedStatement st = conn.prepareStatement("SELECT * FROM question WHERE positionId = ?");
		// Execute the query
		st.setInt(1, position.getId());
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			question = rs.getString("question");
			reponse = rs.getString("reponse");
			possible1 = rs.getString("possible1");
			possible2 = rs.getString("possible2");
			possible3 = rs.getString("possible3");
			possible4 = rs.getString("possible4");
			numQ = rs.getInt("numero");
			result = new Question(position, numQ, question, reponse, possible1, possible2, possible3, possible4);
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
		String possible1, possible2, possible3, possible4;
		
		// Get a statement from the connection
		Statement stmt = conn.createStatement();

		// Execute the query
		ResultSet rs = stmt.executeQuery("SELECT * FROM question");

		// Loop through the result set
		while (rs.next()) {
			question = rs.getString("question");
			reponse = rs.getString("reponse");
			numero = rs.getInt("numero");
			possible1 = rs.getString("possible1");
			possible2 = rs.getString("possible2");
			possible3 = rs.getString("possible3");
			possible4 = rs.getString("possible4");
			idPosition = rs.getInt("positionId");
			RequetePosition rp = new RequetePosition();
			position = rp.getPositionById(conn, idPosition);

			result.add(new Question(position, numero, question, reponse, possible1, possible2, possible3, possible4));
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
		String possible1, possible2, possible3, possible4;
		
		// Get a statement from the connection
		PreparedStatement st = conn.prepareStatement("SELECT * FROM question WHERE positionId = ?");
		// Execute the query
		st.setInt(1, position.getId());
		ResultSet rs = st.executeQuery();

		// Loop through the result set
		while (rs.next()) {
			question = rs.getString("question");
			reponse = rs.getString("reponse");
			possible1 = rs.getString("possible1");
			possible2 = rs.getString("possible2");
			possible3 = rs.getString("possible3");
			possible4 = rs.getString("possible4");
			numero = rs.getInt("numero");

			result.add(new Question(position, numero, question, reponse, possible1, possible2, possible3, possible4));
		}
		// Close the result set, statement and the connection
		rs.close();
		return result;
	}
	
}
