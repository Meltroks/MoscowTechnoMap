package com.meltroks.moscowtechnomap;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class EventAdapter extends ArrayAdapter <Event>{

    Context context;

    public EventAdapter(Context context, Event[] objects) {
        super(context, R.layout.event_view, objects);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = View.inflate(context, R.layout.event_view, null);
        }
        TextView name = convertView.findViewById(R.id.name);
        TextView date = convertView.findViewById(R.id.date);

        Event ev = getItem(position);

        name.setText(ev.getName());
        date.setText(ev.getDate());

        return convertView;
    }
}
