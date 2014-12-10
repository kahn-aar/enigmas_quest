package com.enigma.jdbc.mapping;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * 
 * @author leovidal
 *
 */
@XmlRootElement
public class Reponse {

	private String question; 
	private String reponseJoueur;
	private String solution;
	private boolean juste;
	
	public Reponse(){
		
	}
	
	/**
	 * 
	 * @param question
	 * @param reponseJoueur
	 * @param solution
	 * @param juste
	 */
	public Reponse(String question, String reponseJoueur, String solution,
			boolean juste) {
		super();
		this.question = question;
		this.reponseJoueur = reponseJoueur;
		this.solution = solution;
		this.juste = juste;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getReponseJoueur() {
		return reponseJoueur;
	}
	public void setReponseJoueur(String reponseJoueur) {
		this.reponseJoueur = reponseJoueur;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	public boolean isJuste() {
		return juste;
	}
	public void setJuste(boolean juste) {
		this.juste = juste;
	}
	
	
	
	
}
