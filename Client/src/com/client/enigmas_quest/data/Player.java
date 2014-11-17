package com.client.enigmas_quest.data;

import org.json.JSONException;
import org.json.JSONObject;

public class Player {

	private int id;
	private String name;
	private int points;
	private int questionAnswered;
	private int questionAsked;
	
	public Player(JSONObject jsonObject) throws JSONException {
		this.id = jsonObject.getInt("id");
		this.name = jsonObject.getString("name");
		this.points = 0;
		this.questionAnswered = 0;
		this.questionAsked = 0;
	}
	
	
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
