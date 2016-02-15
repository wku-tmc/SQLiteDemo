package com.celiveo.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;


//We are creating a java file called SQLiteHelper and extending SQLiteOpenHelper class
// and It is used to create a bridge between android and SQLite.
//To perform basic SQL operations we need to extend SQLiteOpenHelper class.
public class SQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SQLiteDatabase.db";
    private SQLiteDatabase database;
    private Context context;

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public static final String TABLE_NAME = "EVENTS";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_TITLE = "EVENT_TITLE";
    public static final String COLUMN_DATE = "EVENT_DATE";
    public static final String COLUMN_LOCATION = "EVENT_LOCATION";
    public static final String COLUMN_DESCRIPTION = "EVENT_DESCRIPTION";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " VARCHAR, " + COLUMN_DATE + " VARCHAR, " + COLUMN_LOCATION + " VARCHAR, "
                + COLUMN_DESCRIPTION + " VARCHAR);");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertRecord(Event event) {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, event.getTitle());
        contentValues.put(COLUMN_DATE, event.getDate());
        contentValues.put(COLUMN_LOCATION, event.getLocation());
        contentValues.put(COLUMN_DESCRIPTION, event.getDescription());
        long id = database.insert(TABLE_NAME, null, contentValues);

        if(id != -1) {
            event.setId(""+id);
            Toast.makeText(context, "New record inserted with id = " + id, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Error inserting new record", Toast.LENGTH_SHORT).show();
        }

        database.close();
    }


    public ArrayList<Event> getAllRecords() {
        database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null);

        ArrayList<Event> events = new ArrayList<Event>();
        Event event;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();

                event = new Event();
                event.setId(cursor.getString(0));
                event.setTitle(cursor.getString(1));
                event.setDate(cursor.getString(2));
                event.setLocation(cursor.getString(3));
                event.setDescription(cursor.getString(4));

                events.add(event);
            }
        }
        cursor.close();
        database.close();

        return events;
    }

}