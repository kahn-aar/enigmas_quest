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
 * regroupe tout les appels à la base MySQL concernant la table questionReponse
 * @author leovidal
 *
 */
public class RequeteQuestionReponse {

	/**
	 * retourne vrai ou faux pour une question et un joueur donné
	 * @param conn
	 * @param player
	 * @param question
	 * @return
	 * @throws SQLException
	 */
	public boolean getVraiOuFauxByPlayerAndQuestion(Connection conn, Player player, Question question) throws SQLException{
		
		boolean result = false;
		
		// Get a statement from the connection
		PreparedStatement st = conn.prepareStatement("SELECT vraiOuFaux FROM questionReponse WHERE numeroQ = ? AND login = ?");
		// Execute the query
		st.setInt(1, question.getNumero());
		st.setString(2, player.getLogin());
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			result = rs.getBoolean("vraiOuFaux");
		}
		System.out.println(result);
		return result;
	}
	
	/**
	 * retourne toutes les questions auxquelles un joueur a répondu
	 * @param conn
	 * @param login
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Question> getAllQuestionByLogin(Connection conn, String login) throws SQLException{
		ArrayList<Question> result = new ArrayList<Question>();
		int numero;
		
		// Get a statement from the connection
		Statement stmt = conn.createStatement();

		// Execute the query
		ResultSet rs = stmt.executeQuery("SELECT numeroQ FROM questionReponse WHERE login = ?");

		// Loop through the result set
		while (rs.next()) {
			numero = rs.getInt("numero");
			RequeteQuestion rq = new RequeteQuestion();
			result.add(rq.getQuestionByNum(conn, numero));
		}
		// Close the result set, statement and the connection
		rs.close();
		stmt.close();
		return result;
	}
	
}
