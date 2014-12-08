package com.enigma.jdbc.mapping;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * Classe de modele, concernant les combats
 * @author leovidal
 *
 */
@XmlRootElement
@XmlSeeAlso({Question.class})
public class Combat extends Quetes{

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
