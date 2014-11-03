package com.client.enigmas_quest.data;

public class DrawerItem {

	private String title;
	
	private int imgResID;

	public DrawerItem(String title, int imgResID) {
		super();
		this.title = title;
		this.imgResID = imgResID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getImgResID() {
		return imgResID;
	}

	public void setImgResID(int imgResID) {
		this.imgResID = imgResID;
	}
	
	
	
}
