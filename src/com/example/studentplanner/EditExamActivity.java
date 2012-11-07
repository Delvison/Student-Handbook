package com.example.studentplanner;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

public class EditExamActivity extends Activity{
	String eName;
	String course;
	EditText eNameInView;
	DatePicker dateInView;
	EditText rcvdInView;
	EditText maxInView;
	EditText courseInView;
	Assignment as;
	SQLiteDatabase db;
	CheckBox complete;
	
     	@Override
	   public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.edit_exam_view);
	        Bundle extras = getIntent().getExtras();
	        if (extras != null){
	        	eName = extras.getString("key");
	        }
	       // R.string.semesterString = sName;
	        this.initExam(eName);

	    }

	  public void initExam(String e){
	    	//db.execSQL("CREATE TABLE IF NOT EXISTS Assignments (Name VARCHAR, DueYear INT," +
	    		//	" DueMonth INT, DueDay INT, Description VARCHAR, MaxPoints INT, Course VARCHAR)");
		   //open database
  	   db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE, null);
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
       course = c.getString(c.getColumnIndex("Course"));
       int comp = c.getInt(c.getColumnIndex("Complete"));
         //close cursor
       c.close();
       
       eNameInView = (EditText) findViewById(R.id.editText1);
       eNameInView.setText(name);
       
       dateInView = (DatePicker) findViewById(R.id.datePicker1);
       dateInView.updateDate(dueYear, dueMonth, dueDay);
       
       rcvdInView = (EditText) findViewById(R.id.editText3);
       rcvdInView.setText(Integer.toString(ptsRcvd));
       
       maxInView = (EditText) findViewById(R.id.editText4);
       maxInView.setText(Integer.toString(ptsMax));
       
		complete = (CheckBox) findViewById(R.id.checkBox1);
		if (comp == 1) {
			complete.setChecked(true);
		} else {
			complete.setChecked(false);
		}
	   }
	  
		public void doneHandler(View v) {
			// replace values in db
			String nameGot = (String) eNameInView.getText().toString();
			int yrGot = dateInView.getYear();
			int mnthGot = dateInView.getMonth();
			int dayGot = dateInView.getDayOfMonth();
			int pntsGot = Integer.parseInt(rcvdInView.getText().toString());
			int maxGot = Integer.parseInt(maxInView.getText().toString());

			ContentValues values = new ContentValues();
			values.put("Name", nameGot);
			values.put("DueYear", yrGot);
			values.put("DueMonth", mnthGot);
			values.put("DueDay", dayGot);
			values.put("PointsRecieved", pntsGot);
			values.put("MaxPoints", maxGot);
			values.put("Course", course);
			if (complete.isChecked()) {
				values.put("Complete", 1);
			}else{
				values.put("Complete", 0);
			}

			db.update("Exams", values, "Name=" + "'" + eName + "'", null);
			// close db

			db.close();

			Intent intent = new Intent(getApplicationContext(),
					CourseActivity.class);
			intent.putExtra("key", course);
			startActivity(intent);
		}
		
		public void deleteHandler(View v) {
			db.delete("Exams", "Name=" + "'" + eName + "'", null);
			db.close();

			Intent intent = new Intent(getApplicationContext(),
					CourseActivity.class);
			intent.putExtra("key", course);
			startActivity(intent);
		}

}