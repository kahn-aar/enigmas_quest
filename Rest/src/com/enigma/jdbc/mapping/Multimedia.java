package com.enigma.jdbc.mapping;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author leovidal
 *
 */
@XmlRootElement
public abstract class Multimedia extends Quetes{

	private String theme, url;

	/**
	 * Constructeur
	 * @param position
	 * @param numero
	 * @param theme
	 * @param url
	 */
	public Multimedia(Position position, int numero, String theme, String url) {
		super(position, numero);
		this.theme = theme;
		this.url = url;
	}
	
	public Multimedia(){
		
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	
	

	
}
