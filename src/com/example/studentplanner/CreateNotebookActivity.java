package com.example.studentplanner;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class CreateNotebookActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_notebook_view);
    }
    
    public void handleClick1(View v){
    	SQLiteDatabase db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE, null);
    	db.execSQL("CREATE TABLE IF NOT EXISTS Notebooks (Session VARCHAR, YearStart INT," +
    			" MonthStart INT, DayStart INT,YearEnd INT, MonthEnd INT, DayEnd INT )");
    	
    }
}
