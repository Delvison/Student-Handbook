package com.example.studentplanner;

import android.app.ListActivity;
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

public class ProgressActivity extends ListActivity {

	String[] coursesArr;
	Spinner courses;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_view);
        
        this.populateCourses();
        
        courses = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, coursesArr);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        courses.setAdapter(spinnerArrayAdapter);
    }
	
    public void handleClick(View v){
    	
    	SQLiteDatabase db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE, null);
    	
    	/*		I will need to display these somehow.
    	String course = (String) courses.getSelectedItem();
		Cursor cursor1 = db.rawQuery("select * from Assignments where CourseName ='"
				+ course + "' and Complete = 1", null);
		// get those values
		cursor1.moveToFirst();
    	
		Cursor cursor2 = db.rawQuery("select * from Exams where CourseName ='"
				+ course + "' and Complete = 1", null);
		// get those values
		cursor2.moveToFirst();
		*/
    	String course = (String) courses.getSelectedItem().toString();
    	
        ContentValues values = new ContentValues();
        
        //int grade = this.calculateAverage();
        
        //values.put("Grade", grade);

        db.insert("Courses", null, values);
        
        db.close();
      
        Intent intent = new Intent(getApplicationContext(), CourseActivity.class);
        intent.putExtra("key", course);
        startActivity(intent);
        finish();
    }
	
    public void populateCourses() {
    	try{
    	//open database
	    SQLiteDatabase db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE, null);
	    //query. receive a cursor
	    Cursor c= db.rawQuery("SELECT CourseName FROM Courses", null);
	    //count how many items in cursor. add 1 to leave space for add semester option
	    int i = c.getCount();
	    //instantiate array of semesters by size of the cursor + 1
	    coursesArr = new String[i];
	    //set up a count int to keep track of array positions
	    int count = 0;
        //move the cursor to first position
	    c.moveToFirst();
	    
        //while the cursor position isn't passed the last item in the cursor 
	    while(c.isAfterLast()==false) {
	    		//store the string in "Session" column into the array of semesters
	    		coursesArr[count] = c.getString(c.getColumnIndex("CourseName"));
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
    		 coursesArr = new String[1];
    		 coursesArr[0] = "No Courses Exist!"; 
    	 }
    }
	
}
