package com.example.myapplication;


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
    private LocationListener locationlistener;
    private LocationManager test;
    private final long MIN_TIME = 1000;
    private final long MIN_DIST = 5;
    private LatLng  latlng;

    public void buttonBackHome(View view) {
        openHome();
    }

    public void openHome() {
        Intent intent1 = new Intent(this, MainActivity.class);
        Intent intent2 = new Intent(this, MapsActivityHelper.class);
        intent2.putExtra("Victim", latlng);
        startActivity(intent1);
    }

    //@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_location);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PackageManager.PERMISSION_GRANTED);
    }


    public void onMapReady(GoogleMap googleMap) {

        locationlistener = new LocationListener() {

            public void onLocationChanged(Location location) {
                latlng = new LatLng(location.getLatitude(), location.getLongitude());
                test = (LocationManager) getSystemService(LOCATION_SERVICE);
                try{
                    test.requestLocationUpdates(LocationManager.GPS_PROVIDER,MIN_TIME,MIN_DIST,locationlistener);
                }
                catch(SecurityException e){
                    e.printStackTrace();
                }
                LatLng game = new LatLng(1,103);
                mMap.addMarker(new MarkerOptions().position(game).title("Victim"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(game));

            };
        };
    }

}