package mappage;

/**
 * Classe du modèle concernant les questions reponses (lien entre player et question avec la réponse du joueur)
 * @author leovidal
 *
 */
public class QuestionReponse {

	private Player player;
	private Question question;
	private boolean vraiOuFaux;
	
	/**
	 * Constructeur par champs
	 * @param player
	 * @param question
	 * @param vraiOuFaux
	 */
	public QuestionReponse(Player player, Question question, boolean vraiOuFaux) {
		super();
		this.player = player;
		this.question = question;
		this.vraiOuFaux = vraiOuFaux;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public boolean isVraiOuFaux() {
		return vraiOuFaux;
	}
	public void setVraiOuFaux(boolean vraiOuFaux) {
		this.vraiOuFaux = vraiOuFaux;
	}
	
	
}
