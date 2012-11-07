package com.example.studentplanner;

import java.util.GregorianCalendar;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AssignmentActivity extends Activity {
	String aName;
	TextView aNameInView;
	TextView daysInView;
	TextView descInView;
	TextView rcvdInView;
	TextView maxInView;
	TextView courseInView;
	TextView complete;

	long days;
	
     	@Override
	   public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.assignment_view);
	        Bundle extras = getIntent().getExtras();
	        if (extras != null){
	        	aName = extras.getString("key");
	        }
	       // R.string.semesterString = sName;
	        aNameInView = (TextView) findViewById(R.id.assignmentView1);
	        aNameInView.setText(aName);
	        this.initAssignment(aName);
	        daysInView = (TextView) findViewById(R.id.textView5);
	        daysInView.setText(Long.toString(days)+" days left!");
	        
	    }

	  public void initAssignment(String a){
	//	  db.execSQL("CREATE TABLE IF NOT EXISTS Assignments (Name VARCHAR, DueYear INT," +
	  //  			" DueMonth INT, DueDay INT, Description VARCHAR, PointsRecieved INT, MaxPoints INT, Course VARCHAR)");
		   //open database
  	    SQLiteDatabase db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE, null);
  	       //get values for the current semester being viewed
       Cursor c = db.rawQuery("select * from Assignments where Name ='"+a+"'", null);
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
       int comp = c.getInt(c.getColumnIndex("Complete"));
         //close cursor and database
       c.close();
       db.close();
         //create a semester object
       Assignment as = new Assignment(name,new GregorianCalendar(dueYear,dueMonth,dueDay),desc);
         //calculate days toward end
       as.calculateDaysDue();
       days = as.daysTilDue;
       
       TextView dateInView = (TextView) findViewById(R.id.textView2);
       dateInView.setText(Integer.toString(dueYear)+"/"+Integer.toString(dueMonth)+"/"+Integer.toString(dueDay));

       descInView = (TextView) findViewById(R.id.textView4);
       descInView.setText(desc);
       
       rcvdInView = (TextView) findViewById(R.id.textView7);
       rcvdInView.setText(Integer.toString(ptsRcvd));
       
       maxInView = (TextView) findViewById(R.id.textView9);
       maxInView.setText(Integer.toString(ptsMax));
       
       courseInView = (TextView) findViewById(R.id.textView11);
       courseInView.setText(course);
       
       complete = (TextView) findViewById(R.id.textView13);
       if (comp == 1) {
           complete.setText("Completed.");
       } else {
    	   complete.setText("Not Completed.");
       }
       
	   }
	  
	  public void clickHandler(View V) {
		    Intent intent = new Intent(getApplicationContext(), EditAssignmentActivity.class);
	        intent.putExtra("key", aName);
	        startActivity(intent);
	   }
}
