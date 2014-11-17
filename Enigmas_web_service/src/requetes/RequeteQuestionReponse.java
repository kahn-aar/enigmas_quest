package requetes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import mappage.*;

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
	
}
