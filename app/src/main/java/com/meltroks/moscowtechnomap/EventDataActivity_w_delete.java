package com.meltroks.moscowtechnomap;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

final public class EventDataActivity_w_delete extends AppCompatActivity { // активность, которая открывается чтобы отобразить точку в формате отдельного окна со всеми параметрами (из оргазайзера)

    public static String inText1, inText2, inText3; // поступающие текстовые поля


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_data_w_delete);

        final TextView tv = findViewById(R.id.textView_eventData);
        final TextView tv2 = findViewById(R.id.textView_eventData_2);
        final TextView tv3 = findViewById(R.id.textView_eventData_3);
        final Button bt = findViewById(R.id.button_delete); // кнопка удаления ивента из списка

        tv.setText(inText1); // установка текста
        tv2.setText(inText2);
        tv3.setText(inText3);

        final Intent t = new Intent(this, SavedEventListActivity.class);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // реализация кнопки удаления
                for(int x = 0; x < MapsActivity.events.length; x++) {
                    if(inText1.equals(SavedEventListActivity.Text.get(x).get("Title"))) {
                        SavedEventListActivity.Text.remove(x);
                        startActivity(t);
                        break;
                    }
                }
            }
        });
    }

}
