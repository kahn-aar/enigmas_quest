package com.client.enigmas_quest.data;

public class Player {

	private int id;
	private String name;
	private int points;
	private int questionAnswered;
	private int questionAsked;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getQuestionAnswered() {
		return questionAnswered;
	}
	public void setQuestionAnswered(int questionAnswered) {
		this.questionAnswered = questionAnswered;
	}
	public int getQuestionAsked() {
		return questionAsked;
	}
	public void setQuestionAsked(int questionAsked) {
		this.questionAsked = questionAsked;
	}
	
	
	
}
