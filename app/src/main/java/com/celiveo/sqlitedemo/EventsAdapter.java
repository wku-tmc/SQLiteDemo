package com.celiveo.sqlitedemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EventsAdapter extends ArrayAdapter<Event> {
    public EventsAdapter(Context context, ArrayList<Event> events) {
        super(context, 0, events);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Event event = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_event, parent, false);
        }
        // Lookup view for data population
        TextView textViewTitle = (TextView) convertView.findViewById(R.id.textViewTitle);
        TextView textViewDate = (TextView) convertView.findViewById(R.id.textViewDate);
        TextView textViewLocation = (TextView) convertView.findViewById(R.id.textViewLocation);
        TextView textViewDescription = (TextView) convertView.findViewById(R.id.textViewDescription);
        // Populate the data into the template view using the data object
        textViewTitle.setText(event.getTitle());
        textViewDate.setText(event.getDate());
        textViewLocation.setText(event.getLocation());
        textViewDescription.setText(event.getDescription());

        // Return the completed view to render on screen
        return convertView;
    }
}
