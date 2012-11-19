package com.example.studentplanner;

import android.app.ListActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class ProgressActivity extends ListActivity {
	String cName;
	String[] stringArr;
	String[] assignArr;
	String[] examArr;
	Spinner courses;
	SQLiteDatabase db;
	Course c;
	int i; //count of both
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progress_view);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			cName = extras.getString("key");
		}
		popAssignment();
        popExam();
        bridge();
		setListAdapter(new ArrayAdapter<String>(this,
				R.layout.semester_listview, stringArr));
	

	}

	public void backHandler(View v) {
		finish();
	}

	private void popAssignment() {
		try{
		 i=0;
		 db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE,
					null);
		Cursor cursor2 = db.rawQuery(
				"select * from Assignments where Course ='" + cName
						+ "' and Complete = 1", null); // get those values
		
		 i = i+ cursor2.getCount();
		 assignArr = new String[cursor2.getCount()];
		// instantiate array of semesters by size of the cursor + 1
		// set up a count int to keep track of array positions
		int count = 0;
		// move the cursor to first position
		cursor2.moveToFirst();
		// while the cursor position isn't passed the last item in the cursor
		while (cursor2.isAfterLast() == false) {
			String temp = cursor2.getString(cursor2.getColumnIndex("Name"));
			int temp1 = cursor2
					.getInt(cursor2.getColumnIndex("PointsRecieved"));
			int temp2 = cursor2.getInt(cursor2.getColumnIndex("MaxPoints"));
			int grade = temp1 / temp2 * 100;
			// store the string in "Session" column into the array of semesters
			assignArr[count] = temp + " " + Integer.toString(grade);
			// increment count
			count++;
			// move cursor by 1
			cursor2.moveToNext();
			
		}
		cursor2.close();
		} catch (SQLiteException e) {
			e.printStackTrace();
		}

	}
	
	public void popExam() {
		try{
		int count=0;
		Cursor cursor3 = db.rawQuery("select * from Exams where Course ='"
				+ cName + "' and Complete = 1", null); // get those values
		 i = i+ cursor3.getCount();
		 examArr = new String[cursor3.getCount()];

		cursor3.moveToFirst();

		while (cursor3.isAfterLast() == false) {
			String temp = cursor3.getString(cursor3.getColumnIndex("Name"));
			int temp1 = cursor3
					.getInt(cursor3.getColumnIndex("PointsRecieved"));
			int temp2 = cursor3.getInt(cursor3.getColumnIndex("MaxPoints"));
			int grade = temp1 / temp2 * 100;
			// store the string in "Session" column into the array of semesters
			examArr[count] = temp + " " + Integer.toString(grade);
			// increment count
			count++;
			// move cursor by 1
			cursor3.moveToNext();
		}

		// add an "add semester option
		// close the cursor
		cursor3.close();
		// close the database
		db.close();
		}catch (SQLiteException e){
			e.printStackTrace();
		}
	}
	
	public void bridge(){
		stringArr = new String[i];
		int count= 0;
		for (int j = 0; j < assignArr.length-1;j++){
			stringArr[count] = assignArr[j];
			count++;
		}
		for (int h = 0; h < examArr.length-1;h++){
			stringArr[count] = examArr[h];
			count++;
		}
	}

}
