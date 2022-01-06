package com.example.myapplication;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.myapplication.databinding.ActivityMapsBinding;

public class GoogleLocationTracker extends AppCompatActivity {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private LocationListener locationListener;
    private LocationListener TEST;
    private LocationManager test;
    private final long MIN_TIME = 1000;
    private final long MIN_DIST = 5;
    private LatLng latLng;
    private int latitude ;
    private int longitude ;


    public void buttonBackHome(View view) {
        openHome();
    }

//    public void openHome() {
//        getLatitude();
//        getLongitude();
//        Intent intent1 = new Intent(this, MainActivity.class);
//        Intent intent2 = new Intent(this, MapsActivityHelper.class);
//        intent2.putExtra("Victim", latlng);
//        startActivity(intent1);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_location);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PackageManager.PERMISSION_GRANTED);
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                //test = (LocationManager) getSystemService(LOCATION_SERVICE);
                try{
                    latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    latitude = 3;//(int) location.getLatitude();
                    longitude = 101;//(int) location.getLongitude();
                    //test.requestLocationUpdates(LocationManager.GPS_PROVIDER,MIN_TIME,MIN_DIST,locationListener);
                }
                catch(SecurityException e){
                    e.printStackTrace();
                }

                LatLng testLatLng = new LatLng(latitude, longitude);

                mMap.addMarker(new MarkerOptions().position(testLatLng).title("Victim"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(testLatLng));

            }
        };
        test = (LocationManager) getSystemService(LOCATION_SERVICE);
        try {
            test.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME, MIN_DIST, locationListener);
        }
        catch (SecurityException e){
            e.printStackTrace();
        }
    }

    public void openHome() {



        Intent intent1 = new Intent(this,MainActivity.class);
        Intent intent2 = new Intent(this, HelperMapDisplayPageActivity.class);
        intent2.putExtra("Lat", latitude);
        intent2.putExtra("Long",longitude);
        startActivity(intent2);
    }

    public int getLatitude(){
        return latitude;
    }

    public int getLongitude(){
        return longitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }
}