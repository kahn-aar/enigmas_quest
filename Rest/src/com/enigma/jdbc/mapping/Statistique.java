package com.enigma.jdbc.mapping;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author leovidal
 *
 */
@XmlRootElement
public class Statistique {
	public float pourcentQuestion;

	public Statistique(){
		
	}
	
	public Statistique(float pourcentQuestion) {
		this.pourcentQuestion = pourcentQuestion;
	}
	
}
