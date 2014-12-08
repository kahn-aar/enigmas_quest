package com.enigma.jdbc.mapping;

/**
 * 
 * @author leovidal
 *
 */
public class Reponse {

	private String question; 
	private String reponse;
	private boolean juste;
	
	/**
	 * 
	 * @param question
	 * @param reponse
	 * @param juste
	 */
	public Reponse(String question, String reponse, boolean juste) {
		super();
		this.question = question;
		this.reponse = reponse;
		this.juste = juste;
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
	public boolean isJuste() {
		return juste;
	}
	public void setJuste(boolean juste) {
		this.juste = juste;
	}
	
	
}
