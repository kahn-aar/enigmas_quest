package com.client.enigmas_quest.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.os.AsyncTask;
import android.util.Log;

import com.client.enigmas_quest.constants.EnigmasConstants;

public class RequestRESTAsync extends AsyncTask<String, Void, JSONObject> {
	
	private HttpClient httpclient = new DefaultHttpClient();
	
	private final String baseUrl = "http://192.168.0.104:8080/EnigmaRest/rest/enigma";
	
	private int requestType;
	
	public RequestRESTAsync(int requestType) {
		this.requestType = requestType;
	}

	@Override
	protected JSONObject doInBackground(String... arg0) {
		JSONObject object = null;
		switch (this.requestType) {
			case EnigmasConstants.REST_AUTHENTICATION:
				if(arg0.length == 2) {
					Map<String, String> hm = new HashMap<String, String>();
					hm.put("login", arg0[0]);
					hm.put("password", arg0[1]);
					String url = addParametersToUrl(this.baseUrl+"/login", hm);

					object = responseTOJSON(executeRequestGet(url));
				}
				break;
			case EnigmasConstants.REST_ACOUNT_CREATION:
				if(arg0.length == 2) {
					Map<String, String> hm = new HashMap<String, String>();
					hm.put("login", arg0[0]);
					hm.put("password", arg0[1]);
				}
				break;	
			case EnigmasConstants.REST_GET_ALL_POSITIONS:
				String url = this.baseUrl+"/allQuest";
				object = responseTOJSON(executeRequestGet(url));
				break;
		}
		return object;
	}
	
	@Override
	protected void onPostExecute(JSONObject result) {
		super.onPostExecute(result);
    }
	

	private HttpResponse executeRequestGet(String url) {
		HttpGet httpget = new HttpGet(url); 
		httpget.setHeader("Content-type", "application/json");
		httpget.setHeader("Accept", "application/json");
		HttpResponse response;
		try {
	        response = httpclient.execute(httpget);
	        
	        return response;
	    } catch (Exception e) {
	    	return null;
	    }
	}
	
	private String addParametersToUrl(String url, Map<String, String> hm){
	    if(!url.endsWith("?"))
	        url += "?";
	    
	    List<BasicNameValuePair> params = new LinkedList<BasicNameValuePair>();
	    for(String currentKey : hm.keySet()){
	    	params.add(new BasicNameValuePair(currentKey, hm.get(currentKey)));
	    }
	    
	    String paramString = URLEncodedUtils.format(params, "utf-8");
	    url += paramString;
	    return url;
	}
	
	private JSONObject responseTOJSON(HttpResponse response) {
		
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			StringBuilder builder = new StringBuilder();
			try {
				for (String line = null; (line = reader.readLine()) != null;) {
				    builder.append(line).append("\n");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Log.d("testRequete", builder.toString());
			JSONTokener tokener = new JSONTokener(builder.toString());
			JSONObject finalResult = null;
			try {
				finalResult = new JSONObject(tokener);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return finalResult;
		
	}

}
