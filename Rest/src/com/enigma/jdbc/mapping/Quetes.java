package com.enigma.jdbc.mapping;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author leovidal
 *
 */
@XmlRootElement
public abstract class Quetes {

	private Position position;
	private int numero;
	
	public Quetes() {
		
	}
	
	/**
	 * constructeur
	 * @param position
	 * @param numero
	 */
	public Quetes(Position position, int numero) {
		super();
		this.position = position;
		this.numero = numero;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	
	
}
