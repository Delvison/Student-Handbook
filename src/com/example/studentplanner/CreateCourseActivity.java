package com.example.studentplanner;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

public class CreateCourseActivity extends Activity {
    Button createCourse;

    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_course);
        createCourse = (Button) findViewById(R.id.createCourse);
 
    }

    public void handleClick1(View v){
    	
    	SQLiteDatabase db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE, null);
    	//db.execSQL("CREATE TABLE IF NOT EXISTS Courses (CourseName VARCHAR, Description VARCHAR," +
    	//		" Location VARCHAR, HourStart INT, MinuteStart INT, YearStart INT, MonthStart INT, DayStart INT )");
    	db.execSQL("CREATE TABLE IF NOT EXISTS Courses (CourseName VARCHAR unique, Description VARCHAR," +
    	    	" Location VARCHAR, HourStart INT, MinuteStart INT, YearStart INT, MonthStart INT, DayStart INT, " +
    			" Occurences VARCHAR)");
    	
    	//extract courseName from view
    	EditText editName = (EditText) findViewById(R.id.editName);
    	String course = (String) editName.getText().toString();
    	
    	//extract description from view
    	EditText editDescription = (EditText) findViewById(R.id.editDescription);
    	String descript = (String) editDescription.getText().toString();
    	
    	//extract location from view
    	EditText editLocation = (EditText) findViewById(R.id.editLocation);
    	String loc = (String) editLocation.getText().toString();
    	
    	//extract start date from view
    	DatePicker datePicker1 = (DatePicker) findViewById(R.id.datePicker1);
        int startDay = datePicker1.getDayOfMonth();
        int startMonth = datePicker1.getMonth();
        int startYear = datePicker1.getYear();
            
        //extract start time from view
   	    TimePicker timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
        int startMinute = timePicker1.getCurrentMinute(); //is this right?
        int startHour = timePicker1.getCurrentHour(); //is this right?

        Spinner reoccurSpinner = (Spinner) findViewById(R.id.reoccurSpinner);
        String occurences = (String) reoccurSpinner.getSelectedItem();
        
        ContentValues values = new ContentValues();
        
        values.put("CourseName", course);
        values.put("Description", descript);
        values.put("Location", loc);
        
        values.put("YearStart", startYear);
        values.put("MonthStart", startMonth);
        values.put("DayStart", startDay);

        values.put("HourStart", startHour);
        values.put("MinuteStart", startMinute);
        
        values.put("Occurences", occurences);
        
        db.insert("Courses", null, values);
        
        //db.execSQL("INSERT INTO Semesters VALUES(sessionName,startYear,startMonth,startDay,endYear,endMonth,endDay);");
        db.close();		
      
        Intent intent = new Intent(getApplicationContext(), CourseListviewActivity.class);
        //putExtras() ... take user to the new course they just created
        startActivity(intent);
        
    }
}
