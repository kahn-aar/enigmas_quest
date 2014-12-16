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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
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
	
	private final String baseUrl = "http://10.238.50.213:8080/EnigmaRest/rest/enigma";
	
	private int requestType;
	
	public RequestRESTAsync(int requestType) {
		this.requestType = requestType;
	}

	@Override
	protected JSONObject doInBackground(String... arg0) {
		JSONObject object = null;
		String url = "";
		Map<String, String> hm = new HashMap<String, String>();
		switch (this.requestType) {
			case EnigmasConstants.REST_AUTHENTICATION:
				if(arg0.length == 2) {
					
					hm.put("login", arg0[0]);
					hm.put("password", arg0[1]);
					url = addParametersToUrl(this.baseUrl+"/login", hm);
					System.out.println(url);

					object = responseTOJSON(executeRequestGet(url));
				}
				break;
			case EnigmasConstants.REST_ACOUNT_CREATION:
				if(arg0.length == 2) {
					HttpClient httpClient = new DefaultHttpClient();
		            HttpPost post = new HttpPost(this.baseUrl + "/register");
		            post.setHeader("content-type", "application/json");

		            //Construimos el objeto cliente en formato JSON
		            JSONObject dato = new JSONObject();

		            try {
		                dato.put("login", arg0[0]);
		                dato.put("password", arg0[1]);

		                StringEntity entity = new StringEntity(dato.toString());
		                post.setEntity(entity);

		                httpClient.execute(post);
		            } catch (Exception e) {
		            	
		            }
		            
				}
				break;	
			case EnigmasConstants.REST_GET_ALL_POSITIONS:
				url = this.baseUrl+"/allQuest";
				object = responseTOJSON(executeRequestGet(url));
				break;
			case EnigmasConstants.REST_GET_ENIGMA:
				hm.put("num", arg0[0]);
				
				url = addParametersToUrl(this.baseUrl+"/quest", hm);
				object = responseTOJSON(executeRequestGet(url));
				break;
			case EnigmasConstants.REST_GET_PLAYER_STATS:
				hm.put("login", arg0[0]);
				
				url = addParametersToUrl(this.baseUrl+"/details", hm);
				object = responseTOJSON(executeRequestGet(url));
				break;
			case EnigmasConstants.REST_GET_BATTLE:
				hm.put("login", arg0[0]);
				
				url = addParametersToUrl(this.baseUrl+"/battle", hm);
				object = responseTOJSON(executeRequestGet(url));
				break;
			case EnigmasConstants.REST_POST_ENIGMA_RESPONSE:
				hm.put("num", arg0[0]);
				hm.put("login", arg0[1]);
				hm.put("answer", arg0[2]);
				
				url = addParametersToUrl(this.baseUrl+"/quest", hm);
				object = responseTOJSON(executeRequestGet(url));
				break;
			case EnigmasConstants.REST_POST_POS_PLAYER:
				hm.put("latitude", arg0[0]);
				hm.put("longitude", arg0[1]);
				
				url = addParametersToUrl(this.baseUrl+"/pos", hm);
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
