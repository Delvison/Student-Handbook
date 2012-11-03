package com.example.studentplanner;

import java.util.GregorianCalendar;

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

public class CreateAssignmentActivity extends Activity {
	Button createActivity;
	String[] coursesArr;
	Spinner courses;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_assignment_view);
        createActivity = (Button) findViewById(R.id.createAssignment);
        
        this.populateCourses();
        
        courses = (Spinner) findViewById(R.id.courseSpinner1);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, coursesArr);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        courses.setAdapter(spinnerArrayAdapter);
    }
	
    public void handleClick(View v){
    	
    	SQLiteDatabase db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE, null);
    	db.execSQL("CREATE TABLE IF NOT EXISTS Assignments (Name VARCHAR, DueYear INT," +
    			" DueMonth INT, DueDay INT, Description VARCHAR, PointsRecieved INT, MaxPoints INT, Course VARCHAR)");
    	
    	//extract name from view
    	EditText nameText = (EditText) findViewById(R.id.assignmentText1);
    	String name = nameText.getText().toString();
  
    	
    	//extract start date from view
    	DatePicker assignmentDatePicker = (DatePicker) findViewById(R.id.semesterStartDate);
        int dueDay = assignmentDatePicker.getDayOfMonth();
        int dueMonth = assignmentDatePicker.getMonth();
        int dueYear = assignmentDatePicker.getYear();
            
        //extract description from the view
        EditText decText = (EditText) findViewById(R.id.assignmentText2);
        String description = decText.getText().toString();
    	String course = (String) courses.getSelectedItem();

        ContentValues values = new ContentValues();
        
        values.put("Name", name);
        
        values.put("DueYear", dueYear);
        values.put("DueMonth", dueMonth);
        values.put("DueDay", dueDay);
        values.put("Description", description);
        values.put("PointsRecieved", 0);
        values.put("MaxPoints", 0);
        values.put("Course", course);

        db.insert("Assignments", null, values);
        
        //db.execSQL("INSERT INTO Semesters VALUES(sessionName,startYear,startMonth,startDay,endYear,endMonth,endDay);");
        db.close();
      
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
    
    public void populateCourses() {
    	try{
    	//open database
	    SQLiteDatabase db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE, null);
	    //query. receive a cursor
	    Cursor c= db.rawQuery("SELECT Name FROM Assignments", null);
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
	    		coursesArr[count] = c.getString(c.getColumnIndex("Name"));
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
