package com.enigma.jdbc.mapping;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe du modèle concernant les videos
 * @author leovidal
 *
 */
@XmlRootElement
public class Video extends Multimedia{

	/**
	 * Constructeur
	 * @param position
	 * @param numero
	 * @param theme
	 * @param url
	 */
	public Video(Position position, int numero, String theme, String url) {
		super(position, numero, theme, url);
		// TODO Auto-generated constructor stub
	}

	
	
}
