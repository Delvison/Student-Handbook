package com.example.studentplanner;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

public class EditAssignmentActivity extends Activity{
	String aName;
	EditText aNameInView;
	DatePicker dateInView;
	EditText descInView;
	EditText rcvdInView;
	EditText maxInView;
	EditText courseInView;
	Assignment as;
	SQLiteDatabase db;

	
     	@Override
	   public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.assignment_view);
	        Bundle extras = getIntent().getExtras();
	        if (extras != null){
	        	aName = extras.getString("key");
	        }
	       // R.string.semesterString = sName;
	        this.initAssignment(aName);
	 
	        
	    }

	  public void initAssignment(String a){
	    	//db.execSQL("CREATE TABLE IF NOT EXISTS Assignments (Name VARCHAR, DueYear INT," +
	    		//	" DueMonth INT, DueDay INT, Description VARCHAR, MaxPoints INT, Course VARCHAR)");
		   //open database
  	   db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE, null);
  	       //get values for the current semester being viewed
       Cursor c = db.rawQuery("select * from Assignments where name ='"+a+"'", null);
         //get those values
       c.moveToFirst();
       String name = c.getString(c.getColumnIndex("Name"));
       int dueYear = c.getInt(c.getColumnIndex("DueYear"));
       int dueMonth = c.getInt(c.getColumnIndex("DueMonth"));
       int dueDay = c.getInt(c.getColumnIndex("DueDay"));
       String desc = c.getString(c.getColumnIndex("Description"));
       int ptsRcvd = c.getInt(c.getColumnIndex("PointsRecieved"));
       int ptsMax = c.getInt(c.getColumnIndex("MaxPoints"));
       String course = c.getString(c.getColumnIndex("Course"));
         //close cursor and database
       c.close();
       
       aNameInView = (EditText) findViewById(R.id.editText1);
       aNameInView.setText(name);
       
       dateInView = (DatePicker) findViewById(R.id.datePicker1);
       dateInView.updateDate(dueYear, dueMonth, dueDay);
       
       rcvdInView = (EditText) findViewById(R.id.editText3);
       rcvdInView.setText(ptsRcvd);
       
       maxInView = (EditText) findViewById(R.id.editText4);
       maxInView.setText(ptsMax);
       
       descInView = (EditText) findViewById(R.id.editText5);
       descInView.setText(desc);
	   }
	  
	   public void handleClick1(View v){
		   //replace values in db
		   
		   //close db
		   db.close();
	    	
	   }

}
