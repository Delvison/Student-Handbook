package com.example.studentplanner;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

public class EditSemesterActivity extends Activity {
	Spinner sessionSpinner;
	DatePicker startDatePicker;
	DatePicker endDatePicker;
	String sName;
	SQLiteDatabase db;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_semester_view);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			sName = extras.getString("key");
		}
		this.initSemester(sName);
	}

	public void initSemester(String s) {
		db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE, null);
		sessionSpinner = (Spinner) findViewById(R.id.sessionSpinner);
		startDatePicker = (DatePicker) findViewById(R.id.semesterStartDate);
		endDatePicker = (DatePicker) findViewById(R.id.semesterEndDate);
		Cursor c = db.rawQuery("select * from Semesters where Session ='" + s
				+ "'", null);
		// get those values
		c.moveToFirst();
		String name = c.getString(c.getColumnIndex("Session"));
		int startDay = c.getInt(c.getColumnIndex("DayStart"));
		int startMonth = c.getInt(c.getColumnIndex("MonthStart"));
		int startYear = c.getInt(c.getColumnIndex("YearStart"));

		int endDay = c.getInt(c.getColumnIndex("DayEnd"));
		int endMonth = c.getInt(c.getColumnIndex("MonthEnd"));
		int endYear = c.getInt(c.getColumnIndex("YearEnd"));
		// close cursor
		c.close();

		startDatePicker.updateDate(startYear, startMonth, startDay);
		endDatePicker.updateDate(endYear, endMonth, endDay);
		int p = 0;
		if (name.equals("Fall " + Integer.toString(startYear)))
			p = 0;
		if (name.equals("Spring " + Integer.toString(startYear)))
			p = 1;
		if (name.equals("Winter " + Integer.toString(startYear)))
			p = 2;
		if (name.equals("Summer " + Integer.toString(startYear)))
			p = 3;
		sessionSpinner.setSelection(p);
	}

	public void doneHandler(View v) {
		// extract session from view
		String session = (String) sessionSpinner.getSelectedItem();

		// extract start date from view
		int startDay = startDatePicker.getDayOfMonth();
		int startMonth = startDatePicker.getMonth();
		int startYear = startDatePicker.getYear();

		// extract end date from view
		int endDay = endDatePicker.getDayOfMonth();
		int endMonth = endDatePicker.getMonth();
		int endYear = endDatePicker.getYear();

		String sessionName = (String) session + " "
				+ Integer.toString(startYear);
		// Semester s = new Semester(sessionName,new
		// GregorianCalendar(startYear,startMonth,startDay),new
		// GregorianCalendar(endYear,endMonth,endDay));d
		ContentValues values = new ContentValues();

		values.put("Session", sessionName);

		values.put("YearStart", startYear);
		values.put("MonthStart", startMonth);
		values.put("DayStart", startDay);

		values.put("YearEnd", endYear);
		values.put("MonthEnd", endMonth);
		values.put("DayEnd", endDay);

		db.update("Semesters", values, "Session = " + "'" + sName + "'", null);
		// close db

		db.close();
		Intent intent = new Intent(getApplicationContext(),
				SemesterActivity.class);
		intent.putExtra("key", sessionName);
		startActivity(intent);
		finish();

	}

	public void deleteHandler(View v) {
		// Create the alert box
		AlertDialog.Builder alertbox = new AlertDialog.Builder(this);

		// Set the message to display
		alertbox.setMessage("WARNING! This will delete the semester and EVERYTHING associated"
				+ " with it. Would you like to continue?");

		// Add a neutral button to the alert box and assign a click listener

		alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {

			}
		});
		alertbox.setNeutralButton("Yes", new DialogInterface.OnClickListener() {

			// Click listener on the neutral button of alert box
			public void onClick(DialogInterface arg0, int arg1) {
				db.delete("Semesters", "Session=" + "'" + sName + "'", null);
						// must also delete everything belonging to semester
				db.close();
				// The neutral button was clicked
				Intent intent = new Intent(getApplicationContext(),
						SemesterListviewActivity.class);
				startActivity(intent);
				finish();
			}
		});

		// show the alert box
		alertbox.show();
	


	}//end of deleteHandler

}
