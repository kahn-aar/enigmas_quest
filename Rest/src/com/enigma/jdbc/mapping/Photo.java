package com.enigma.jdbc.mapping;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe du modèle concernant les photos
 * @author leovidal
 *
 */
@XmlRootElement
public class Photo extends Multimedia{

	/**
	 * Constructeur
	 * @param position
	 * @param numero
	 * @param theme
	 * @param url
	 */
	public Photo(Position position, int numero, String theme, String url) {
		super(position, numero, theme, url);
		// TODO Auto-generated constructor stub
	}

	public Photo(){
		
	}
	
}
