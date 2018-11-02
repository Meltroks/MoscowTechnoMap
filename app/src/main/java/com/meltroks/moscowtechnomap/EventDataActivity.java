package com.meltroks.moscowtechnomap;

import android.app.Application;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;

final public class EventDataActivity extends AppCompatActivity { // активность, которая открывается чтобы отобразить точку в формате отдельного окна со всеми параметрами (с карты)

    public static String inText1, inText2, inText3; // входящие строки названия ивента, описания ивента и точки проведения ивента


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_data);

        final TextView tv = findViewById(R.id.textView_eventData); // три текста которые меняются в зависимости от точки
        final TextView tv2 = findViewById(R.id.textView_eventData_2);
        final TextView tv3 = findViewById(R.id.textView_eventData_3);
        final Button bt = findViewById(R.id.button_add);

        tv.setText(inText1); // установка нужного текста
        tv2.setText(inText2);
        tv3.setText(inText3);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // реализация кнопки добавления
                    SavedEventListActivity.addItem(inText1, inText3, EventDataActivity .this); // по клику ивент добавляется в органайзе
                Snackbar.make(view, "Событие добавлено", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
        });
    }

}
