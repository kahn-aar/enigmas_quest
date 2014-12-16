package com.client.enigmas_quest.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

import com.client.enigmas_quest.BattleActivity;
import com.client.enigmas_quest.CreateAccountActivity;
import com.client.enigmas_quest.EnigmaApplication;
import com.client.enigmas_quest.GPSTracker;
import com.client.enigmas_quest.LoginActivity;
import com.client.enigmas_quest.Map_Activity;
import com.client.enigmas_quest.mappage.Combat;

public class EnigmaService extends IntentService {

	EnigmaApplication application;

	public EnigmaService() {
		super("Enigma service");
	}

	// intervalle entre les maj = 5 secondes
	static final int DELAY = 60000;

	GPSTracker gpsTraker;

	// est-ce que le service
	// est en train de s’exécuter ?
	private boolean runFlag = false;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	private LocationManager			locationMgr				= null;
	 private LocationListener		onLocationChange	= new LocationListener()
	 {
	 @Override
	 public void onProviderEnabled(String provider)
	 {
	 }

	 @Override
	 public void onProviderDisabled(String provider)
	 {
	 }

	 @Override
	 public void onLocationChanged(Location location)
	 {
		 System.out.println(location);
			application.sendPositionToServer(location);
		 
			Combat battle = application
					.getBattle(application.getPlayer().getName());
			if (battle != null && !runFlag) {
				/*Intent intent = new Intent(Map_Activity.this, BattleActivity.class);
                startActivity(intent);*/

			}
	 }

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	 };

	@Override
	public void onCreate() {
		System.out.println("created service");
		application = (EnigmaApplication) getApplication();
		//locationMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		//locationMgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0, onLocationChange);
		//locationMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, onLocationChange);
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onHandleIntent(Intent intent) {
	}

}
