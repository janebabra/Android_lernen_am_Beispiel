package com.greendog.example.app;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import java.util.ArrayList;

public class CRUDonDB extends ListActivity {
	
	private final String SAMPLE_DB_NAME = "myDogsDb";
	private final String SAMPLE_TABLE_NAME = "dogs";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        ArrayList<String> results = new ArrayList<String>();
        SQLiteDatabase sampleDB = null;
        
        try {
        	sampleDB =  this.openOrCreateDatabase(SAMPLE_DB_NAME, MODE_PRIVATE, null);
        	
        	sampleDB.execSQL("CREATE TABLE IF NOT EXISTS " +
        			SAMPLE_TABLE_NAME +
        			" (PuppyName VARCHAR, BreedName VARCHAR," +
        			" Sex VARCHAR, Age INT(3));");
        	
        	sampleDB.execSQL("INSERT INTO " +
        			SAMPLE_TABLE_NAME +
        			" Values ('Jenny','Mix','female',15);");
        	sampleDB.execSQL("INSERT INTO " +
        			SAMPLE_TABLE_NAME +
        			" Values ('Theo','Terrier','male',9);");
        	sampleDB.execSQL("INSERT INTO " +
        			SAMPLE_TABLE_NAME +
        			" Values ('Jacky','Tibet Terrier','female',8);");
        	
        	Cursor cursor = sampleDB.rawQuery("SELECT PuppyName, Sex, BreedName, Age FROM " +
        			SAMPLE_TABLE_NAME +
        			" where Age > 5 LIMIT 5", null);
        	
        	if (cursor != null ) {
        		if  (cursor.moveToFirst()) {
        			do {
        				String firstName = cursor.getString(cursor.getColumnIndex("PuppyName"));
                        String breed = cursor.getString(cursor.getColumnIndex("BreedName"));
                        String sex = cursor.getString(cursor.getColumnIndex("Sex"));
        				int age = cursor.getInt(cursor.getColumnIndex("Age"));
        				results.add("" + firstName + ",\n Sex: " + sex + ", " + breed + ", Age: " + age);
        			}while (cursor.moveToNext());
        		} 
        	}
        	
        this.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,results));

        	
        } catch (SQLiteException se ) {
        	Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        } finally {
        	if (sampleDB != null) 
        		sampleDB.execSQL("DELETE FROM " + SAMPLE_TABLE_NAME);
        		sampleDB.close();
        }
    }
}