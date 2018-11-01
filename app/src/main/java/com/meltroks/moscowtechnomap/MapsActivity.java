package com.meltroks.moscowtechnomap;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.io.IOException;

final public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    static GoogleMap mMap;
    private DrawerLayout mdl;
    getData gd = new getData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mdl = findViewById(R.id.drawer_layout);

        final Intent map_act = new Intent(this, MapsActivity.class);
        final Intent contacts = new Intent(this, ContactsActivity.class);
        final Intent savedEvents = new Intent(this, SavedEventListActivity.class);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        int id = menuItem.getItemId();

                        if(id == R.id.nav_refresh){
                            DataRefresh dr = new DataRefresh();
                            try {
                                dr.sendGet();
                                dr.dataRefill();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else if (id == R.id.nav_map){
                            startActivity(map_act);
                        } else if(id == R.id.nav_savedEvents){
                            startActivity(savedEvents);
                        } else if (id == R.id.nav_contacts){
                            startActivity(contacts);
                        }

                        mdl.closeDrawers();
                        return true;
                    }
                });
    }

    public static MapPoint[] events;

    @Override
    public void onMapReady(GoogleMap googleMap) {

        final Intent eventData = new Intent(this, EventDataActivity.class);
        gd.execute();

        mMap = googleMap;
        while (true){
            if(gd.textResult != null) break;
        }

        textDivider.text = gd.textResult;
        textDivider.parse();

        while(true){
            if(DataContainer.ActiveSpots[0] != null) break;
        }

        int countEvents = 0;
        for(int i = 0; i< DataContainer.ActiveSpots.length; i++){
            if(DataContainer.ActiveSpots[i] != null) countEvents++;
            else break;
        }

        events = new MapPoint[countEvents];

        for(int i = 0; i < DataContainer.ActiveSpots.length; i++){
            if(DataContainer.ActiveSpots[i] != null) {
                for (int j = 0; j < DataContainer.SpotTitles.length; j++) {
                    if (DataContainer.ActiveSpots[i].equals(DataContainer.SpotTitles[j])) {
                        events[i] = new MapPoint(DataContainer.SpotX[j], DataContainer.SpotY[j], DataContainer.EventTitles[i], DataContainer.ActiveSpots[i], DataContainer.Descriptions[i]);
                        break;
                    }
                }
            }
        }

        mMap.setOnInfoWindowClickListener(
                new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        for(int i = 0; i<events.length; i++){
                            if(marker.getTitle().equals(events[i].EventTitle)){
                                EventDataActivity.inText1 = events[i].EventTitle;
                                EventDataActivity.inText2 = events[i].EventDescription;
                                EventDataActivity.inText3 = events[i].SpotTitle;
                                break;
                            }
                        }
                        startActivity(eventData);
                    }
                }
        );

        LatLng Moscow = new LatLng(55.7522200, 37.6155600);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Moscow, 11));
    }
}
