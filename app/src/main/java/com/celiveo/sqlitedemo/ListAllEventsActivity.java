package com.celiveo.sqlitedemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class ListAllEventsActivity extends AppCompatActivity {

    private SQLiteHelper sQLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_events);

        sQLiteHelper = new SQLiteHelper(ListAllEventsActivity.this);

        // Construct the data source
        ArrayList<Event> events = sQLiteHelper.getAllRecords();
// Create the adapter to convert the array to views
        EventsAdapter adapter = new EventsAdapter(this, events);
// Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

    }

}
