package com.client.enigmas_quest.fragments;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.client.enigmas_quest.EnigmaActivity;
import com.client.enigmas_quest.EnigmaApplication;
import com.client.enigmas_quest.GPSTracker;
import com.client.enigmas_quest.PhotoActivity;
import com.client.enigmas_quest.constants.EnigmasConstants;
import com.client.enigmas_quest.data.QuestInformation;
import com.client.enigmas_quest.mappage.Photo;
import com.client.enigmas_quest.mappage.Question;
import com.client.enigmas_quest.mappage.Quetes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapPageFragment extends SupportMapFragment implements
		OnMarkerClickListener {

	public static final String ITEM_NAME = "item_name";

	TextView monTexte;

	EnigmaApplication application;

	GoogleMap mapView;

	GPSTracker gpsTraker;

	@Override
	public void onCreate(Bundle arg0) {
		super.onCreate(arg0);
	}

	@Override
	public View onCreateView(LayoutInflater mInflater, ViewGroup arg1,
			Bundle arg2) {
		return super.onCreateView(mInflater, arg1, arg2);
	}

	@Override
	public void onInflate(Activity arg0, AttributeSet arg1, Bundle arg2) {
		super.onInflate(arg0, arg1, arg2);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		application = (EnigmaApplication) getActivity().getApplicationContext();

		mapView = getMap();
		mapView.setMyLocationEnabled(true);

		gpsTraker = new GPSTracker(getActivity().getApplicationContext());

		for (QuestInformation quest : application.getPositions()) {
			mapView.addMarker(new MarkerOptions().position(
					new LatLng(quest.getPosition().getLatitude(), quest
							.getPosition().getLongitude())).title(
					String.valueOf(quest.getId())));
		}
		mapView.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
				45.3788, -71.9291), 15));

	}

	private double distance(double lat1, double lon1, double lat2, double lon2,
			char unit) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
				* Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (unit == 'K') {
			dist = dist * 1.609344 * 1000;
		} else if (unit == 'N') {
			dist = dist * 0.8684;
		}
		return (dist);

	}

	private double rad2deg(double rad) {

		return (rad * 180 / Math.PI);
	}

	private double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	public class GPSTracker extends Service implements LocationListener {

		private final Context mContext;

		// flag for GPS status
		boolean isGPSEnabled = false;

		// flag for network status
		boolean isNetworkEnabled = false;

		// flag for GPS status
		boolean canGetLocation = false;

		Location location; // location
		double latitude; // latitude
		double longitude; // longitude

		// The minimum distance to change Updates in meters
		private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10
																		// meters

		// The minimum time between updates in milliseconds
		private static final long MIN_TIME_BW_UPDATES = 1000 * 15 * 1; // 15sec

		// Declaring a Location Manager
		protected LocationManager locationManager;

		public GPSTracker(Context context) {
			this.mContext = context;
			getLocation();
		}

		public Location getLocation() {
			try {
				locationManager = (LocationManager) mContext
						.getSystemService(LOCATION_SERVICE);

				// getting GPS status
				isGPSEnabled = locationManager
						.isProviderEnabled(LocationManager.GPS_PROVIDER);

				// getting network status
				isNetworkEnabled = locationManager
						.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

				if (!isGPSEnabled && !isNetworkEnabled) {
					// no network provider is enabled
				} else {
					this.canGetLocation = true;
					// First get location from Network Provider
					if (isNetworkEnabled) {
						locationManager.requestLocationUpdates(
								LocationManager.NETWORK_PROVIDER,
								MIN_TIME_BW_UPDATES,
								MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
						Log.d("Network", "Network");
						if (locationManager != null) {
							location = locationManager
									.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
							if (location != null) {
								latitude = location.getLatitude();
								longitude = location.getLongitude();
							}
						}
					}
					// if GPS Enabled get lat/long using GPS Services
					if (isGPSEnabled) {
						if (location == null) {
							locationManager.requestLocationUpdates(
									LocationManager.GPS_PROVIDER,
									MIN_TIME_BW_UPDATES,
									MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
							Log.d("GPS Enabled", "GPS Enabled");
							if (locationManager != null) {
								location = locationManager
										.getLastKnownLocation(LocationManager.GPS_PROVIDER);
								if (location != null) {
									latitude = location.getLatitude();
									longitude = location.getLongitude();
								}
							}
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return location;
		}

		/**
		 * Stop using GPS listener Calling this function will stop using GPS in
		 * your app
		 * */
		public void stopUsingGPS() {
			if (locationManager != null) {
				locationManager.removeUpdates(GPSTracker.this);
			}
		}

		/**
		 * Function to get latitude
		 * */
		public double getLatitude() {
			if (location != null) {
				latitude = location.getLatitude();
			}

			// return latitude
			return latitude;
		}

		/**
		 * Function to get longitude
		 * */
		public double getLongitude() {
			if (location != null) {
				longitude = location.getLongitude();
			}
			return longitude;
		}

		/**
		 * Function to check GPS/wifi enabled
		 * 
		 * @return boolean
		 * */
		public boolean canGetLocation() {
			return this.canGetLocation;
		}

		@Override
		public void onLocationChanged(Location location) {
			Log.d("testRequete", "ca change!!!");
			Toast.makeText(mContext, "Location change", Toast.LENGTH_LONG)
					.show();
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public IBinder onBind(Intent intent) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	@Override
	public boolean onMarkerClick(Marker marker) {
		int i = Integer.parseInt(marker.getTitle());
		Question enigme = (Question) application.getEngimaById(i);
		double distance = distance(marker.getPosition().latitude,
				marker.getPosition().longitude, gpsTraker.getLatitude(),
				gpsTraker.getLongitude(), 'K');
		if(distance < 15) {
			if (enigme instanceof Question) {
				Intent intent = new Intent(getActivity(), EnigmaActivity.class);
				intent.putExtra(EnigmasConstants.ENIGMA_ID, i);
				startActivity(intent);

				/*
				 * } else if (enigme instanceof Photo) { Intent intent = new
				 * Intent(getActivity(), PhotoActivity.class);
				 * intent.putExtra(EnigmasConstants.ENIGMA_ID,
				 * Integer.valueOf(marker.getTitle())); startActivity(intent);
				 */
			} else {
				System.out.println("echec");
			}
		}
		else {
			Toast.makeText(getActivity().getApplicationContext(), "Vous êtes trop loin ("+i+")", 
					Toast.LENGTH_LONG).show();
		}

		

		return false;
	}

}
