package com.meltroks.moscowtechnomap;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Event [] eventArray = new Event[3];
        eventArray[0] = new Event("RAVE Dissident", 250202);

        EventAdapter test = new EventAdapter(this, eventArray);
        ListView listView = findViewById(R.id.eventList);
        listView.setAdapter(test);

    }

}
