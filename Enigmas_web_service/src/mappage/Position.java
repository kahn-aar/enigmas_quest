package mappage;

/**
 * Classe du modèle concernant les positions
 * @author leovidal
 *
 */
public class Position {

	private int id;
	private float longitude;
	private float latitude;
	
	/**
	 * Constructeur par champs
	 * @param id
	 * @param longitude
	 * @param latitude
	 */
	public Position(int id, float longitude, float latitude){
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	
	
}
