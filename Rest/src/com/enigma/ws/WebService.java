package com.enigma.ws;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.enigma.jdbc.loader.Controleur;
import com.enigma.jdbc.loader.LauncherRequest;
import com.enigma.jdbc.mapping.Photo;
import com.enigma.jdbc.mapping.Player;
import com.enigma.jdbc.mapping.Quetes;

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
			Player p = launcher.rp.getPlayerByLogin(Controleur.getConn(), login);
			if(p == null) {
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
	public Quetes questById(@QueryParam("login") int id) {
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
	public ArrayList<Photo> getAllPhoto(){
		ArrayList<Photo> allPhoto = launcher.rph.getAllPhoto(Controleur.getConn());
		return allPhoto;
	}
	
}
