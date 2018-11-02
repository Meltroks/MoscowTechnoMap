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

final public class MapsActivity extends FragmentActivity implements OnMapReadyCallback { // активность с картой

    static final String[] savingTitles = new String[10]; // сохранение промежуточных данных в отдельные массивы чтобы потом восстановить
    static final String[] savingSpots = new String[10];
    static final String[] savingDescs = new String[10];
    static final double[] savingX = new double[10];
    static final double[] savingY = new double[10];

    static GoogleMap mMap; // инициализация фрагмента карты
    private DrawerLayout mdl; // шторка поверх всего
    getData gd = new getData(); // инициализация

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map); // установка связи с  картой
        mapFragment.getMapAsync(this);

        mdl = findViewById(R.id.drawer_layout); // связь с шторкой

        final Intent map_act = new Intent(this, MapsActivity.class); // ссылки на другие активности для шторки
        final Intent contacts = new Intent(this, ContactsActivity.class);
        final Intent savedEvents = new Intent(this, SavedEventListActivity.class);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront(); // вытаскиваем интерфейс с которым будем взаимодействовать на верхний слой

        navigationView.setNavigationItemSelectedListener( // прослушивание кликов на кнопки в шторке
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        int id = menuItem.getItemId();
                        if(id == R.id.nav_savedEvents){
                            startActivity(savedEvents);
                        } else if (id == R.id.nav_contacts){
                            startActivity(contacts);
                        }

                        mdl.closeDrawers();
                        return true;
                    }
                });
    }

    public static MapPoint[] events; // массив с ивентами

    @Override
    public void onMapReady(GoogleMap googleMap) {

        final Intent eventData = new Intent(this, EventDataActivity.class);
        mMap = googleMap;
        if(DataContainer.isTurned == false) {
            gd.execute();
            while (true) { // задержка для того чтобы программа успела получить данные
                if (gd.textResult != null) break;
            }

            textDivider.text = gd.textResult; // передаем в парсер текста текст
            textDivider.parse(); // включаем парсинг

            while (true) { // задержка для того чтобы парс завершился до конца
                if (DataContainer.ActiveSpots[0] != null) break;
            }

        }

        int countEvents = 0; // счетчик ивентов
        for (int i = 0; i < DataContainer.ActiveSpots.length; i++) { // подсчет инвентов
            if (DataContainer.ActiveSpots[i] != null) countEvents++;
            else break;
        }

        events = new MapPoint[countEvents]; // реинициализация массива ивентов

        for(int i = 0; i < DataContainer.ActiveSpots.length; i++){ // цикл расстановки точек на карту
            if(DataContainer.ActiveSpots[i] != null) {
                for (int j = 0; j < DataContainer.SpotTitles.length; j++) {
                    if (DataContainer.ActiveSpots[i].equals(DataContainer.SpotTitles[j])) {
                        events[i] = new MapPoint(DataContainer.SpotX[j], DataContainer.SpotY[j], DataContainer.EventTitles[i], DataContainer.ActiveSpots[i], DataContainer.Descriptions[i]);
                        break;
                    }
                }
            }
        }

        mMap.setOnInfoWindowClickListener( // установка прослушки кликов на окно с информацией
                new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        for(int i = 0; i<events.length; i++){ // передача нужной информации в активность отображения
                            if(marker.getTitle().equals(events[i].EventTitle)){
                                EventDataActivity.inText1 = events[i].EventTitle;
                                EventDataActivity.inText2 = events[i].EventDescription;
                                EventDataActivity.inText3 = events[i].SpotTitle;
                                break;
                            }
                        }
                        startActivity(eventData); // переход в активность
                    }
                }
        );

        LatLng Moscow = new LatLng(55.7522200, 37.6155600); // установка точки Москвы
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Moscow, 11)); // перемещение карты на москву с зумом в 11
    }

}