package com.enigma.jdbc.request;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.enigma.jdbc.mapping.Question;
import com.enigma.jdbc.mapping.Reponse;

/**
 * 
 * @author leovidal
 *
 */
public class RequeteReponse {

	/**
	 * 
	 * @param conn
	 * @param login
	 * @return
	 * @throws SQLException 
	 */
	public ArrayList<Reponse> getReponseByLogin(Connection conn, String login) throws SQLException{
		ArrayList<Reponse> result = new ArrayList<Reponse>();
		String question, solution, reponseJ;
		boolean juste;
		
		// Get a statement from the connection
		Statement stmt = conn.createStatement();

		// Get a statement from the connection
		PreparedStatement st = conn.prepareStatement("SELECT Q.question AS question, Q.reponse AS solution, QR.reponse AS reponseJ, QR.vraiOuFaux AS juste FROM question Q, questionReponse QR WHERE Q.numero = QR.numeroQ AND QR.login = ?");
		// Execute the query
		st.setString(1, login);
		ResultSet rs = st.executeQuery();

		// Loop through the result set
		while (rs.next()) {
			question = rs.getString("question");
			solution = rs.getString("solution");
			reponseJ = rs.getString("reponseJ");
			juste = rs.getBoolean("juste");
			
			result.add(new Reponse(question, reponseJ, solution, juste));
		}
		// Close the result set, statement and the connection
		rs.close();
		stmt.close();

		
		
		return result;
	}
}
