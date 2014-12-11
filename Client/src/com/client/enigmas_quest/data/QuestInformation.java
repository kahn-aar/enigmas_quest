package com.client.enigmas_quest.data;

import org.json.JSONException;
import org.json.JSONObject;

import com.client.enigmas_quest.mappage.Position;

public class QuestInformation {

	private int id;
	private Position position;
	private QuestType type;
	
	public QuestInformation(JSONObject json) throws JSONException {
		super();
		this.id = json.getInt("numero");
		this.position = new Position(json.getJSONObject("position"));
		this.type = QuestType.QUESTION;
	}
	
	public QuestInformation(Position position, int id, QuestType type) {
		super();
		this.position = position;
		this.id = id;
		this.type = type;
	}
	
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public QuestType getType() {
		return type;
	}
	public void setType(QuestType type) {
		this.type = type;
	}
	
}
