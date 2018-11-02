package com.meltroks.moscowtechnomap;

import android.content.Context;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;

final public class SavedEventListActivity extends AppCompatActivity { // активность органазайера

    public static ArrayList<HashMap<String, String>> Text = new ArrayList<>(); // массив для отображения
    public static HashMap<String, String> map; // данные для заполнения каждой ячейки массива
    private DrawerLayout mdl; // шторка

    public static JSONArray preparingToJson = new JSONArray();
//    public File savedEventsFile = new File(getApplicationContext().getFilesDir(), "SavedEventsData");

    public static void addItem(String topText, String bottomText, Context context){ // добавление ивента в органайзер
        map = new HashMap<>();
        map.put("Title", topText);
        map.put("SubTitle", bottomText);
        Text.add(map);
    }


    public void writeToFile(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("SavedEventsData", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
        }
    }


    public String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("SavedEventsData");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
        } catch (IOException e) {
        }

        return ret;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_event_list);
        final ListView lv = findViewById(R.id.listView_1);

        final Intent eventData = new Intent(this, EventDataActivity_w_delete.class); // ссылка на активность отображения из органазйреа

        SimpleAdapter adapter = new SimpleAdapter(this, Text, android.R.layout.simple_expandable_list_item_2,
                new String[]{"Title", "SubTitle"},
                new int[]{android.R.id.text1, android.R.id.text2}); // адаптер для ArrayList
        lv.setAdapter(adapter); // применение к ArrayList



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() { // установка прослушки на клик на элемент из списка
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    for(int x = 0; x < MapsActivity.events.length; x++) {
                        if (Text.get(i).get("Title").equals(MapsActivity.events[x].EventTitle)) { // установка данных в активность отображения
                            EventDataActivity_w_delete.inText1 = MapsActivity.events[x].EventTitle;
                            EventDataActivity_w_delete.inText2 = MapsActivity.events[x].EventDescription;
                            EventDataActivity_w_delete.inText3 = MapsActivity.events[x].SpotTitle;
                            break;
                        }
                    }
                startActivity(eventData); // переход в активность
            }
        });

        mdl = findViewById(R.id.drawer_layout);

        final Intent map_act = new Intent(this, MapsActivity.class); // ссылки для шторки
        final Intent contacts = new Intent(this, ContactsActivity.class);
        final Intent savedEvents = new Intent(this, SavedEventListActivity.class);

        NavigationView navigationView = findViewById(R.id.nav_view); // все та же шторка что и на карте
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);

                        int id = menuItem.getItemId();
                        if (id == R.id.nav_map){
                            startActivity(map_act);
                        }
                        else if (id == R.id.nav_contacts){
                            startActivity(contacts);
                        }

                        mdl.closeDrawers();
                        return true;
                    }
                });
    }

    @Override
    public void onPause() {
        super.onPause();
        for(int i = 0; i < Text.size(); i++) {
            JSONObject tmp = new JSONObject();
            try {
                tmp.put("Title", Text.get(i).get("Title"));
                tmp.put("SubTitle", Text.get(i).get("SubTitle"));
                preparingToJson.put(tmp);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        writeToFile(preparingToJson.toString(), this);
    }

}
