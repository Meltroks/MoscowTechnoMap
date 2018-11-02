package com.meltroks.moscowtechnomap;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapPoint { // класс Ивента, в котором лежат все данные о точке

    public double x, y; // координаты точки

    LatLng Marker; // маркер, к которому все будет применяться
    public String SpotTitle, EventTitle, EventDescription; // имя точки, имя мероприятия, описание мероприятия

    public MapPoint(double x, double y, String EventTitle, String SpotTitle, String EventDescription){ // конструктор
        this.x = x;
        this.y = y;
        this.SpotTitle = SpotTitle;
        this.EventTitle = EventTitle;
        this.EventDescription = EventDescription;
        SetMarker();
    }
    void SetMarker() { // установка маркера на карте
        Marker = new LatLng(x, y);
        MapsActivity.mMap.addMarker(new MarkerOptions().position(Marker).title(EventTitle).snippet(SpotTitle));
    }

}
