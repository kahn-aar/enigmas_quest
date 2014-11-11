package com.client.enigmas_quest.service;

import com.client.enigmas_quest.constants.EnigmasConstants;

import android.os.AsyncTask;

public class RequestRESTAsync extends AsyncTask<Integer, Void, String> {

	@Override
	protected String doInBackground(Integer... arg0) {
		switch(arg0[0]) {
		case EnigmasConstants.REST_GET_ALL_POSITIONS:break;
		case EnigmasConstants.REST_AUTHENTICATION:
			// /player/authentification?login=xxx&pasword=xxx
			break;
		case EnigmasConstants.REST_ACOUNT_CREATION:
			// /player/authentification?login=xxx&pasword=xxx
			break;
		case EnigmasConstants.REST_GET_ENIGMA:
			// /player/authentification?login=xxx&pasword=xxx
			break;
		case EnigmasConstants.REST_GET_PLAYER_STATS:
			// /player/authentification?login=xxx&pasword=xxx
			break;
		}
		return null;
	}

}
