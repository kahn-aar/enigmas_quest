package com.client.enigmas_quest.data;

import com.client.enigmas_quest.mappage.Position;

public class QuestInformation {

	private Position position;
	private int id;
	private QuestType type;
	
	
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
