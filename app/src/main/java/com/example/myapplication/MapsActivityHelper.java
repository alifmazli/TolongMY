package com.example.myapplication;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.myapplication.databinding.ActivityMapsHelperBinding;
import com.example.myapplication.MapsActivity;

public class MapsActivityHelper extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsHelperBinding binding;
    VictimRegistrationPage vrp = new VictimRegistrationPage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsHelperBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //LatLng victim = getIntent().getExtras().getParcelable("Victim");
        for(int i=0;i<1;i++) {
            LatLng testLatLng = new LatLng(vrp.person.getLatitude(), vrp.person.getLongitude());
            mMap.addMarker(new MarkerOptions().position(testLatLng).title(vrp.person.getName()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(testLatLng));
        }
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(testLatLng));
    }
}

// TODO Try outputting latlng as marker
// TODO Test latlng
// TODO Emulator location from where?