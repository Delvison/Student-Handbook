package com.example.studentplanner;

import java.util.GregorianCalendar;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Spinner;

public class CreateSemesterActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester_create);
    }

    public void handleClick(View v){
    	//extract session from view
    	Spinner sessionSpinner = (Spinner) findViewById(R.id.sessionSpinner);
    	String session = (String) sessionSpinner.getSelectedItem();
    	
    	//extract start date from view
    	 DatePicker startDatePicker = (DatePicker) findViewById(R.id.semesterStartDate);
        int startDay = startDatePicker.getDayOfMonth();
        int startMonth = startDatePicker.getMonth();
        int startYear = startDatePicker.getYear();
        String startDate = startYear+","+startMonth+","+startDay;
        
      //extract end date from view
   	   DatePicker endDatePicker = (DatePicker) findViewById(R.id.semesterEndDate);
       int endDay = startDatePicker.getDayOfMonth();
       int endMonth = startDatePicker.getMonth();
       int endYear = startDatePicker.getYear();
       String endDate = endYear+","+endMonth+","+endDay;

       Semester s = new Semester(session,new GregorianCalendar(startYear,startMonth,startDay),new GregorianCalendar(endYear,endMonth,endDay));
    	
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

}
