package com.client.enigmas_quest.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.client.enigmas_quest.EnigmaActivity;
import com.client.enigmas_quest.EnigmaApplication;
import com.client.enigmas_quest.GPSTracker;
import com.client.enigmas_quest.constants.EnigmasConstants;
import com.client.enigmas_quest.data.QuestInformation;
import com.client.enigmas_quest.mappage.Question;
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


	@Override
	public boolean onMarkerClick(Marker marker) {
		int i = Integer.parseInt(marker.getTitle());
		Question enigme = (Question) application.getEngimaById(i);
		double distance = distance(marker.getPosition().latitude,
				marker.getPosition().longitude,
				gpsTraker.getLatitude(), gpsTraker.getLongitude(), 'K');

		
		  if (enigme instanceof Question) { Intent intent = new
		  Intent(getActivity(), EnigmaActivity.class);
		  intent.putExtra(EnigmasConstants.ENIGMA_ID, i);
		  startActivity(intent);
		  
		 /*} else if (enigme instanceof Photo) { Intent intent = new
		  Intent(getActivity(), PhotoActivity.class);
		  intent.putExtra(EnigmasConstants.ENIGMA_ID,
		  Integer.valueOf(marker.getTitle())); startActivity(intent);
		  */
		  } else { System.out.println("echec"); }
		 
		return false;
	}

}
