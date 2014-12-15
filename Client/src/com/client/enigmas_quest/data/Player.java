package com.client.enigmas_quest.data;

import org.json.JSONException;
import org.json.JSONObject;

public class Player {

	private String name;
	private int points;
	private int questionAnswered;
	private int questionAsked;
	
	public Player(JSONObject jsonObject) throws JSONException {
		this.name = jsonObject.getString("login");
		this.points = jsonObject.getInt("points");
		this.questionAnswered = 0;
		this.questionAsked = 0;
	}
	
	public Player() {
		name = "nicoco";
		points = 5;
		questionAnswered = 10;
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
