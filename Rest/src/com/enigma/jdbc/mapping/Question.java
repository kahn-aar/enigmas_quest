package com.enigma.jdbc.mapping;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe du modèle concernant les questions
 * @author leovidal
 *
 */
@XmlRootElement(name = "queque")
public class Question extends Quetes{

	private String question;
	
	private String reponse;
	
	private String possible1, possible2, possible3, possible4;
	
	
	/**
	 * Constructeur
	 * @param position
	 * @param numero
	 * @param question
	 * @param reponse
	 * @param possible1
	 * @param possible2
	 * @param possible3
	 * @param possible4
	 */
	public Question(Position position, int numero, String question,
			String reponse, String possible1, String possible2,
			String possible3, String possible4) {
		super(position, numero);
		this.question = question;
		this.reponse = reponse;
		this.possible1 = possible1;
		this.possible2 = possible2;
		this.possible3 = possible3;
		this.possible4 = possible4;
	}
	
	public Question(){
		
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getReponse() {
		return reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}

	public String getPossible1() {
		return possible1;
	}

	public void setPossible1(String possible1) {
		this.possible1 = possible1;
	}

	public String getPossible2() {
		return possible2;
	}

	public void setPossible2(String possible2) {
		this.possible2 = possible2;
	}

	public String getPossible3() {
		return possible3;
	}

	public void setPossible3(String possible3) {
		this.possible3 = possible3;
	}

	public String getPossible4() {
		return possible4;
	}

	public void setPossible4(String possible4) {
		this.possible4 = possible4;
	}
	
	
	
}
