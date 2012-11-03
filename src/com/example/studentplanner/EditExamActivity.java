package com.example.studentplanner;

import java.util.GregorianCalendar;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class EditExamActivity extends Activity{
	String eName;
	EditText eNameInView;
	DatePicker dateInView;
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
	        	eName = extras.getString("key");
	        }
	       // R.string.semesterString = sName;
	        this.initAssignment(eName);
	 
	        
	    }

	  public void initAssignment(String e){
	    	//db.execSQL("CREATE TABLE IF NOT EXISTS Assignments (Name VARCHAR, DueYear INT," +
	    		//	" DueMonth INT, DueDay INT, Description VARCHAR, MaxPoints INT, Course VARCHAR)");
		   //open database
  	   db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE, null);
  	       //get values for the current semester being viewed
       Cursor c = db.rawQuery("select * from Courses where name ='"+e+"'", null);
         //get those values
       c.moveToFirst();
       String name = c.getString(c.getColumnIndex("Name"));
       int dueYear = c.getInt(c.getColumnIndex("DueYear"));
       int dueMonth = c.getInt(c.getColumnIndex("DueMonth"));
       int dueDay = c.getInt(c.getColumnIndex("DueDay"));
       int ptsRcvd = c.getInt(c.getColumnIndex("PointsRecieved"));
       int ptsMax = c.getInt(c.getColumnIndex("MaxPoints"));
       String course = c.getString(c.getColumnIndex("Course"));
         //close cursor and database
       c.close();
       
       eNameInView = (EditText) findViewById(R.id.editText1);
       eNameInView.setText(name);
       
       dateInView = (DatePicker) findViewById(R.id.datePicker1);
       dateInView.updateDate(dueYear, dueMonth, dueDay);
       
       rcvdInView = (EditText) findViewById(R.id.editText2);
       rcvdInView.setText(ptsRcvd);
       
       maxInView = (EditText) findViewById(R.id.editText3);
       maxInView.setText(ptsMax);
       
	   }
	  
	   public void handleClick1(View v){
		   //replace values in db
		  // db.update(table, values, whereClause, whereArgs)
		   //close db
		   db.close();
	   }

}