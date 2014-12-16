package com.client.enigmas_quest.service;

import android.app.IntentService;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;

import com.client.enigmas_quest.EnigmaApplication;
import com.client.enigmas_quest.GPSTracker;
import com.client.enigmas_quest.mappage.Combat;

public class EnigmaService extends IntentService {

	EnigmaApplication application;

	public EnigmaService() {
		super("Enigma service");
		application = (EnigmaApplication) getApplication();
		gpsTraker = new GPSTracker(getApplicationContext());
	}

	// intervalle entre les maj = 5 secondes
	static final int DELAY = 5000;

	GPSTracker gpsTraker;

	// est-ce que le service
	// est en train de s’exécuter ?
	private boolean runFlag = false;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);

		// Send position

		Location position = gpsTraker.getLocation();
		application.sendPositionToServer(position);
		System.out.println(position);
		Combat battle = application
				.getBattle(application.getPlayer().getName());
		if (battle != null && !runFlag) {
			// Do the battle

		}
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onHandleIntent(Intent intent) {
	}

}
