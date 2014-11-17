package com.enigma.jdbc.mapping;

/**
 * 
 * @author leovidal
 *
 */
public abstract class Quetes {

	private Position position;
	private int numero;
	
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
