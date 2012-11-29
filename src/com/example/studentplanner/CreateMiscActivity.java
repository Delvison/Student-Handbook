package com.example.studentplanner;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

public class CreateMiscActivity extends Activity {
	Button createMisc;
	String[] semestersArr;
	Spinner semesters;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_misc);
        createMisc = (Button) findViewById(R.id.createMisc);
        
        this.populateSemesters();
        
        semesters = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, semestersArr);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        semesters.setAdapter(spinnerArrayAdapter);
    }

    public void handleClick1(View v){
    	
    	SQLiteDatabase db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE, null);
    	//db.execSQL("CREATE TABLE IF NOT EXISTS Courses (CourseName VARCHAR, Description VARCHAR," +
    	//		" Location VARCHAR, HourStart INT, MinuteStart INT, YearStart INT, MonthStart INT, DayStart INT )");
    	db.execSQL("CREATE TABLE IF NOT EXISTS Miscs (MiscName VARCHAR, Description VARCHAR," +
    	    	" Location VARCHAR, HourStart INT, MinuteStart INT, YearStart INT, MonthStart INT, DayStart INT, " +
    			" Occurences VARCHAR, Semester VARCHAR)");
    	
    	//extract miscName from view
    	EditText editName = (EditText) findViewById(R.id.editName);
    	String misc = (String) editName.getText().toString();
    	
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

        //This really needs to be implemented in the Misc_view.xml
        Spinner reoccurSpinner = (Spinner) findViewById(R.id.spinner1);
        String occurences = (String) reoccurSpinner.getSelectedItem();
        
        String semester = (String) semesters.getSelectedItem().toString();
        
        ContentValues values = new ContentValues();
        
        values.put("MiscName", misc);
        values.put("Description", descript);
        values.put("Location", loc);
        
        values.put("YearStart", startYear);
        values.put("MonthStart", startMonth + 1);
        values.put("DayStart", startDay);

        values.put("HourStart", startHour);
        values.put("MinuteStart", startMinute);
        
        values.put("Occurences", occurences);
        values.put("Semester", semester);
        
        db.insert("Miscs", null, values);
        
        //db.execSQL("INSERT INTO Semesters VALUES(sessionName,startYear,startMonth,startDay,endYear,endMonth,endDay);");
        db.close();		
      
        Intent intent = new Intent(getApplicationContext(), MiscActivity.class);
        intent.putExtra("key", misc);
        startActivity(intent);
        finish();
    }
    
    public void populateSemesters() {
    	try{
    	//open database
	    SQLiteDatabase db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE, null);
	    //query. receive a cursor
	    Cursor c= db.rawQuery("SELECT Session FROM Semesters", null);
	    //count how many items in cursor. add 1 to leave space for add semester option
	    int i = c.getCount();
	    //instantiate array of semesters by size of the cursor + 1
	    semestersArr = new String[i];
	    //set up a count int to keep track of array positions
	    int count = 0;
        //move the cursor to first position
	    c.moveToFirst();
	    
        //while the cursor position isn't passed the last item in the cursor 
	    while(c.isAfterLast()==false) {
	    		//store the string in "Session" column into the array of semesters
	    		semestersArr[count] = c.getString(c.getColumnIndex("Session"));
	    		//increment count
	    		count++;
	    		//move cursor by 1
	    		c.moveToNext();
	    }
	    //close the cursor
	    c.close();
	    //close the database
   	   	db.close();
    	 }catch(SQLiteException e){
    		 semestersArr = new String[1];
    		 semestersArr[0] = "No Semesters Exist!"; 
    	 }
    }
}
