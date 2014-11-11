package mappage;

/**
 * 
 * @author leovidal
 *
 */
public abstract class Multimedia extends Quetes{

	private String theme;

	/**
	 * Constructeur
	 * @param position
	 * @param numero
	 * @param theme
	 */
	public Multimedia(Position position, int numero, String theme) {
		super(position, numero);
		this.theme = theme;
	}
	
	

	
}
