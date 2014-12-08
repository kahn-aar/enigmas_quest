package com.enigma.jdbc.mapping;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * Classe du modèle concernant les questions reponses (lien entre player et question avec la réponse du joueur)
 * @author leovidal
 *
 */
@XmlRootElement
@XmlSeeAlso({Player.class, Question.class})
public class QuestionReponse {

	private Player player;
	private Question question;
	private boolean vraiOuFaux;
	private String reponse;
	
	public QuestionReponse(){
		
	}
	
	/**
	 * 
	 * @param player
	 * @param question
	 * @param vraiOuFaux
	 * @param reponse
	 */
	public QuestionReponse(Player player, Question question,
			boolean vraiOuFaux, String reponse) {
		super();
		this.player = player;
		this.question = question;
		this.vraiOuFaux = vraiOuFaux;
		this.reponse = reponse;
	}

	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public boolean isVraiOuFaux() {
		return vraiOuFaux;
	}
	public void setVraiOuFaux(boolean vraiOuFaux) {
		this.vraiOuFaux = vraiOuFaux;
	}

	public String getReponse() {
		return reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}
	
	
}
