package com.client.enigmas_quest.service;

import com.client.enigmas_quest.constants.EnigmasConstants;

import android.os.AsyncTask;

public class RequestRESTAsync extends AsyncTask<Integer, Void, String> {

	@Override
	protected String doInBackground(Integer... arg0) {
		switch(arg0[0]) {
		case EnigmasConstants.REST_GET_ALL_POSITIONS:
			System.out.println("// /positions/allPositions=true");
			break;
		case EnigmasConstants.REST_AUTHENTICATION:
			System.out.println("// /player/authentification?login=xxx&pasword=xxx");
			break;
		case EnigmasConstants.REST_ACOUNT_CREATION:
			System.out.println("// /player/creation?login=xxx&pasword=xxx");
			break;
		case EnigmasConstants.REST_GET_ENIGMA:
			System.out.println("// /enigma/specific?id=xxx");
			break;
		case EnigmasConstants.REST_GET_PLAYER_STATS:
			System.out.println("// /player/stats?id=xxx");
			break;
		}
		return null;
	}

}
