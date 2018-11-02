package com.meltroks.moscowtechnomap;

import android.content.Intent;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent intent = new Intent(this, MapsActivity.class);
        final Intent contacts = new Intent(this, ContactsActivity.class);
        final Intent savedEvents = new Intent(this, SavedEventListActivity.class);

        final Button map = findViewById(R.id.map);
        final Button contactsButton = findViewById(R.id.contacts_button);
        final Button savedEventsButton = findViewById(R.id.calendar_button);

        map.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
        contactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(contacts);
            }
        });
        savedEventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { startActivity(savedEvents);
            }
        });
    }


}
