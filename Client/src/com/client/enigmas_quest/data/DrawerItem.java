package com.client.enigmas_quest.data;

public class DrawerItem {

	private String title;
	private String ItemName;
	private int imgResID;

	public DrawerItem(String title) {
		this(null, 0);
		this.title = title;
	}
	
	public DrawerItem(String ItemName, int imgResID) {
		super();
		this.ItemName = ItemName;
		this.imgResID = imgResID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getItemName() {
		return ItemName;
	}

	public void setItemName(String ItemName) {
		this.ItemName = ItemName;
	}

	public int getImgResID() {
		return imgResID;
	}

	public void setImgResID(int imgResID) {
		this.imgResID = imgResID;
	}
	
	
	
}
