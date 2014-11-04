package mappage;

/**
 * Classe du modèle concernant les joueurs
 * @author leovidal
 *
 */
public class Player {

	private String login;
	private int points;
	private int quetesRealisees;
	private Position position;
	
	/**
	 * Constructeur par champs
	 * @param login
	 * @param points
	 * @param quetesRealisees
	 * @param position
	 */
	public Player(String login, int points, int quetesRealisees,
			Position position) {
		super();
		this.login = login;
		this.points = points;
		this.quetesRealisees = quetesRealisees;
		this.position = position;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getQuetesRealisees() {
		return quetesRealisees;
	}
	public void setQuetesRealisees(int quetesRealisees) {
		this.quetesRealisees = quetesRealisees;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	
	
}
