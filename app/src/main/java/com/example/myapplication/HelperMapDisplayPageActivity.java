package com.example.myapplication;

//import androidx.fragment.app.FragmentActivity;
//
//import android.location.Location;
//import android.location.LocationListener;
//import android.location.LocationManager;
//import android.os.Bundle;
//
//import com.example.myapplication.databinding.ActivityMapsBinding;
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.MarkerOptions;
//import com.example.myapplication.databinding.ActivityMapsHelperBinding;
//
//public class HelperMapDisplayPageActivity extends FragmentActivity implements OnMapReadyCallback {
//
////    private GoogleMap mMap;
////    private ActivityMapsHelperBinding binding;
//    VictimRegistrationPageActivity victimRegPageActivity = new VictimRegistrationPageActivity();
//
//    // from GLT.java
//    private GoogleMap mMap;
//    private ActivityMapsBinding binding;
//    private LocationListener locationListener;
//    private LocationManager test;
//    private final long MIN_TIME = 1000;
//    private final long MIN_DIST = 5;
//    private LatLng latLng;
//    private int latitude, longitude;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        binding = ActivityMapsBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//    }
//
//    /**
//     * Manipulates the map once available.
//     * This callback is triggered when the map is ready to be used.
//     * This is where we can add markers or lines, add listeners or move the camera. In this case,
//     * we just add a marker near Sydney, Australia.
//     * If Google Play services is not installed on the device, the user will be prompted to install
//     * it inside the SupportMapFragment. This method will only be triggered once the user has
//     * installed Google Play services and returned to the app.
//     */
//
//
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//        //LatLng victim = getIntent().getExtras().getParcelable("Victim");
//
//        //mMap.moveCamera(CameraUpdateFactory.newLatLng(testLatLng));
//
//        locationListener = new LocationListener() {
//            public void onLocationChanged(Location location) {
//                latLng = new LatLng(location.getLatitude(), location.getLongitude());
//                latitude = (int) location.getLatitude();
//                longitude = (int) location.getLongitude();
//                test = (LocationManager) getSystemService(LOCATION_SERVICE);
//                mMap.addMarker(new MarkerOptions().position(latLng).title("Marker in KL"));
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//                try {
//                    test.requestLocationUpdates(LocationManager.GPS_PROVIDER,MIN_TIME,MIN_DIST,locationListener);
//                } catch (SecurityException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        };
//
//        LatLng test = new LatLng(latitude, longitude);
//
//        mMap.addMarker(new MarkerOptions().position(test).title("Marker in KL"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(test));
//
//    }
//}
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.myapplication.databinding.ActivityMapsBinding;

public class HelperMapDisplayPageActivity extends FragmentActivity implements OnMapReadyCallback{

        private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private LocationListener locationlistener;
    private LocationManager test;
    private final long MIN_TIME = 1000;
    private final long MIN_DIST = 5;
    private LatLng  latlng;
    private LatLng NEW;
    private int LAT = 123;
    private int LONG = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PackageManager.PERMISSION_GRANTED);

        if(getIntent().getExtras() !=null)
        {
            LAT = getIntent().getExtras().getInt("Lat");
            LONG = getIntent().getExtras().getInt("Long");

        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;




        locationlistener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                try{
                    latlng = new LatLng(location.getLatitude(),location.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latlng).title("You"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));

                    NEW = new LatLng(LAT,LONG);
                    mMap.addMarker(new MarkerOptions().position(NEW).title("Victim"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(NEW));

                    //passToActivity(location);
                }
                catch(SecurityException e){
                    e.printStackTrace();
                }
            }

        };

        test = (LocationManager) getSystemService(LOCATION_SERVICE);
        try{
            test.requestLocationUpdates(LocationManager.GPS_PROVIDER,MIN_TIME,MIN_DIST,locationlistener);
        }
        catch(SecurityException e){
            e.printStackTrace();
        }
    }

}
// TODO Try outputting latlng as marker
// TODO Test latlng
// TODO Emulator location from where?