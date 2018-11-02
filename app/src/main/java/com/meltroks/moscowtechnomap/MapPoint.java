package com.meltroks.moscowtechnomap;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapPoint {

    public double x, y;

    LatLng Marker;
    public String SpotTitle, EventTitle, EventDescription;

    public MapPoint(double x, double y, String EventTitle, String SpotTitle, String EventDescription){
        this.x = x;
        this.y = y;
        this.SpotTitle = SpotTitle;
        this.EventTitle = EventTitle;
        this.EventDescription = EventDescription;
        SetMarker();
    }
    void SetMarker() {
        Marker = new LatLng(x, y);
        MapsActivity.mMap.addMarker(new MarkerOptions().position(Marker).title(EventTitle).snippet(SpotTitle));
    }

}
