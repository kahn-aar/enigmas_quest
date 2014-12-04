package com.client.enigmas_quest.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.client.enigmas_quest.constants.EnigmasConstants;


import android.os.AsyncTask;

public class RequestRESTAsync extends AsyncTask<Integer, Void, String> {
	
	HttpClient httpclient = new DefaultHttpClient();
	String baseUrl = "http://192.168.56.1:8080/EnigmaRest/rest/enigma/";
	

	@Override
	protected String doInBackground(Integer... arg0) {

		switch (arg0[0]) {
			case EnigmasConstants.REST_GET_ALL_POSITIONS:
				
				break;
			case EnigmasConstants.REST_AUTHENTICATION:
				HttpGet httpget = new HttpGet(baseUrl+"/login?login=bob&password=bob"); 
				HttpResponse response;
				try {
			        response = httpclient.execute(httpget);
			        HttpEntity entity = response.getEntity();
			        if (entity != null) {
			            // A Simple JSON Response Read
			            InputStream instream = entity.getContent();
			            String result= convertStreamToString(instream);
			            instream.close();
			        }

			    } catch (Exception e) {}
				break;
			case EnigmasConstants.REST_ACOUNT_CREATION:
				
				break;
			case EnigmasConstants.REST_GET_ENIGMA:
				System.out.println("/enigma?id=lolilol");
				break;
			case EnigmasConstants.REST_GET_PLAYER_STATS:
				
				break;
			case EnigmasConstants.REST_POST_ENIGMA_RESPONSE:
				System.out.println("/enigma?id=lolilol&answer=bouh");
				
				break;
		}


		
		return null;
	}

	private static String convertStreamToString(InputStream is) {
	    /*
	     * To convert the InputStream to String we use the BufferedReader.readLine()
	     * method. We iterate until the BufferedReader return null which means
	     * there's no more data to read. Each line will appended to a StringBuilder
	     * and returned as String.
	     */
	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    StringBuilder sb = new StringBuilder();

	    String line = null;
	    try {
	        while ((line = reader.readLine()) != null) {
	            sb.append(line + "\n");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            is.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    return sb.toString();
	}

}
