package com.client.enigmas_quest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Application;
import android.content.res.Configuration;

import com.client.enigmas_quest.constants.EnigmasConstants;
import com.client.enigmas_quest.data.Enigma;
import com.client.enigmas_quest.data.Player;
import com.client.enigmas_quest.data.Position;
import com.client.enigmas_quest.service.RequestRESTAsync;

public class EnigmaApplication extends Application {

	private List<Enigma> enigmes = new ArrayList<Enigma>();
	private List<Position> positions = new ArrayList<Position>();
	private Player player = null;
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		this.player = new Player();
	}
	
	public void getAllEnigmasPositions() {
		RequestRESTAsync async = new RequestRESTAsync();
		async.execute(EnigmasConstants.REST_GET_ALL_POSITIONS);
		/*try {
			String jsonResponse = async.get();
			JSONArray json = new JSONArray(jsonResponse);
			for (int i = 0; i < json.length(); i++) {
				positions.add(new Position(json.getJSONObject(i)));
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
	
	public void getSpecificEnigma(int engimaId) {
		
	}
	
	public Player createNewAccount(String pseudo, String password) {
		String crypto = "Mon appli est trop cool";
		RequestRESTAsync async = new RequestRESTAsync();
		async.execute(EnigmasConstants.REST_ACOUNT_CREATION);
		Player player = null;
		try {
			String jsonResponse = async.get();
			JSONObject json = new JSONObject(jsonResponse);
			player = new Player(json);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return player;
	}
	
	public Player connexion(String pseudo, String password) {
		String crypto = "Mon appli est trop cool";
		RequestRESTAsync async = new RequestRESTAsync();
		async.execute(EnigmasConstants.REST_AUTHENTICATION);
		Player player = new Player();
		/*try {
			String jsonResponse = async.get();
			JSONObject json = new JSONObject(jsonResponse);
			if(json != null) {
				player = new Player(json);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		this.player = player;
		return player;
	}

	public Player getPlayerInformations(int i) {
		RequestRESTAsync async = new RequestRESTAsync();
		async.execute(EnigmasConstants.REST_GET_PLAYER_STATS);
		Player player = null;
		/*try {
			String jsonResponse = async.get();
			JSONObject json = new JSONObject(jsonResponse);
			if(json != null) {
				player = new Player(json);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		return new Player();
	}

	public Player getPlayer() {
		return this.player;
	}
	
}