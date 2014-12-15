package com.client.enigmas_quest.mappage;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Classe de modele, concernant les combats
 * @author leovidal
 *
 */
public class Combat extends Quetes {

	private Player player1;
	private Player player2;
	private Question question;
	
	/**
	 * Constructeur
	 * @param position
	 * @param numero
	 * @param player1
	 * @param player2
	 * @param question
	 */
	public Combat(Position position, int numero, Player player1,
			Player player2, Question question) {
		super(position, numero);
		this.player1 = player1;
		this.player2 = player2;
		this.question = question;
	}

	public Combat(JSONObject json) {
		try {
			this.question = new Question((JSONObject) json.get("question"));
			this.player1 = new Player((JSONObject) json.get("player1"));
			this.player2 = new Player((JSONObject) json.get("player2"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
	
	
	
}
