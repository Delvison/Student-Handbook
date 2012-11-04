package com.example.studentplanner;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import java.util.ArrayList;

public class NotebookActivity extends Activity {
	String title;
	ArrayList<Note> notes;
	MyEvent currentEvent;
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notebook_view);
        
        
    }
    
    public void initNotebook(){
    	//creates a new database. Needs to be connected to a single database and added as a table
    	SQLiteDatabase db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE, null);
    	Cursor c = db.rawQuery("select , null);
    	c.moveToFirst();
    	for(int i =0; i<notes.size();i++){
        	//dynamically create note buttons based on the Note array
    		
        }
    	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notebook_view, menu);
        return true;
    }
}
