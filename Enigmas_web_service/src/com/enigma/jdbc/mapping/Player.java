package com.enigma.jdbc.mapping;

/**
 * Classe du modèle concernant les joueurs
 * @author leovidal
 *
 */
public class Player {

	private String login, salt;
	private int points;
	private int quetesRealisees;
	private Position position;
	
	/**
	 * Constructeur
	 * @param login
	 * @param salt
	 * @param points
	 * @param quetesRealisees
	 * @param position
	 */
	public Player(String login, String salt, int points, int quetesRealisees,
			Position position) {
		super();
		this.login = login;
		this.salt = salt;
		this.points = points;
		this.quetesRealisees = quetesRealisees;
		this.position = position;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getQuetesRealisees() {
		return quetesRealisees;
	}
	public void setQuetesRealisees(int quetesRealisees) {
		this.quetesRealisees = quetesRealisees;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	
}
