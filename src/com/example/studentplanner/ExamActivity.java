package com.example.studentplanner;

import java.util.GregorianCalendar;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class ExamActivity extends Activity {
	String eName;
	TextView eNameInView;
	TextView daysInView;
	long days;
	
     	@Override
	   public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.assignment_view);
	        Bundle extras = getIntent().getExtras();
	        if (extras != null){
	        	eName = extras.getString("key");
	        }
	       // R.string.semesterString = sName;
	        eNameInView = (TextView) findViewById(R.id.examView1);
	        eNameInView.setText(eName);
	        this.initAssignment(eName);
	        daysInView = (TextView) findViewById(R.id.textView5);
	        daysInView.setText(Long.toString(days)+" days left!");
	        
	    }

	  public void initAssignment(String e){
		   //open database
  	    SQLiteDatabase db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE, null);
  	       //get values for the current semester being viewed
       Cursor c = db.rawQuery("select * from Exams where name ='"+e+"'", null);
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
       db.close();
         //create a semester object
       Exam ex = new Exam(name,new GregorianCalendar(dueYear,dueMonth,dueDay));
         //calculate days toward end
       ex.calculateDaysDue();
       days = ex.daysTilDue;
       
       TextView dateInView = (TextView) findViewById(R.id.textView2);
       dateInView.setText(Integer.toString(dueYear)+"/"+Integer.toString(dueMonth)+"/"+Integer.toString(dueDay));
	   }
}

