package com.client.enigmas_quest.application;

import java.util.ArrayList;
import java.util.List;

import android.app.Application;
import android.content.res.Configuration;

import com.client.enigmas_quest.data.Enigma;
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
	
}
