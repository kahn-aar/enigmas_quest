package com.client.enigmas_quest.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
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
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapPageFragment extends SupportMapFragment implements OnMarkerClickListener {

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
        
        for(QuestInformation quest : application.getPositions()) {
        	mapView.addMarker(new MarkerOptions()
            .position(new LatLng(quest.getPosition().getLatitude(), quest.getPosition().getLongitude()))
            .title(String.valueOf(quest.getId())));
        }
        
        mapView.setOnMarkerClickListener(new OnMarkerClickListener() {
			
			@Override
			public boolean onMarkerClick(Marker marker) {
				Toast.makeText(getActivity().getApplicationContext(), marker.getTitle(), Toast.LENGTH_SHORT).show();
				Quetes enigme = application.getEngimaById(Integer.valueOf(marker.getId()));
				/*if (enigme instanceof Question) {
					Intent intent = new Intent(getActivity(), EnigmaActivity.class);
					intent.putExtra(EnigmasConstants.ENIGMA_ID, Integer.valueOf(marker.getTitle()));
					startActivity(intent);
				} else if (enigme instanceof Photo) {
					Intent intent = new Intent(getActivity(), PhotoActivity.class);
					intent.putExtra(EnigmasConstants.ENIGMA_ID, Integer.valueOf(marker.getTitle()));
					startActivity(intent);
				} else {
					System.out.println("echec");
				}*/
				return false;
			}
		});
        /*MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.draggable(true);
        markerOptions.position(new LatLng(23.231251f, 71.648437f));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker());
        mapView.addMarker(markerOptions);
        
        Location mCurrentLocation;
        mCurrentLocation = mLocationClient.getLastLocation();*/
    }


    
	@Override
	public boolean onMarkerClick(Marker marker) {
		Toast.makeText(getActivity().getApplicationContext(), marker.getTitle(), Toast.LENGTH_SHORT).show();
		Quetes enigme = application.getEngimaById(Integer.valueOf(marker.getId()));
		if (enigme instanceof Question) {
			Intent intent = new Intent(getActivity(), EnigmaActivity.class);
			intent.putExtra(EnigmasConstants.ENIGMA_ID, Integer.valueOf(marker.getTitle()));
			startActivity(intent);
		} else if (enigme instanceof Photo) {
			Intent intent = new Intent(getActivity(), PhotoActivity.class);
			intent.putExtra(EnigmasConstants.ENIGMA_ID, Integer.valueOf(marker.getTitle()));
			startActivity(intent);
		} else {
			System.out.println("echec");
		}
		return false;
	}
	
}
