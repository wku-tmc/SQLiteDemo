package com.celiveo.sqlitedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private SQLiteHelper sQLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sQLiteHelper = new SQLiteHelper(MainActivity.this);

        final EditText editTextTitle = (EditText) findViewById(R.id.editTextTitle);
        final EditText editTextDate = (EditText) findViewById(R.id.editTextDate);
        final EditText editTextLocation = (EditText) findViewById(R.id.editTextLocation);
        final EditText editTextDescription = (EditText) findViewById(R.id.editTextDescription);
        Button buttonAddEvent = (Button) findViewById(R.id.buttonAddEvent);

        buttonAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTextTitle.getText().toString();
                String date = editTextDate.getText().toString();
                String location = editTextLocation.getText().toString();
                String description = editTextDescription.getText().toString();
                Event event = new Event(title,location,description,date);
                sQLiteHelper.insertRecord(event);
            }
        });

        Button buttonViewEvents = (Button) findViewById(R.id.buttonViewEvents);

        buttonViewEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListAllEventsActivity.class);
                startActivity(intent);

            }
        });

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
