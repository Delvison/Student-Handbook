package com.example.studentplanner;

import java.util.GregorianCalendar;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

public class CreateSemesterActivity extends Activity {
    Button createSemester;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_semester_view);
        createSemester = (Button) findViewById(R.id.createSemester);
    }

    public void handleClick1(View v){
    	
    	SQLiteDatabase db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE, null);
    	db.execSQL("CREATE TABLE IF NOT EXISTS Semesters (Session VARCHAR, YearStart INT," +
    			" MonthStart INT, DayStart INT,YearEnd INT, MonthEnd INT, DayEnd INT )");
    	
    	//extract session from view
    	Spinner sessionSpinner = (Spinner) findViewById(R.id.sessionSpinner);
    	String session = (String) sessionSpinner.getSelectedItem();
    	
    	//extract start date from view
    	DatePicker startDatePicker = (DatePicker) findViewById(R.id.semesterStartDate);
        int startDay = startDatePicker.getDayOfMonth();
        int startMonth = startDatePicker.getMonth();
        int startYear = startDatePicker.getYear();
            
        //extract end date from view
   	    DatePicker endDatePicker = (DatePicker) findViewById(R.id.semesterEndDate);
        int endDay = endDatePicker.getDayOfMonth();
        int endMonth = endDatePicker.getMonth();
        int endYear = endDatePicker.getYear();

        String sessionName = (String) session+" "+Integer.toString(startYear);
        //Semester s = new Semester(sessionName,new GregorianCalendar(startYear,startMonth,startDay),new GregorianCalendar(endYear,endMonth,endDay));d
        ContentValues values = new ContentValues();
        
        values.put("Session", sessionName);
        
        values.put("YearStart", startYear);
        values.put("MonthStart", startMonth);
        values.put("DayStart", startYear);

        values.put("YearEnd", endYear);
        values.put("MonthEnd", endMonth);
        values.put("DayEnd", endDay);
        
        db.insert("Semesters", null, values);
        
        //db.execSQL("INSERT INTO Semesters VALUES(sessionName,startYear,startMonth,startDay,endYear,endMonth,endDay);");
        db.close();		
      
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

}
