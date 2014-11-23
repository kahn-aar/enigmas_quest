package com.enigma.jdbc.mapping;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * Classe du modèle concernant les joueurs
 * @author leovidal
 *
 */
@XmlRootElement
@XmlSeeAlso({Position.class})
public class Player {

	private String login;
	
	private String salt;
	
	private int points;
	
	private int quetesRealisees;
	
	private Position positionId;
	
	public Player() {
		
	}
	
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
		this.positionId = position;
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
		return positionId;
	}
	public void setPosition(Position position) {
		this.positionId = position;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	
}
