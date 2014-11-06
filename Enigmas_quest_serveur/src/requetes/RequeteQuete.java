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
 * regroupe tout les appels à la base MySQL concernant les quetes
 * @author leovidal
 *
 */
public class RequeteQuete {

	/**
	 * renvoie toutes quetes pour une position donnée
	 * @param conn
	 * @param position
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Quetes> getQuetesByPosition(Connection conn, Position position) throws SQLException{
		ArrayList<Quetes> result = new ArrayList<Quetes>();
		RequeteQuestion rq = new RequeteQuestion();
		RequeteCombat rc = new RequeteCombat();
		RequetePhoto rp = new RequetePhoto();
		RequeteVideo rv = new RequeteVideo();
		
		result.addAll(rq.getAllQuestionByPosition(conn, position));
		result.addAll(rc.getAllCombatByPosition(conn, position));
		result.addAll(rp.getPhotosByPosition(conn, position));
		result.addAll(rv.getVideosByPosition(conn, position));
		
		return result;
	}
}
