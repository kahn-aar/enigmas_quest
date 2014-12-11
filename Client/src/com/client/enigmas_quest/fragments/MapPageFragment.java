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

import com.client.enigmas_quest.EnigmaApplication;
import com.client.enigmas_quest.data.QuestInformation;
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
            .title("Id "+quest.getId()));
        }
        
        /*MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.draggable(true);
        markerOptions.position(new LatLng(23.231251f, 71.648437f));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker());
        mapView.addMarker(markerOptions);
        
        Location mCurrentLocation;
        mCurrentLocation = mLocationClient.getLastLocation();*/
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
        private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
     
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
         * Stop using GPS listener
         * Calling this function will stop using GPS in your app
         * */
        public void stopUsingGPS(){
            if(locationManager != null){
                locationManager.removeUpdates(GPSTracker.this);
            }       
        }
         
        /**
         * Function to get latitude
         * */
        public double getLatitude(){
            if(location != null){
                latitude = location.getLatitude();
            }
             
            // return latitude
            return latitude;
        }
         
        /**
         * Function to get longitude
         * */
        public double getLongitude(){
            if(location != null){
                longitude = location.getLongitude();
            }
             
            // return longitude
            return longitude;
        }
         
        /**
         * Function to check GPS/wifi enabled
         * @return boolean
         * */
        public boolean canGetLocation() {
            return this.canGetLocation;
        }
       
     
        @Override
        public void onLocationChanged(Location location) {
        	Log.d("testRequete", "ca change!!!");
        	Toast.makeText(mContext, "Location change", Toast.LENGTH_LONG).show();
        }

		@Override
		public IBinder onBind(Intent intent) {
			// TODO Auto-generated method stub
			return null;
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
    }

	@Override
	public boolean onMarkerClick(Marker marker) {
		Toast.makeText(getActivity().getApplicationContext(), marker.getTitle(), Toast.LENGTH_SHORT).show();
		return false;
	}
	
}
