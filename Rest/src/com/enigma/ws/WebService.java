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
import com.enigma.jdbc.mapping.Photo;
import com.enigma.jdbc.mapping.Player;
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
	
	@GET
	@Path("player")
	@Produces({"application/xml", "application/json"})
	public ArrayList<Player> getAllPlayer() throws SQLException{
		ArrayList<Player> allPlayer = launcher.rp.allPlayers(Controleur.getConn());
		return allPlayer;
	}
	
	
	@GET
	@Path("photos")
	@Produces({"application/xml", "application/json"})
	public ArrayList<Photo> getAllPhoto() throws SQLException {
		ArrayList<Photo> allPhoto = launcher.rph.getAllPhoto(Controleur.getConn());
	    return allPhoto;
	}
	
	@GET
	@Path("details")
	@Produces({"application/xml", "application/json"})
	public ArrayList<Reponse> getStatByLogin(@QueryParam("login") String login) throws SQLException{
		ArrayList<Reponse> result = launcher.rr.getReponseByLogin(Controleur.getConn(), login);
		return result;
	}
	
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
	
	@POST
	@Path("quest")
	@Produces({"application/xml", "application/json"})
	public void answerQuest(@QueryParam("num") int id, @QueryParam("login") String login, @QueryParam("answer") String Answer) {
		System.out.println("Appel de la membrane");
	}
	
}
