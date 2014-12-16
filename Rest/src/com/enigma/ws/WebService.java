package com.enigma.ws;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.PathParam;

import com.enigma.jdbc.loader.Controleur;
import com.enigma.jdbc.loader.LauncherRequest;
import com.enigma.jdbc.mapping.Combat;
import com.enigma.jdbc.mapping.Photo;
import com.enigma.jdbc.mapping.Player;
import com.enigma.jdbc.mapping.Position;
import com.enigma.jdbc.mapping.Question;
import com.enigma.jdbc.mapping.QuestionReponse;
import com.enigma.jdbc.mapping.Quetes;
import com.enigma.jdbc.mapping.Reponse;
import com.enigma.jdbc.mapping.Statistique;


/**
 * 
 * @author team_enigma
 *
 */
@Path("/enigma")
public class WebService {
	
	private Controleur controller = Controleur.getInstance();
	
	private LauncherRequest launcher = new LauncherRequest();
	
	/**
	 * 
	 * @param login
	 * @param password
	 * @return
	 */
	@GET
	@Path("login")
	@Produces({"application/xml", "application/json"})
	public Player login(@QueryParam("login") String login, @QueryParam("password") String password) {
		try {
			Player p = launcher.rp.getPlayerByLogin(Controleur.getConn(), login);
			return p;
		} catch (SQLException e) {
			return null;
		}
	}
	
	/**
	 * 
	 * @param login
	 * @param password
	 */
	@POST
	@Path("register")
	public void register(@QueryParam("login") String login, @QueryParam("password") String password) {
		try {
			System.out.println("bonjour du register");
			Player p = launcher.rp.getPlayerByLogin(Controleur.getConn(), login);
			System.out.println(p);
			if(p == null) {
				System.out.println("bonjour du register");
				Player newPlayer = new Player(login, password, 0, 0, null);
				launcher.rp.addPlayer(Controleur.getConn(), newPlayer);
			}
		} catch (SQLException e) {
			
		}
	}
	
	/**
	 * 
	 * @return
	 */
	@GET
	@Path("allQuest")
	@Produces({"application/xml", "application/json"})
	public ArrayList<Quetes> allQuest() {
		try {
			ArrayList<Quetes> allQuests = launcher.rquete.getAllQuest(Controleur.getConn());
			return allQuests;
		} catch (SQLException e) {
			return null;
		}
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path("quest")
	@Produces({"application/xml", "application/json"})
	public Quetes questById(@QueryParam("num") int id) {
		try {
			Quetes quest = launcher.rquete.getQueteByNum(Controleur.getConn(), id);
			return quest;
		} catch (SQLException e) {
			return null;
		}
	}
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	@GET
	@Path("player")
	@Produces({"application/xml", "application/json"})
	public ArrayList<Player> getAllPlayer() throws SQLException{
		ArrayList<Player> allPlayer = launcher.rp.allPlayers(Controleur.getConn());
		return allPlayer;
	}
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	@GET
	@Path("photos")
	@Produces({"application/xml", "application/json"})
	public ArrayList<Photo> getAllPhoto() throws SQLException {
		ArrayList<Photo> allPhoto = launcher.rph.getAllPhoto(Controleur.getConn());
	    return allPhoto;
	}
	
	/**
	 * 
	 * @param login
	 * @return
	 * @throws SQLException
	 */
	@GET
	@Path("details")
	@Produces({"application/xml", "application/json"})
	public ArrayList<Reponse> getStatByLogin(@QueryParam("login") String login) throws SQLException{
		ArrayList<Reponse> result = launcher.rr.getReponseByLogin(Controleur.getConn(), login);
		return result;
	}
	
	/**
	 * 
	 * @param login
	 * @return
	 * @throws SQLException
	 */
	@GET
	@Path("stat")
	@Produces({"application/xml", "application/json"})
	public Statistique getPourcentageByLogin(@QueryParam("login") String login) throws SQLException{
		int nbJuste = 0;
		ArrayList<Reponse> liste = launcher.rr.getReponseByLogin(Controleur.getConn(), login);
		for(int i=0; i<liste.size(); i++){
			if (liste.get(i).isJuste()){
				nbJuste++;
			}
		}
		return new Statistique((float)nbJuste/(float)liste.size()*100);
	}
	
	/**
	 * 
	 * @param id
	 * @param login
	 * @param Answer
	 */
	@POST
	@Path("quest")
	@Produces({"application/xml", "application/json"})
	public void answerQuest(@QueryParam("num") int id, @QueryParam("login") String login, @QueryParam("answer") String Answer) {
		System.out.println("Appel de la membrane");
	}
	
	/**
	 * 
	 * @param id
	 * @param login
	 * @param Answer
	 */
	@POST
	@Path("position")
	@Produces({"application/xml", "application/json"})
	public void givePosition(@QueryParam("login") String login, @QueryParam("latitude") float lat, @QueryParam("longitude") float longitude) {
		try {
			Player p = launcher.rp.getPlayerByLogin(Controleur.getConn(), login);
			launcher.rp.changePosition(Controleur.getConn(), p, new Position(lat, longitude));
		} catch (SQLException e) {
		}
	}
	
	/**
	 * recuperer le joueur le plus proche de celui la. Si il est proche de 5m ou moins, on renvoie un combat (2 joueurs et quetes choisi au hasard) sinon null.
	 * @param login
	 * @return
	 * @throws SQLException 
	 */
	@GET
	@Path("battle")
	@Produces({"application/xml", "application/json"})
	public Combat getBattle(@QueryParam("login") String login) throws SQLException{
		ArrayList<Player> listeJoueur = launcher.rp.allPlayers(Controleur.getConn());
		Player joueur1, joueur2;
		joueur2 = null;
		double distante = 1000000000;
		double pizza;
		joueur1 = launcher.rp.getPlayerByLogin(Controleur.getConn(), login);
		Combat result = null;
		
		for(int i=0; i<listeJoueur.size(); i++){
			if(joueur1.getLogin() != listeJoueur.get(i).getLogin()){
				pizza = Tools.distance((double)joueur1.getPosition().getLatitude(), (double)joueur1.getPosition().getLongitude(), (double)listeJoueur.get(i).getPosition().getLatitude(), (double)listeJoueur.get(i).getPosition().getLongitude(), 'K');
				if (distante > pizza){
					joueur2 = listeJoueur.get(i);
					distante = pizza;
				}
			}
		}
		
		if(distante <= 5 && joueur2 != null){
			Question question = launcher.rq.getAllQuestion(Controleur.getConn()).get(1);
			result = new Combat(joueur1.getPosition(), 1, joueur1, joueur2, question);
		}
		
		return result;
	}
	
}
