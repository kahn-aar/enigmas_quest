package mappage;

/**
 * Classe du modèle concernant les questions
 * @author leovidal
 *
 */
public class Question extends Quetes{

	private String question;
	private String reponse;
	
	/**
	 * Constructeur
	 * @param position
	 * @param numero
	 * @param question
	 * @param reponse
	 */
	public Question(Position position, int numero, String question,
			String reponse) {
		super(position, numero);
		this.question = question;
		this.reponse = reponse;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getReponse() {
		return reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}
	
	
}
