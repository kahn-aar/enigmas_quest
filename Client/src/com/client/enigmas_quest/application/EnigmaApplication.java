package com.client.enigmas_quest.application;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Application;
import android.content.res.Configuration;

import com.client.enigmas_quest.constants.EnigmasConstants;
import com.client.enigmas_quest.data.Enigma;
import com.client.enigmas_quest.data.Player;
import com.client.enigmas_quest.service.RequestRESTAsync;

public class EnigmaApplication extends Application {

	private List<Enigma> enigmes = new ArrayList<Enigma>();
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		getAllEnigmasPositions();
	}
	
	public void getAllEnigmasPositions() {
		RequestRESTAsync async = new RequestRESTAsync();
		
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
		Player player = null;
		try {
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
		}
		
		return player;
	}
	
}
