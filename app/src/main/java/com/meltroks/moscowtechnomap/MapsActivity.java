package com.meltroks.moscowtechnomap;

import android.renderscript.ScriptGroup;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;

import org.json.*;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    public class Spot{
        double x, y;
        String name;
        public Spot(double X, double Y, String Name) {
            x = X;
            y = Y;
            name = Name;
            SetMarker();
        }
        void SetMarker() {
            LatLng Marker = new LatLng(x, y);
            mMap.addMarker(new MarkerOptions().position(Marker).title(name));
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
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

        Spot Dissident = new Spot(55.760107, 37.646816, "Dissident");
        Spot Sharaga = new Spot(55.763611, 37.643633, "Харитон");
        LatLng Moscow = new LatLng(55.7522200, 37.6155600);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Moscow, 11));
    }

}
