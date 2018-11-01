package com.meltroks.moscowtechnomap;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

final public class SavedEventListActivity extends AppCompatActivity {

    public static ArrayList<HashMap<String, String>> Text = new ArrayList<>();
    public static HashMap<String, String> map;
    private DrawerLayout mdl;


    public static void addItem(String topText, String bottomText){
        map = new HashMap<>();
        map.put("Title", topText);
        map.put("SubTitle", bottomText);
        Text.add(map);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_event_list);
        final ListView lv = findViewById(R.id.listView_1);

        final Intent eventData = new Intent(this, EventDataActivity_w_delete.class);

        SimpleAdapter adapter = new SimpleAdapter(this, Text, android.R.layout.simple_expandable_list_item_2,
                new String[]{"Title", "SubTitle"},
                new int[]{android.R.id.text1, android.R.id.text2});
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    for(int x = 0; x < MapsActivity.events.length; x++) {
                        if (Text.get(i).get("Title").equals(MapsActivity.events[x].EventTitle)) {
                            EventDataActivity_w_delete.inText1 = MapsActivity.events[x].EventTitle;
                            EventDataActivity_w_delete.inText2 = MapsActivity.events[x].EventDescription;
                            EventDataActivity_w_delete.inText3 = MapsActivity.events[x].SpotTitle;
                            break;
                        }
                    }
                startActivity(eventData);
            }
        });

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
}
