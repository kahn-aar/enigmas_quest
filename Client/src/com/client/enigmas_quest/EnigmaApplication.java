package com.client.enigmas_quest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Application;
import android.content.res.Configuration;
import android.location.Location;

import com.client.enigmas_quest.constants.EnigmasConstants;
import com.client.enigmas_quest.data.Enigma;
import com.client.enigmas_quest.data.Player;
import com.client.enigmas_quest.data.QuestInformation;
import com.client.enigmas_quest.mappage.Combat;
import com.client.enigmas_quest.mappage.Position;
import com.client.enigmas_quest.mappage.Question;
import com.client.enigmas_quest.mappage.Quetes;
import com.client.enigmas_quest.service.RequestRESTAsync;

public class EnigmaApplication extends Application {

	/**
	 * Current player
	 */
	private Player player = null;
	
	
	/**
	 * Positions
	 */
	private List<QuestInformation> positions = new ArrayList<QuestInformation>();
	
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}
	
	/**
	 * Try to log user on server
	 * @param pseudo username of user
	 * @param password password of user
	 * @return Player
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @throws JSONException
	 */
	public Player loginOnServer(String pseudo, String password) 
			throws InterruptedException, ExecutionException, JSONException {
		RequestRESTAsync async = new RequestRESTAsync(EnigmasConstants.REST_AUTHENTICATION);
		async.execute(pseudo, password);
		JSONObject object = async.get();
		Player player = new Player(object);
		this.player = player;
		return player;
	}
	
	public void getAllEnigmasPositions() throws InterruptedException, ExecutionException, JSONException {
		RequestRESTAsync async = new RequestRESTAsync(EnigmasConstants.REST_GET_ALL_POSITIONS);
		async.execute();
		JSONObject object = async.get();
		JSONArray array = object.getJSONArray("quetes");
		for (int i = 0; i < array.length(); i++) {
			positions.add(new QuestInformation(array.getJSONObject(i)));
		}
	}
	
	public Player createNewAccount(String pseudo, String password) {
		RequestRESTAsync async = new RequestRESTAsync(EnigmasConstants.REST_ACOUNT_CREATION);
		async.execute(pseudo, password);
		
		return player;
	}
	
	

	public Player getPlayerInformations(int i) {
		
		
		return new Player();
	}

	public Player getPlayer() {
		return this.player;
	}

	public Quetes getEngimaById(int id) {
		RequestRESTAsync async = new RequestRESTAsync(EnigmasConstants.REST_GET_ENIGMA);
		async.execute(String.valueOf(id));
		Question enigma = null;
		try {
			JSONObject json = async.get();
			if(json != null) {
				enigma = new Question(json);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return enigma;
	}

	public void answerTheQuestion(String answer, int enigmaId, boolean succes) {
		RequestRESTAsync async = new RequestRESTAsync(EnigmasConstants.REST_POST_ENIGMA_RESPONSE);
		async.execute(String.valueOf(enigmaId), player.getName(), answer, String.valueOf(succes));
		
	}

	public void answerTheBattle(String answer, int enigmaId, boolean time, int opponentId) {
		RequestRESTAsync async = new RequestRESTAsync(EnigmasConstants.REST_POST_BATTLE_RESPONSE);
		async.execute();
	}

	public List<QuestInformation> getPositions() {
		return positions;
	}

	public Combat getBattle(String name) {
		RequestRESTAsync async = new RequestRESTAsync(EnigmasConstants.REST_GET_BATTLE);
		async.execute(name);
		Combat enigma = null;
		try {
			JSONObject json = async.get();
			if(json != null) {
				enigma = new Combat(json);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return enigma;
	}

	public void sendPositionToServer(Location position) {
		RequestRESTAsync async = new RequestRESTAsync(EnigmasConstants.REST_POST_POS_PLAYER);
		async.execute(player.getName(), String.valueOf(position.getLatitude()), String.valueOf(position.getLongitude()));
		
	}
	
}
