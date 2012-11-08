package com.example.studentplanner;

import java.util.GregorianCalendar;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SemesterActivity extends Activity {
	String sName;
	TextView sNameInView;
	TextView daysInView;
	long days;
	Button courses;
	Button Events;
	
     	@Override
	   public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.semester_view);
	        Bundle extras = getIntent().getExtras();
	        if (extras != null){
	        	sName = extras.getString("key");
	        }
	       // R.string.semesterString = sName;
	        sNameInView = (TextView) findViewById(R.id.textView1);
	        sNameInView.setText(sName);
	        this.initSemester(sName);
	        daysInView = (TextView) findViewById(R.id.textView2);
	        if (days != 0){
	        daysInView.setText(Long.toString(days)+" days left!");
	        } else{
	        	daysInView.setText("Semester Over.");
	        }
	    }
	   
	   public void initSemester(String sem){
		   //open database
   	    SQLiteDatabase db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE, null);
   	       //get values for the current semester being viewed
        Cursor c = db.rawQuery("select * from Semesters where Session ='"+sem+"'", null);
          //get those values
        c.moveToFirst();
        String name = c.getString(c.getColumnIndex("Session"));
        int startYear = c.getInt(c.getColumnIndex("YearStart"));
        int startMonth = c.getInt(c.getColumnIndex("MonthStart"));
        int startDay = c.getInt(c.getColumnIndex("DayStart"));
        int endYear = c.getInt(c.getColumnIndex("YearEnd"));
        int endMonth = c.getInt(c.getColumnIndex("MonthEnd"));
        int endDay = c.getInt(c.getColumnIndex("DayEnd"));
          //close cursor and database
        c.close();
        db.close();
          //create a semester object
        Semester s = new Semester(name,new GregorianCalendar(startYear,startMonth,startDay),new GregorianCalendar(endYear,endMonth,endDay));
          //calculate days toward end
        s.calculateDaysToEnd();
        days = s.daysTowardsEnd;
	   }
	   
	   @Override
	   public boolean onCreateOptionsMenu(Menu menu) {
	       MenuInflater inflater = getMenuInflater();
	       inflater.inflate(R.menu.activity_main, menu);
	       
	       return true;
	   }
	   
	   @Override
	   public boolean onOptionsItemSelected(MenuItem item) {
		    switch (item.getItemId()) {
	          case R.id.addAssignment:
	            startActivity(new Intent(this, CreateAssignmentActivity.class));
	          return true;
	          case R.id.viewEvents:
		            startActivity(new Intent(this, CreateCourseActivity.class));
		          return true;
	          case R.id.editSemester:
	        	  Intent i = new Intent(this, EditSemesterActivity.class);
	        	  i.putExtra("key", sName);
	        	  startActivity(i);
		            return true;
		    }
	        return super.onOptionsItemSelected(item);
	    }
	   
	   public void clickHandler(View v) {
		     Intent intent = new Intent(getApplicationContext(), CourseListviewActivity.class);
		      startActivity(intent);
	   }
}