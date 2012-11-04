package com.example.studentplanner;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class CourseActivity extends Activity {
    String cName;
    TextView courseName;
    TextView cDesc;
    TextView cTime;
    TextView cLoc;
    TextView cDays;

 	@Override
	   public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.course_view);
	        Bundle extras = getIntent().getExtras();
	        if (extras != null){
	        	cName = extras.getString("key");
	        }
	        this.initCourse(cName);
	        courseName = (TextView) findViewById(R.id.textView1);
	        courseName.setText(cName);


	    }

	  public void initCourse(String c){
	
	    //	db.execSQL("CREATE TABLE IF NOT EXISTS Courses (CourseName VARCHAR, Description VARCHAR," +
	    //	    	" Location VARCHAR, HourStart INT, MinuteStart INT, YearStart INT, MonthStart INT, DayStart INT, " +
	    //			" Occurences VARCHAR)");
	    SQLiteDatabase db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE, null);
	       //get values for the current semester being viewed
    Cursor cursor = db.rawQuery("select * from Courses where CourseName ='"+c+"'", null);
      //get those values
    cursor.moveToFirst();
    String name = cursor.getString(cursor.getColumnIndex("CourseName"));
    String desc = cursor.getString(cursor.getColumnIndex("Description"));
    String loc = cursor.getString(cursor.getColumnIndex("Location"));
    int startHr = cursor.getInt(cursor.getColumnIndex("HourStart"));
    int startMin = cursor.getInt(cursor.getColumnIndex("MinuteStart"));
    int startYr = cursor.getInt(cursor.getColumnIndex("YearStart"));
    int startMth = cursor.getInt(cursor.getColumnIndex("MonthStart"));
    int startDay = cursor.getInt(cursor.getColumnIndex("DayStart"));
    String occur = cursor.getString(cursor.getColumnIndex("Occurences"));

      //close cursor and database
    cursor.close();
    db.close();
      //create a course object
    Course co = new Course(name,desc,loc,true);
     //display information on the view
     cDesc = (TextView) findViewById(R.id.textView2);
     cTime = (TextView) findViewById(R.id.textView4);
     cLoc = (TextView) findViewById(R.id.textView3);
     cDays = (TextView) findViewById(R.id.textView5);
     
     cDesc.setText(desc);
     cTime.setText(startHr+":"+startMin);
     cLoc.setText(loc);
     cDays.setText(occur);
    
     }
}

